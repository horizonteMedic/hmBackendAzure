package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import backendhm.serviciosRest.models.azure.repository.parametros.IRespuestaBackendRepository;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.sistemasArchivos.ITipoArchivoRepository;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.ArchivoServidorDTO;
import backendhm.serviciosRest.models.azure.entity.sistemaArchivos.ArchivosServidor;
import backendhm.serviciosRest.models.azure.repository.sistemasArchivos.IArchivoServidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
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
    private ITipoArchivoRepository tipoArchivoRepository;

    @Override
    public ArchivoServidorDTO detalleArchivoServidor(String hc, long ta) {
        ArchivosServidor archivosServidor=archivoServidorRepository.detalleArchivoServidor(hc,ta).
                orElseThrow();
        System.out.println("El archivo de base 64 : ");
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
            System.out.println("El archivo de base 64 : "+base64File);
            resultService = "Download success!!!";

        }catch (Exception e){
            resultService = e.getMessage();
        }
        ArchivoServidorDTO archivoServidorDTO=mapearDTO(archivosServidor);
        archivoServidorDTO.setFileBase64(base64File);

        return archivoServidorDTO;
    }

    @Override
    public RespuestaBackendDTO registrarArchivoOActualizar(ArchivoServidorDTO archivoServidorDTO) {
        RespuestaBackend respuestaBackend=respuestaBackendRepository.existenciaDelArchivo(archivoServidorDTO.getHistoriaClinica(),archivoServidorDTO.getId_tipo_archivo()).orElseThrow();
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
            respuestaBackendDTO.setMensaje("Se actualizo con exito!");
        }

        return respuestaBackendDTO;
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
    public List<ArchivoServidorDTO> listadoArchivoPorHC(String hc) {
        List<ArchivosServidor> listaArchivos=archivoServidorRepository.listadoArchivosPorHC(hc).orElseThrow();

        return listaArchivos.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ArchivoServidorDTO obtenerArchivoServidorPorID(long id) {
        ArchivosServidor archivosServidor=archivoServidorRepository.
                findById(id).orElseThrow(()->new ResourceNotFoundException("ArchivoServidor","id_archivo_servidor",id));
        System.out.println("El archivo de base 64 : ");
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
            System.out.println("El archivo de base 64 : "+base64File);
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
        System.out.println("el valor del archivo servidor dentro de la funcion actualizarArchivoServidor"+archivosServidor);
        ArchivosServidor archivosServidorActualizacion=archivoServidorRepository.save(actualizarArchivoServidorEntidad(archivoServidorDTO,archivosServidor));

        return mapearDTO(archivosServidorActualizacion);
    }

    @Override
    public void eliminarArchivoServidor(long id) {
        ArchivosServidor archivosServidor= archivoServidorRepository.
                findById(id).orElseThrow(()->new ResourceNotFoundException("ArchivoServidor","id_archivo_servidor",id));
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
        archivosServidor.setRutaArchivo(archivoServidorDTO.getRutaArchivo());
        archivosServidor.setEstado(archivoServidorDTO.getEstado());
        archivosServidor.setFechaRegistro(archivoServidorDTO.getFechaRegistro());
        archivosServidor.setUserRegistro(archivoServidorDTO.getUserRegistro());
        archivosServidor.setFechaActualizacion(archivoServidorDTO.getFechaActualizacion());
        archivosServidor.setUserActualizacion(archivoServidorDTO.getUserActualizacion());
        archivosServidor.setId_tipo_archivo(archivoServidorDTO.getId_tipo_archivo());

      ArchivoServidorDTO  archivoServidorDTO2=usarActualizarArchivo(archivoServidorDTO);

        return archivosServidor;
    }

}
