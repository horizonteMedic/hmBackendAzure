package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.RolAsignadoDTO;
import backendhm.serviciosRest.models.azure.entity.RolAsignado;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IRolAsignadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolAsignadoServiceImpl implements IRolAsignadoService{

    @Autowired
    private IRolAsignadoRepository rolAsignadoRepository;

    @Override
    public RolAsignadoDTO crearRolAsignado(RolAsignadoDTO rolAsignadoDTO) {
        RolAsignado rolAsignado=mapearEntidad(rolAsignadoDTO);

        RolAsignado nuevoRolAsignado=rolAsignadoRepository.save(rolAsignado);
        RolAsignadoDTO rolAsignadoDTORespuesta=mapearDTO(nuevoRolAsignado);

        return rolAsignadoDTORespuesta;
    }

    @Override
    public List<RolAsignadoDTO> listadoRol() {

        List<RolAsignado> listaRolesAsignados=rolAsignadoRepository.findAll();
        return listaRolesAsignados.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<RolAsignadoDTO> listadoRolesPorIDROL(long idRol) {
        List<RolAsignado> listadoRolesAsignados=rolAsignadoRepository.listadoRolesPoridRol(idRol).orElseThrow();
        return listadoRolesAsignados.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public RolAsignadoDTO obtenerRolPorID(long id) {
        RolAsignado listadoRolesAsignados=rolAsignadoRepository.findById(id).orElseThrow();
        return mapearDTO(listadoRolesAsignados);

    }

    @Override
    public RolAsignadoDTO actualizarRol(RolAsignadoDTO rolAsignadoDTO, long id) {
        RolAsignado rolAsignado=rolAsignadoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rol","id",id));

        RolAsignado rolAsignadoActual=rolAsignadoRepository.save(actualizarEmpleadoEntidad(rolAsignadoDTO,rolAsignado));
        return mapearDTO(rolAsignadoActual);
    }

    @Override
    public void eliminarRolAsignado(long id) {
        RolAsignado rolAsignado=rolAsignadoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rol","id",id));
        rolAsignadoRepository.delete(rolAsignado);
    }

    private RolAsignadoDTO mapearDTO(RolAsignado rolAsignado){
        RolAsignadoDTO rolAsignadoDTO=new RolAsignadoDTO();

        rolAsignadoDTO.setIdRolAsignado(rolAsignado.getIdRolAsignado());
        rolAsignadoDTO.setIdRol(rolAsignado.getIdRol());
        rolAsignadoDTO.setEstado(rolAsignado.getEstado());
        rolAsignadoDTO.setFechaRegistro(rolAsignado.getFechaRegistro());
        rolAsignadoDTO.setUserRegistro(rolAsignado.getUserRegistro());
        rolAsignadoDTO.setFechaActualizacion(rolAsignado.getFechaActualizacion());
        rolAsignadoDTO.setUserActualizacion(rolAsignado.getUserActualizacion());
        rolAsignadoDTO.setIdRolAsignar(rolAsignado.getIdRolAsignar());

        return rolAsignadoDTO;
    }

    private RolAsignado mapearEntidad(RolAsignadoDTO rolAsignadoDTO){
        RolAsignado rolAsignado=new RolAsignado();

        rolAsignado.setIdRol(rolAsignadoDTO.getIdRol());
        rolAsignado.setEstado(rolAsignadoDTO.getEstado());
        rolAsignado.setFechaRegistro(rolAsignadoDTO.getFechaRegistro());
        rolAsignado.setUserRegistro(rolAsignadoDTO.getUserRegistro());
        rolAsignado.setFechaActualizacion(rolAsignadoDTO.getFechaActualizacion());
        rolAsignado.setUserActualizacion(rolAsignadoDTO.getUserActualizacion());
        rolAsignado.setIdRolAsignar(rolAsignadoDTO.getIdRolAsignar());
        return rolAsignado;
    }

    private RolAsignado actualizarEmpleadoEntidad(RolAsignadoDTO rolAsignadoDTO,RolAsignado rolAsignado){
        rolAsignado.setIdRol(rolAsignadoDTO.getIdRol());
        rolAsignado.setEstado(rolAsignadoDTO.getEstado());
        rolAsignado.setFechaRegistro(rolAsignadoDTO.getFechaRegistro());
        rolAsignado.setUserRegistro(rolAsignadoDTO.getUserRegistro());
        rolAsignado.setFechaActualizacion(rolAsignadoDTO.getFechaActualizacion());
        rolAsignado.setUserActualizacion(rolAsignadoDTO.getUserActualizacion());
        rolAsignado.setIdRolAsignar(rolAsignadoDTO.getIdRolAsignar());

        return rolAsignado;
    }
}
