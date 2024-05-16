package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.TipoArchivoAsignadoDTO;
import backendhm.serviciosRest.models.azure.entity.Rol;
import backendhm.serviciosRest.models.azure.entity.TipoArchivoAsignado;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IRolRepository;
import backendhm.serviciosRest.models.azure.repository.ITipoArchivoAsignadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoArchivoAsignadoServiceImpl implements ITipoArchivoAsignadoService{

    @Autowired
    private ITipoArchivoAsignadoRepository tipoArchivoAsignadoRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public TipoArchivoAsignadoDTO crearTipoArchivoAsignado(TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO) {
        TipoArchivoAsignado tipoArchivoAsignado=mapearEntidad(tipoArchivoAsignadoDTO);

        TipoArchivoAsignado nuevoTipoArchivoAsignado=tipoArchivoAsignadoRepository.save(tipoArchivoAsignado);
        TipoArchivoAsignadoDTO tipoArchivoAsignadoDTORespuesta=mapearDTO(nuevoTipoArchivoAsignado);

        return tipoArchivoAsignadoDTORespuesta;
    }

    @Override
    public List<TipoArchivoAsignadoDTO> listadoTipoArchivoAsignado() {
        List<TipoArchivoAsignado> listaTipoArchivoAsignado=tipoArchivoAsignadoRepository.findAll();
        return listaTipoArchivoAsignado.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<TipoArchivoAsignadoDTO> listadoTipoArchivoAsignadoPorIDROL(long idRol) {
        List<TipoArchivoAsignado> listadoTipoArchivoAsignado=tipoArchivoAsignadoRepository.listadoTipoArchivoAsignadoPoridRol(idRol).orElseThrow();
        return listadoTipoArchivoAsignado.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public TipoArchivoAsignadoDTO obtenerTipoArchivoAsignadoPorIDTipoAsignado(long id) {
        TipoArchivoAsignado listadoTipoArchivoAsignado=tipoArchivoAsignadoRepository.findById(id).orElseThrow();
        return mapearDTO(listadoTipoArchivoAsignado);
    }

    @Override
    public TipoArchivoAsignadoDTO actualizarTipoArchivoAsignado(TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO, long id) {
        TipoArchivoAsignado tipoArchivoAsignado=tipoArchivoAsignadoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Tipo Archivo Asignado","id",id));

        TipoArchivoAsignado tipoArchivoAsignadoActual=tipoArchivoAsignadoRepository.save(actualizarTipoArchivoAsignadoEntidad(tipoArchivoAsignadoDTO,tipoArchivoAsignado));
        return mapearDTO(tipoArchivoAsignadoActual);
    }

    @Override
    public void eliminarTipoArchivoAsignado(long id) {
        TipoArchivoAsignado tipoArchivoAsignado=tipoArchivoAsignadoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Tipo Archivo Asignado","id",id));
        tipoArchivoAsignadoRepository.delete(tipoArchivoAsignado);
    }

    private TipoArchivoAsignadoDTO mapearDTO(TipoArchivoAsignado tipoArchivoAsignado){
        TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO=new TipoArchivoAsignadoDTO();
        Rol rol=rolRepository.findById(tipoArchivoAsignado.getIdRol()).orElseThrow();
        tipoArchivoAsignadoDTO.setIdTipoArchivoAsignado(tipoArchivoAsignado.getIdTipoArchivoAsignado());
        tipoArchivoAsignadoDTO.setIdRol(tipoArchivoAsignado.getIdRol());
        tipoArchivoAsignadoDTO.setIdTipoArchivoAsignar(tipoArchivoAsignado.getIdTipoArchivoAsignar());
        tipoArchivoAsignadoDTO.setEstado(tipoArchivoAsignado.getEstado());
        tipoArchivoAsignadoDTO.setFechaRegistro(tipoArchivoAsignado.getFechaRegistro());
        tipoArchivoAsignadoDTO.setUserRegistro(tipoArchivoAsignado.getUserRegistro());
        tipoArchivoAsignadoDTO.setFechaActualizacion(tipoArchivoAsignado.getFechaActualizacion());
        tipoArchivoAsignadoDTO.setUserActualizacion(tipoArchivoAsignado.getUserActualizacion());
        tipoArchivoAsignadoDTO.setRol(rol.getNombre());

        return tipoArchivoAsignadoDTO;
    }

    private TipoArchivoAsignado mapearEntidad(TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO){
        TipoArchivoAsignado tipoArchivoAsignado=new TipoArchivoAsignado();

        tipoArchivoAsignado.setIdRol(tipoArchivoAsignadoDTO.getIdRol());
        tipoArchivoAsignado.setIdTipoArchivoAsignar(tipoArchivoAsignadoDTO.getIdTipoArchivoAsignar());
        tipoArchivoAsignado.setEstado(tipoArchivoAsignadoDTO.getEstado());
        tipoArchivoAsignado.setFechaRegistro(tipoArchivoAsignadoDTO.getFechaRegistro());
        tipoArchivoAsignado.setUserRegistro(tipoArchivoAsignadoDTO.getUserRegistro());
        tipoArchivoAsignado.setFechaActualizacion(tipoArchivoAsignadoDTO.getFechaActualizacion());
        tipoArchivoAsignado.setUserActualizacion(tipoArchivoAsignadoDTO.getUserActualizacion());

        return tipoArchivoAsignado;
    }

    private TipoArchivoAsignado actualizarTipoArchivoAsignadoEntidad(TipoArchivoAsignadoDTO tipoArchivoAsignadoDTO,TipoArchivoAsignado tipoArchivoAsignado){

        tipoArchivoAsignado.setIdRol(tipoArchivoAsignadoDTO.getIdRol());
        tipoArchivoAsignado.setIdTipoArchivoAsignar(tipoArchivoAsignadoDTO.getIdTipoArchivoAsignar());
        tipoArchivoAsignado.setEstado(tipoArchivoAsignadoDTO.getEstado());
        tipoArchivoAsignado.setFechaRegistro(tipoArchivoAsignadoDTO.getFechaRegistro());
        tipoArchivoAsignado.setUserRegistro(tipoArchivoAsignadoDTO.getUserRegistro());
        tipoArchivoAsignado.setFechaActualizacion(tipoArchivoAsignadoDTO.getFechaActualizacion());
        tipoArchivoAsignado.setUserActualizacion(tipoArchivoAsignadoDTO.getUserActualizacion());

        return tipoArchivoAsignado;
    }
}
