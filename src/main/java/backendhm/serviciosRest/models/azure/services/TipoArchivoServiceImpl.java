package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.TipoArchivoDTO;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.sistemasArchivos.ITipoArchivoRepository;
import backendhm.serviciosRest.models.azure.entity.TipoArchivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoArchivoServiceImpl implements ITipoArchivoService{

    @Autowired
    ITipoArchivoRepository archivoRepository;


    @Override
    public TipoArchivoDTO crearTipoArchivo(TipoArchivoDTO tipoArchivoDTO) {
        TipoArchivo tipoArchivo=mapearEntidad(tipoArchivoDTO);

        TipoArchivo nuevoTipoArchivo=archivoRepository.save(tipoArchivo);
        TipoArchivoDTO tipoArchivoRespuesta=mapearDTO(nuevoTipoArchivo);

        return tipoArchivoRespuesta;
    }

    @Override
    public List<TipoArchivoDTO> listadoTipoArchivo() {
        List<TipoArchivo> listaTipoArchivo=archivoRepository.findAll();
        return listaTipoArchivo.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<TipoArchivoDTO> listadoTipoArchivoHabilitados() {
        List<TipoArchivo> listadoTipoArchivoHabilitado=archivoRepository.listadoTipoArchivosHabilitados().orElseThrow();
        return listadoTipoArchivoHabilitado.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public TipoArchivoDTO obtenerTipoArchivoPorID(long id) {
        TipoArchivo tipoArchivo=archivoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Tipo archivo","id tipo archivo",id));
        return mapearDTO(tipoArchivo);
    }

    @Override
    public TipoArchivoDTO actualizarTipoArchivo(TipoArchivoDTO tipoArchivoDTO, long id) {
        TipoArchivo tipoArchivo=archivoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Tipo Archivo","id Tipo archivo",id));

        TipoArchivo tipoArchivoActual=archivoRepository.save(actualizarTipoArchivoEntidad(tipoArchivoDTO,tipoArchivo));
        return mapearDTO(tipoArchivoActual);
    }

    @Override
    public void eliminarTipoArchivo(long id) {
        TipoArchivo tipoArchivo=archivoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Tipo Archivo","id Tipo archivo",id));
        archivoRepository.delete(tipoArchivo);
    }

    private TipoArchivoDTO mapearDTO(TipoArchivo tipoArchivo){
        TipoArchivoDTO tipoArchivoDTO=new TipoArchivoDTO();
        tipoArchivoDTO.setId(tipoArchivo.getId());
        tipoArchivoDTO.setNombre(tipoArchivo.getNombre());
        tipoArchivoDTO.setExtension(tipoArchivo.getExtension());
        tipoArchivoDTO.setColor(tipoArchivo.getColor());
        tipoArchivoDTO.setCodigo(tipoArchivo.getCodigo());
        tipoArchivoDTO.setEstado(tipoArchivo.getEstado());
        tipoArchivoDTO.setFechaRegistro(tipoArchivo.getFechaRegistro());
        tipoArchivoDTO.setUserRegistro(tipoArchivo.getUserRegistro());
        tipoArchivoDTO.setFechaActualizacion(tipoArchivo.getFechaActualizacion());
        tipoArchivoDTO.setUserActualizacion(tipoArchivo.getUserActualizacion());
        return tipoArchivoDTO;
    }

    private TipoArchivo mapearEntidad(TipoArchivoDTO tipoArchivoDTO){
        TipoArchivo tipoArchivo=new TipoArchivo();

        tipoArchivo.setNombre(tipoArchivoDTO.getNombre());
        tipoArchivo.setColor(tipoArchivoDTO.getColor());
        tipoArchivo.setExtension(tipoArchivoDTO.getExtension());
        tipoArchivo.setCodigo(tipoArchivoDTO.getCodigo());
        tipoArchivo.setEstado(tipoArchivoDTO.getEstado());
        tipoArchivo.setFechaRegistro(tipoArchivoDTO.getFechaRegistro());
        tipoArchivo.setUserRegistro(tipoArchivoDTO.getUserRegistro());
        tipoArchivo.setFechaActualizacion(tipoArchivoDTO.getFechaActualizacion());
        tipoArchivo.setUserActualizacion(tipoArchivoDTO.getUserActualizacion());

        return tipoArchivo;
    }

    private TipoArchivo actualizarTipoArchivoEntidad(TipoArchivoDTO tipoArchivoDTO,TipoArchivo tipoArchivo){
        tipoArchivo.setNombre(tipoArchivoDTO.getNombre());
        tipoArchivo.setColor(tipoArchivoDTO.getColor());
        tipoArchivo.setExtension(tipoArchivoDTO.getExtension());
        tipoArchivo.setCodigo(tipoArchivoDTO.getCodigo());
        tipoArchivo.setEstado(tipoArchivoDTO.getEstado());
        tipoArchivo.setFechaRegistro(tipoArchivoDTO.getFechaRegistro());
        tipoArchivo.setUserRegistro(tipoArchivoDTO.getUserRegistro());
        tipoArchivo.setFechaActualizacion(tipoArchivoDTO.getFechaActualizacion());
        tipoArchivo.setUserActualizacion(tipoArchivoDTO.getUserActualizacion());

        return tipoArchivo;
    }
}
