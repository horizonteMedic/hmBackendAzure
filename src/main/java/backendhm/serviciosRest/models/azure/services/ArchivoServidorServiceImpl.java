package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.CargaMasivaDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpleadoTipoDocDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.TipoArchivoDTO;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import backendhm.serviciosRest.models.azure.entity.campaña.DesktopEmpleadoTipoDoc;
import backendhm.serviciosRest.models.azure.repository.campaña.IDesktopEmpleadoTipoDocRepository;
import backendhm.serviciosRest.models.azure.repository.parametros.IRespuestaBackendRepository;
import backendhm.serviciosRest.models.spTrujilloNP.entity.EmpleadoTipoDoc;
import backendhm.serviciosRest.models.spTrujilloNP.entity.RespuestaBackendNP;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IEmpleadoTipoDocRepository;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IRespuestaBackendNPRepository;
import backendhm.serviciosRest.models.spTrujilloNP.services.IRespuestaBackendService;
import backendhm.serviciosRest.models.spTrujilloSD.Service.IRespuestaBackendServiceSD;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.ArchivoServidorDTO;
import backendhm.serviciosRest.models.azure.entity.sistemaArchivos.ArchivosServidor;
import backendhm.serviciosRest.models.azure.repository.sistemasArchivos.IArchivoServidorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchivoServidorServiceImpl implements IArchivoServidorService {

    @Autowired
    private IArchivoServidorRepository archivoServidorRepository;

    @Autowired
    private IRespuestaBackendRepository respuestaBackendRepository;

    @Autowired
    private IRespuestaBackendNPRepository respuestaBackendNPRepository;

    @Autowired
    private IRespuestaBackendService respuestaBackendServiceNPService;


    @Autowired
    private IRespuestaBackendServiceSD respuestaBackendServiceSD;

    @Autowired
    private ITipoArchivoService tipoArchivoService;


    @Autowired
    private IEmpleadoTipoDocRepository empleadoTipoDocRepository;

    @Autowired
    private IDesktopEmpleadoTipoDocRepository desktopEmpleadoTipoDocRepository;

    @Override
    public ArchivoServidorDTO detalleArchivoServidor(String hc, long ta) {
        ArchivosServidor archivosServidor=archivoServidorRepository.detalleArchivoServidor(hc,ta).
                orElseThrow();
      //  System.out.println("El archivo de base 64 : ");
        String resultService ="";
        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        String  base64File="";
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);
            final String NOMBRE_ARCHIVO_TEMP = "temp.pdf";

            CloudBlockBlob blockBlob = container.getBlockBlobReference(archivosServidor.getRutaArchivo());
            File file = new File(NOMBRE_ARCHIVO_TEMP);
            blockBlob.downloadToFile(file.getAbsolutePath());
            byte[] fileContent = Files.readAllBytes(file.toPath());
            base64File = Base64.getEncoder().encodeToString(fileContent);
        //    System.out.println("El archivo de base 64 : "+base64File);
            resultService = "Download success!!!";

        }catch (Exception e){
            resultService = e.getMessage();
        }
        ArchivoServidorDTO archivoServidorDTO=mapearDTO(archivosServidor);
        archivoServidorDTO.setFileBase64(base64File);

        return archivoServidorDTO;
    }

    @Override
    public EmpleadoTipoDocDTO detalleArchivoEmpleado(long dni, String tipoArchivo) {
        DesktopEmpleadoTipoDoc empleadoTipoDoc=desktopEmpleadoTipoDocRepository.listarArchivoEmpleado(dni,tipoArchivo).orElseThrow();
    //    System.out.println("la entidad empleado es:  "+empleadoTipoDoc);
        String resultService ="";
        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        String  base64File="";
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);
            final String NOMBRE_ARCHIVO_TEMP = "temp2.pdf";

            CloudBlockBlob blockBlob = container.getBlockBlobReference(empleadoTipoDoc.getRuta());
            File file = new File(NOMBRE_ARCHIVO_TEMP);
            blockBlob.downloadToFile(file.getAbsolutePath());
            byte[] fileContent = Files.readAllBytes(file.toPath());
            base64File = Base64.getEncoder().encodeToString(fileContent);
          //  System.out.println("El archivo de base 64 : "+base64File);
            resultService = "Download success!!!";

        }catch (Exception e){
            resultService = e.getMessage();
        }
        EmpleadoTipoDocDTO empleadoTipoDocDTO=mapearDTOEmpleadoTipoDoc(empleadoTipoDoc);
        empleadoTipoDocDTO.setBase64(base64File);

        return empleadoTipoDocDTO;
    }

    @Override
    public RespuestaBackendDTO registrarArchivoOActualizar(ArchivoServidorDTO archivoServidorDTO) {

        RespuestaBackend respuestaBackend=respuestaBackendRepository.existenciaDelArchivo(archivoServidorDTO.getHistoriaClinica(),archivoServidorDTO.getId_tipo_archivo()).orElseThrow();
      //  System.out.println("la existencia del archivo:"+respuestaBackend);
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();
        ArchivoServidorDTO archivosServidor=new ArchivoServidorDTO();
        if(respuestaBackend.getId()==0){
            archivosServidor=creararchivoServidor(archivoServidorDTO);
                respuestaBackendDTO.setId(Long.valueOf(1));
                respuestaBackendDTO.setMensaje("Se registro con exito!");
        }
        else
        {
           // System.out.println("el id respuesta es:"+respuestaBackend.getId());

            ArchivosServidor archivosServidor1=archivoServidorRepository.detalleArchivoServidor(archivoServidorDTO.getHistoriaClinica(), archivoServidorDTO.getId_tipo_archivo()).orElseThrow();
            ArchivoServidorDTO archivoServidorDTO1=actualizarArchivoServidor(archivoServidorDTO,archivosServidor1.getId());
            respuestaBackendDTO.setId(Long.valueOf(2));
            respuestaBackendDTO.setMensaje("Se actualizo con exito!!");
        }

        return respuestaBackendDTO;
    }

    @Override
    public RespuestaBackendDTO registrarActualizarArchivoEmpleado(EmpleadoTipoDocDTO empleadoTipoDocDTO) {
        RespuestaBackend respuestaBackend=respuestaBackendRepository.existenciaEmpleadoTipoDoc
                (empleadoTipoDocDTO.getDni(), empleadoTipoDocDTO.getTipoArchivo()).orElseThrow();

        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();

        if(respuestaBackend.getId()==0){
          System.out.println("entro al registrar o actualziar archivo empleado, opcion 0");

            crearEmpleadoTipoDoc(empleadoTipoDocDTO);
            respuestaBackendDTO.setId(Long.valueOf(1));
            respuestaBackendDTO.setMensaje("Se registro con exito!");
        }
        else
        {
            // System.out.println("el id respuesta es:"+respuestaBackend.getId());
           System.out.println("entro al registrar o actualziar archivo empleado, opcion actualizar");

            DesktopEmpleadoTipoDoc empleadoTipoDoc=desktopEmpleadoTipoDocRepository.listarArchivoEmpleado(empleadoTipoDocDTO.getDni(), empleadoTipoDocDTO.getTipoArchivo()).orElseThrow();

            EmpleadoTipoDocDTO empleadoTipoDocDTO1=actualizarArchivoServidorEmpleado(empleadoTipoDocDTO,empleadoTipoDoc.getId_empleado_tipo_doc());
            respuestaBackendDTO.setId(Long.valueOf(2));
            respuestaBackendDTO.setMensaje("Se actualizo con exito!!");
        }

        return respuestaBackendDTO;
    }

    @Override
    public RespuestaBackendDTO registroCargaMasiva(CargaMasivaDTO cargaMasivaDTO) {
 //       System.out.println("ENTRO A LA CARGA MASIVA");
        RespuestaBackendDTO respuestaBackendDTORespuesta=new RespuestaBackendDTO();
        try {
            String ruta = "";
            String parte1 = "";
            String parte2 ="";
            long verificador=1;
            String[] parts = cargaMasivaDTO.getNombreArchivo().split("-");
            // si el primer valor no es numero, cambia el orden y toma el segundo haciendo referencia a la segunda nomenclatura
            try {
                parte1 = parts[0].trim();
                parte2 = parts[1].trim();
                verificador= Long.parseLong(parte1);

            }catch (Exception e){
                String temporal=parts[2].trim();
                String contenedor= StringUtils.right(temporal,4);

                parte2 = parts[0].trim();
                String[] partes = temporal.split(contenedor);
                parte1=partes[0].trim();

            }

            ArchivoServidorDTO archivoServidorDTO = new ArchivoServidorDTO();
            TipoArchivoDTO  tipoArchivoDTO = tipoArchivoService.tipoArchivoPorNomencaltura(parte2);


            backendhm.serviciosRest.models.spTrujilloNP.dto.RespuestaBackendDTO respuestaBackendDTO=null;
            backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD respuestaBackendDTOTSD=null;
            if (cargaMasivaDTO.getCodigoSede().contains("T-SD")){
                respuestaBackendDTOTSD = respuestaBackendServiceSD.busquedaDniPorNOrden(Long.parseLong(parte1));
                archivoServidorDTO.setDni(respuestaBackendDTOTSD.getId());

            }

            else {
                if (cargaMasivaDTO.getCodigoSede().contains("HMAC")) {

                    respuestaBackendDTO = respuestaBackendServiceNPService.busquedaDniPorReferencia(parte1);
                    archivoServidorDTO.setDni(respuestaBackendDTO.getId());

                } else {
                    respuestaBackendDTO = respuestaBackendServiceNPService.busquedaDniPorNOrden(Long.parseLong(parte1));
              //      System.out.println("el dni es:"+respuestaBackendDTO.getId());
                    archivoServidorDTO.setDni(respuestaBackendDTO.getId());
                }
            }
            archivoServidorDTO.setRutaArchivo("");
            archivoServidorDTO.setNombreArchivo(cargaMasivaDTO.getNombreArchivo());
            archivoServidorDTO.setHistoriaClinica(cargaMasivaDTO.getCodigoSede() + "-" + parte1);
            archivoServidorDTO.setOrden(Long.valueOf(parte1));
            archivoServidorDTO.setServidor(cargaMasivaDTO.getServidor());
            archivoServidorDTO.setEstado(cargaMasivaDTO.getEstado());
            archivoServidorDTO.setFechaRegistro(cargaMasivaDTO.getFechaRegistro());
            archivoServidorDTO.setUserRegistro(cargaMasivaDTO.getUserRegistro());
            archivoServidorDTO.setId_tipo_archivo(tipoArchivoDTO.getId());
            archivoServidorDTO.setRutaArchivo(ruta);
            archivoServidorDTO.setFileBase64(cargaMasivaDTO.getFileBase64());
            RespuestaBackendDTO respuestaBackendDTO1 = registrarArchivoOActualizar(archivoServidorDTO);
            respuestaBackendDTORespuesta.setId(Long.valueOf(1));
            respuestaBackendDTORespuesta.setMensaje(cargaMasivaDTO.getNombreArchivo());
        }
        catch (Exception e){
          //  System.out.println("El error en la carga masiva es:"+e);
            respuestaBackendDTORespuesta.setId(Long.valueOf(0));
            respuestaBackendDTORespuesta.setMensaje(cargaMasivaDTO.getNombreArchivo());

        }
        return respuestaBackendDTORespuesta;

    }

    @Override
    public RespuestaBackendDTO cargaMasivaArchivos(String sede) {
        String ruta="";

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("C:\\Users\\josue\\Documents\\CARGA MASIVA\\Avance de lonardo"))) {
                int i=1;
            for (Path file : stream) {
                ruta=file.getFileName().toString();
              //  System.out.println("la ruta del archivo es: "+file.getFileName());
                String base64=archivoAbase64(ruta);
                //System.out.println("base 64:"+archivoAbase64(ruta));
                String[] parts = ruta.split("-");
                String parte1=parts[0].trim();
                String parte2=parts[1].trim();

                //System.out.println(file.getFileName());
                //System.out.println(parte1);
                //System.out.println(parte2);
                TipoArchivoDTO tipoArchivoDTO=tipoArchivoService.tipoArchivoPorNomencaltura(parte2);
                backendhm.serviciosRest.models.spTrujilloNP.dto.RespuestaBackendDTO
                        respuestaBackendDTO =respuestaBackendServiceNPService.busquedaDniPorNOrden(Long.parseLong(parte1));
                //System.out.println(respuestaBackendDTO);
                //System.out.println(tipoArchivoDTO);
                ArchivoServidorDTO archivoServidorDTO= new ArchivoServidorDTO();
                archivoServidorDTO.setRutaArchivo("");
                archivoServidorDTO.setNombreArchivo(ruta);
                archivoServidorDTO.setDni(respuestaBackendDTO.getId());
                archivoServidorDTO.setHistoriaClinica(sede+"-"+parte1);
                archivoServidorDTO.setOrden(Long.valueOf(parte1));
                archivoServidorDTO.setServidor("azure");
                archivoServidorDTO.setEstado(true);
                archivoServidorDTO.setFechaRegistro(LocalDate.parse("2024-05-23"));
                archivoServidorDTO.setUserRegistro("developer");
                archivoServidorDTO.setId_tipo_archivo(tipoArchivoDTO.getId());
                archivoServidorDTO.setRutaArchivo(ruta);
                archivoServidorDTO.setFileBase64(base64);
                //System.out.println("El archivo dto a cargar es :"+archivoServidorDTO);
                RespuestaBackendDTO respuestaBackendDTO1=registrarArchivoOActualizar(archivoServidorDTO);
                //System.out.println("la respuesta de registrar o actualizar es:"+respuestaBackendDTO1);
               // System.out.println("Archivos cargados correctamente:"+i+" , nombre archivo: "+archivoServidorDTO.getNombreArchivo());
                i++;
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ruta);
        }
        return null;
    }

    public void crearEmpleadoTipoDoc(EmpleadoTipoDocDTO empleadoTipoDocDTO){
        String resultService ="";

        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        empleadoTipoDocDTO.setRuta("EMPLEADO-DNI-"+empleadoTipoDocDTO.getDni()+"/"+empleadoTipoDocDTO.getNombreArchivo());
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);

            CloudBlob blob;
            blob = container.getBlockBlobReference(empleadoTipoDocDTO.getRuta());
            byte[] decodedBytes = Base64.getDecoder().decode(empleadoTipoDocDTO.getBase64());
            blob.uploadFromByteArray(decodedBytes,0,decodedBytes.length);

            resultService = "OK";
            DesktopEmpleadoTipoDoc empleadoTipoDoc=mapearEntidadEmpleadoTipoDoc(empleadoTipoDocDTO);
            DesktopEmpleadoTipoDoc empleadoTipoDocNuevo=desktopEmpleadoTipoDocRepository.save(empleadoTipoDoc);


        }catch (Exception e){
            resultService = e.getMessage();
        }

    }


    @Override
    public ArchivoServidorDTO creararchivoServidor(ArchivoServidorDTO archivoServidorDTO) {
        String resultService ="";
     //   System.out.println("Estamos recibiendo el objeto:"+archivoServidorDTO);
        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        archivoServidorDTO.setRutaArchivo("DNI-"+archivoServidorDTO.getDni()+"/HC-"+archivoServidorDTO.getHistoriaClinica()+"/"+archivoServidorDTO.getNombreArchivo());

        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);

            CloudBlob blob;
            blob = container.getBlockBlobReference(archivoServidorDTO.getRutaArchivo());
            byte[] decodedBytes = Base64.getDecoder().decode(archivoServidorDTO.getFileBase64());
            blob.uploadFromByteArray(decodedBytes,0,decodedBytes.length);

            resultService = "OK";

        }catch (Exception e){
            resultService = e.getMessage();
        }
        ArchivosServidor archivosServidor=mapearEntidad(archivoServidorDTO);

        ArchivosServidor archivosServidorNuevo= archivoServidorRepository.save(archivosServidor);

        ArchivoServidorDTO archivoServidorDTORespuesta=mapearDTO(archivosServidorNuevo);

        return archivoServidorDTORespuesta;
    }

    private DesktopEmpleadoTipoDoc mapearEntidadEmpleadoTipoDoc(EmpleadoTipoDocDTO empleadoTipoDocDTO){
        DesktopEmpleadoTipoDoc empleadoTipoDoc=new DesktopEmpleadoTipoDoc();

        empleadoTipoDoc.setTipoArchivo(empleadoTipoDocDTO.getTipoArchivo());
        empleadoTipoDoc.setRuta(empleadoTipoDocDTO.getRuta());
        empleadoTipoDoc.setExtension(empleadoTipoDocDTO.getExtension());
        empleadoTipoDoc.setNombreArchivo(empleadoTipoDocDTO.getNombreArchivo());
        empleadoTipoDoc.setDni(empleadoTipoDocDTO.getDni());

        return empleadoTipoDoc;

    }

    private EmpleadoTipoDocDTO mapearDTOEmpleadoTipoDoc(DesktopEmpleadoTipoDoc empleadoTipoDoc){

        EmpleadoTipoDocDTO empleadoTipoDocDTO=new EmpleadoTipoDocDTO();

        empleadoTipoDocDTO.setId_empleado_tipo_doc(empleadoTipoDoc.getId_empleado_tipo_doc());
        empleadoTipoDocDTO.setTipoArchivo(empleadoTipoDoc.getTipoArchivo());
        empleadoTipoDocDTO.setNombreArchivo(empleadoTipoDoc.getNombreArchivo());
        empleadoTipoDocDTO.setExtension(empleadoTipoDoc.getExtension());
        empleadoTipoDocDTO.setDni(empleadoTipoDoc.getDni());
        empleadoTipoDocDTO.setRuta(empleadoTipoDoc.getRuta());

        return empleadoTipoDocDTO;
    }

    private DesktopEmpleadoTipoDoc actualizarEmpleado(EmpleadoTipoDocDTO empleadoTipoDocDTO, DesktopEmpleadoTipoDoc empleadoTipoDoc){

        empleadoTipoDoc.setRuta("EMPLEADO-DNI-"+empleadoTipoDocDTO.getDni()+"/"+empleadoTipoDocDTO.getNombreArchivo());
        empleadoTipoDoc.setTipoArchivo(empleadoTipoDocDTO.getTipoArchivo());
       // empleadoTipoDoc.setRuta(empleadoTipoDocDTO.getRuta());
        empleadoTipoDoc.setExtension(empleadoTipoDocDTO.getExtension());
        empleadoTipoDoc.setNombreArchivo(empleadoTipoDocDTO.getNombreArchivo());
        empleadoTipoDoc.setDni(empleadoTipoDocDTO.getDni());
        usarActualizarArchivoEmpleado(empleadoTipoDocDTO);
        return empleadoTipoDoc;

    }

    public EmpleadoTipoDocDTO usarActualizarArchivoEmpleado(EmpleadoTipoDocDTO empleadoTipoDocDTO) {
        String resultService ="";
        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        empleadoTipoDocDTO.setRuta("EMPLEADO-DNI-"+empleadoTipoDocDTO.getDni()+"/"+empleadoTipoDocDTO.getNombreArchivo());

        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);

            CloudBlob blob;
            blob = container.getBlockBlobReference(empleadoTipoDocDTO.getRuta());
            byte[] decodedBytes = Base64.getDecoder().decode(empleadoTipoDocDTO.getBase64());
            blob.uploadFromByteArray(decodedBytes,0,decodedBytes.length);

            resultService = "OK";

        }catch (Exception e){
            resultService = e.getMessage();
        }
        DesktopEmpleadoTipoDoc empleadoTipoDoc=mapearEntidadEmpleadoTipoDoc(empleadoTipoDocDTO);

        //ArchivosServidor archivosServidorNuevo= archivoServidorRepository.save(archivosServidor);

        EmpleadoTipoDocDTO archivoServidorDTORespuesta=mapearDTOEmpleadoTipoDoc(empleadoTipoDoc);

        return archivoServidorDTORespuesta;
    }

    public String archivoAbase64(String path) throws IOException {

        byte[] bytes=Files.readAllBytes(Paths.get("C:\\Users\\josue\\Documents\\CARGA MASIVA\\Avance de lonardo\\"+path));
        String base64Dtring=Base64.getEncoder().encodeToString(bytes);
        return base64Dtring;
    }

    public ArchivoServidorDTO usarActualizarArchivo(ArchivoServidorDTO archivoServidorDTO) {
        String resultService ="";
        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        archivoServidorDTO.setRutaArchivo("DNI-"+archivoServidorDTO.getDni()+"/HC-"+archivoServidorDTO.getHistoriaClinica()+"/"+archivoServidorDTO.getNombreArchivo());

        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);

            CloudBlob blob;
            blob = container.getBlockBlobReference(archivoServidorDTO.getRutaArchivo());
            byte[] decodedBytes = Base64.getDecoder().decode(archivoServidorDTO.getFileBase64());
            blob.uploadFromByteArray(decodedBytes,0,decodedBytes.length);

            resultService = "OK";

        }catch (Exception e){
            resultService = e.getMessage();
        }
        ArchivosServidor archivosServidor=mapearEntidad(archivoServidorDTO);

        //ArchivosServidor archivosServidorNuevo= archivoServidorRepository.save(archivosServidor);

        ArchivoServidorDTO archivoServidorDTORespuesta=mapearDTO(archivosServidor);

        return archivoServidorDTORespuesta;
    }

    @Override
    public List<ArchivoServidorDTO> listadoArchivoServidor() {
        List<ArchivosServidor> listadoArchivoServidor= archivoServidorRepository.findAll();

        return listadoArchivoServidor.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<ArchivoServidorDTO> listadoArchivoPorHC(String hc, long idUser) {
        List<ArchivosServidor> listaArchivos=archivoServidorRepository.listadoArchivosPorHCYIdUser(hc,idUser).orElseThrow();

        return listaArchivos.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ArchivoServidorDTO obtenerArchivoServidorPorID(long id) {
        ArchivosServidor archivosServidor=archivoServidorRepository.
                findById(id).orElseThrow(()->new ResourceNotFoundException("ArchivoServidor","id_archivo_servidor",id));
      //  System.out.println("El archivo de base 64 : ");
        String resultService ="";
        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        String  base64File="";
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);
            final String NOMBRE_ARCHIVO_TEMP = "temp.pdf";

            CloudBlockBlob blockBlob = container.getBlockBlobReference(archivosServidor.getRutaArchivo());
            File file = new File(NOMBRE_ARCHIVO_TEMP);
            blockBlob.downloadToFile(file.getAbsolutePath());
            byte[] fileContent = Files.readAllBytes(file.toPath());
            base64File = Base64.getEncoder().encodeToString(fileContent);
          //  System.out.println("El archivo de base 64 : "+base64File);
            resultService = "Download success!!!";

        }catch (Exception e){
            resultService = e.getMessage();
        }
        ArchivoServidorDTO archivoServidorDTO=mapearDTO(archivosServidor);
        archivoServidorDTO.setFileBase64(base64File);
        return archivoServidorDTO;
    }

    @Override
    public ArchivoServidorDTO actualizarArchivoServidor(ArchivoServidorDTO archivoServidorDTO, long id) {
        ArchivosServidor archivosServidor=archivoServidorRepository.
                findById(id).orElseThrow(()->new ResourceNotFoundException("ArchivoServidor","id_archivo_servidor",id));
     //   System.out.println("el valor del archivo servidor dentro de la funcion actualizarArchivoServidor"+archivosServidor);
        ArchivosServidor archivosServidorActualizacion=archivoServidorRepository.save(actualizarArchivoServidorEntidad(archivoServidorDTO,archivosServidor));

        return mapearDTO(archivosServidorActualizacion);
    }


    private EmpleadoTipoDocDTO actualizarArchivoServidorEmpleado(EmpleadoTipoDocDTO empleadoTipoDocDTO, long id){
        DesktopEmpleadoTipoDoc empleadoTipoDoc=desktopEmpleadoTipoDocRepository.findById(id).orElseThrow();

        DesktopEmpleadoTipoDoc empleadoTipoDocActualizado=desktopEmpleadoTipoDocRepository.save(actualizarEmpleado(empleadoTipoDocDTO,empleadoTipoDoc));

        return mapearDTOEmpleadoTipoDoc(empleadoTipoDocActualizado);
    }

    @Override
    public void eliminarArchivoServidor(long id) {
        ArchivosServidor archivosServidor= archivoServidorRepository.
                findById(id).orElseThrow(()->new ResourceNotFoundException("ArchivoServidor","id_archivo_servidor",id));
        eliminarArchivo(archivosServidor.getHistoriaClinica(), archivosServidor.getId_tipo_archivo());
        archivoServidorRepository.delete(archivosServidor);
    }

    private ArchivoServidorDTO mapearDTO(ArchivosServidor archivosServidor){
        ArchivoServidorDTO archivoServidorDTO=new ArchivoServidorDTO();

        archivoServidorDTO.setId(archivosServidor.getId());
        archivoServidorDTO.setHistoriaClinica(archivosServidor.getHistoriaClinica());
        archivoServidorDTO.setOrden(archivosServidor.getOrden());
        archivoServidorDTO.setNombreArchivo(archivosServidor.getNombre());
        archivoServidorDTO.setServidor(archivosServidor.getServidor());
        archivoServidorDTO.setRutaArchivo(archivosServidor.getRutaArchivo());
        archivoServidorDTO.setEstado(archivosServidor.getEstado());
        archivoServidorDTO.setFechaRegistro(archivosServidor.getFechaRegistro());
        archivoServidorDTO.setUserRegistro(archivosServidor.getUserRegistro());
        archivoServidorDTO.setUserActualizacion(archivosServidor.getUserActualizacion());
        archivoServidorDTO.setUserActualizacion(archivosServidor.getUserActualizacion());
        archivoServidorDTO.setId_tipo_archivo(archivosServidor.getId_tipo_archivo());

        return archivoServidorDTO;
    }

    private ArchivosServidor mapearEntidad(ArchivoServidorDTO archivoServidorDTO){
            ArchivosServidor archivosServidor=new ArchivosServidor();
        //System.out.println("Lo que llego a entidad es: "+archivoServidorDTO);
       //     TipoArchivo tipoArchivo= tipoArchivoRepository.findById(archivoServidorDTO.getId_tipo_archivo()).
         //           orElseThrow(()-> new ResourceNotFoundException("Tipo Archivo","ID tipo archivo",archivoServidorDTO.getId_tipo_archivo()));
        archivosServidor.setNombre(archivoServidorDTO.getNombreArchivo());
        archivosServidor.setHistoriaClinica(archivoServidorDTO.getHistoriaClinica());
        archivosServidor.setOrden(archivoServidorDTO.getOrden());
        archivosServidor.setServidor(archivoServidorDTO.getServidor());
        archivosServidor.setRutaArchivo(archivoServidorDTO.getRutaArchivo());
        archivosServidor.setEstado(archivoServidorDTO.getEstado());
        archivosServidor.setFechaRegistro(archivoServidorDTO.getFechaRegistro());
        archivosServidor.setUserRegistro(archivoServidorDTO.getUserRegistro());
        archivosServidor.setUserActualizacion(archivoServidorDTO.getUserActualizacion());
        archivosServidor.setUserActualizacion(archivoServidorDTO.getUserActualizacion());
        archivosServidor.setId_tipo_archivo(archivoServidorDTO.getId_tipo_archivo());

        return archivosServidor;
    }

    private ArchivosServidor actualizarArchivoServidorEntidad(ArchivoServidorDTO archivoServidorDTO,ArchivosServidor archivosServidor){
        //TipoArchivo tipoArchivo= tipoArchivoRepository.findById(archivoServidorDTO.getId_tipo_archivo()).
          //      orElseThrow(()-> new ResourceNotFoundException("Tipo archivo","ID tipo archivo",archivoServidorDTO.getId_tipo_archivo()));
        archivosServidor.setNombre(archivoServidorDTO.getNombreArchivo());
        archivosServidor.setHistoriaClinica(archivosServidor.getHistoriaClinica());
        archivosServidor.setOrden(archivoServidorDTO.getOrden());
        archivosServidor.setServidor(archivoServidorDTO.getServidor());
        archivosServidor.setRutaArchivo("DNI-"+archivoServidorDTO.getDni()+"/HC-"+archivoServidorDTO.getHistoriaClinica()+"/"+archivoServidorDTO.getNombreArchivo());
        //archivosServidor.setRutaArchivo(archivoServidorDTO.getRutaArchivo());
        archivosServidor.setEstado(archivoServidorDTO.getEstado());
        archivosServidor.setFechaRegistro(archivoServidorDTO.getFechaRegistro());
        archivosServidor.setUserRegistro(archivoServidorDTO.getUserRegistro());
        archivosServidor.setFechaActualizacion(archivoServidorDTO.getFechaActualizacion());
        archivosServidor.setUserActualizacion(archivoServidorDTO.getUserActualizacion());
        archivosServidor.setId_tipo_archivo(archivoServidorDTO.getId_tipo_archivo());

      ArchivoServidorDTO  archivoServidorDTO2=usarActualizarArchivo(archivoServidorDTO);

        return archivosServidor;
    }
    public ArchivoServidorDTO eliminarArchivo(String hc, long ta) {
        ArchivosServidor archivosServidor=archivoServidorRepository.detalleArchivoServidor(hc,ta).
                orElseThrow();
      //  System.out.println("El archivo de base 64 : ");
        String resultService ="";
        String storageConnectionAzure="DefaultEndpointsProtocol=https;AccountName=fileshm;AccountKey=ATV4bMeYq3Ie5RbJO5rug14qJFXlx4fWeFqXsdUq4xQqjvZTNu9CdJGBcyxEFo+1tVnEsDckzIGV+AStoqla/g==;EndpointSuffix=core.windows.net";
        String nameContainer="files1";
        String  base64File="";
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);
            final String NOMBRE_ARCHIVO_TEMP = "temp.pdf";

            CloudBlockBlob blockBlob = container.getBlockBlobReference(archivosServidor.getRutaArchivo());
            blockBlob.delete();


        }catch (Exception e){
            resultService = e.getMessage();
        }
        ArchivoServidorDTO archivoServidorDTO=mapearDTO(archivosServidor);
        archivoServidorDTO.setFileBase64(base64File);

        return archivoServidorDTO;
    }
}
