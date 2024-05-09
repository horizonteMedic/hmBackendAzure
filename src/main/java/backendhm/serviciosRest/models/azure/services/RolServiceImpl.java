package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.RolDTO;
import backendhm.serviciosRest.models.azure.entity.Rol;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public RolDTO crearRol(RolDTO rolDTO) {
        Rol rol=mapearEntidad(rolDTO);

        Rol nuevoRol=rolRepository.save(rol);
        RolDTO rolRespuesta=mapearDTO(nuevoRol);

        return rolRespuesta;
    }

    @Override
    public List<RolDTO> listadoRol() {
        List<Rol> listaRoles=rolRepository.findAll();
        return listaRoles.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<RolDTO> listadoRolesHabilitados() {
        List<Rol> listadoRoles=rolRepository.listadoRolesHabilitados().orElseThrow();
        return listadoRoles.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<RolDTO> listadoRolesIdUser(long idUser) {
        List<Rol> listadoRoles=rolRepository.listadoRolesPorIdUser(idUser).orElseThrow();
        return listadoRoles.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public RolDTO obtenerRolPorID(long id) {
        Rol rol=rolRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rol","id",id));
        return mapearDTO(rol);
    }

    @Override
    public RolDTO actualizarRol(RolDTO rolDTO, long id) {
        Rol rol=rolRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rol","id",id));

        Rol rolActual=rolRepository.save(actualizarEmpleadoEntidad(rolDTO,rol));
        return mapearDTO(rolActual);
    }

    @Override
    public void eliminarRol(long id) {
        Rol rol=rolRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rol","id",id));
        rolRepository.delete(rol);
    }

    private RolDTO mapearDTO(Rol rol){
        RolDTO rolDTO=new RolDTO();
        rolDTO.setIdRol(rol.getIdRol());
        rolDTO.setNombre(rol.getNombre());
        rolDTO.setEstado(rol.getEstado());
        rolDTO.setFechaRegistro(rol.getFechaRegistro());
        rolDTO.setUserRegistro(rol.getUserRegistro());
        rolDTO.setFechaActualizacion(rol.getFechaActualizacion());
        rolDTO.setUserActualizacion(rol.getUserActualizacion());
        rolDTO.setDescripcion(rol.getDescripcion());

        return rolDTO;
    }

    private Rol mapearEntidad(RolDTO rolDTO){
        Rol rol=new Rol();

        rol.setNombre(rolDTO.getNombre());
        rol.setEstado(rolDTO.getEstado());
        rol.setFechaRegistro(rolDTO.getFechaRegistro());
        rol.setUserRegistro(rolDTO.getUserRegistro());
        rol.setFechaActualizacion(rolDTO.getFechaActualizacion());
        rol.setUserActualizacion(rolDTO.getUserActualizacion());
        rol.setDescripcion(rolDTO.getDescripcion());

        return rol;
    }
    private Rol actualizarEmpleadoEntidad(RolDTO rolDTO,Rol rol){
        rol.setNombre(rolDTO.getNombre());
        rol.setEstado(rolDTO.getEstado());
        rol.setFechaRegistro(rolDTO.getFechaRegistro());
        rol.setUserRegistro(rolDTO.getUserRegistro());
        rol.setFechaActualizacion(rolDTO.getFechaActualizacion());
        rol.setUserActualizacion(rolDTO.getUserActualizacion());
        rol.setDescripcion(rolDTO.getDescripcion());
        return rol;
    }



}
