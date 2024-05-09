package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.entity.OpcionesInterfazPrivilegios;
import backendhm.serviciosRest.models.azure.entity.PrivilegioRol;
import backendhm.serviciosRest.models.azure.entity.Rol;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IOpcionesInterfazPrivilegio;
import backendhm.serviciosRest.models.azure.repository.IPrivilegioRol;
import backendhm.serviciosRest.models.azure.repository.IRolRepository;
import backendhm.serviciosRest.models.azure.dtos.PrivilegioRolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivilegioRolServiceImpl implements IPrivilegioRolService{

    @Autowired
    private IPrivilegioRol privilegioRolRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private IOpcionesInterfazPrivilegio opcionesInterfazPrivilegioRepository;

    @Override
    public PrivilegioRolDTO crearPrivilegioRol(PrivilegioRolDTO privilegioRolDTO) {
        PrivilegioRol privilegioRol=mapearEntidad(privilegioRolDTO);

        PrivilegioRol nuevoPrivilegioRol=privilegioRolRepository.save(privilegioRol);
        PrivilegioRolDTO privilegioRolDTORespuesta=mapearDTO(nuevoPrivilegioRol);

        return privilegioRolDTORespuesta;
    }

    @Override
    public List<PrivilegioRolDTO> listadoPrivilegioRol() {
        List<PrivilegioRol> listaPrivilegioRoles=privilegioRolRepository.findAll();
        return listaPrivilegioRoles.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<PrivilegioRolDTO> listadoPrivilegioRolPorIdRol(long idRol) {
        List<PrivilegioRol> listaPrivilegioRoles=privilegioRolRepository.listadoPrivilegiosRolesPorIdRol(idRol).orElseThrow();
        return listaPrivilegioRoles.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public PrivilegioRolDTO obtenerPrivilegioRolPorID(long id) {
        PrivilegioRol privilegioRol=privilegioRolRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Privielgio Rol","id privilegio rol",id));
        return mapearDTO(privilegioRol);
    }

    @Override
    public PrivilegioRolDTO actualizarPrivilegioRol(PrivilegioRolDTO privilegioRolDTO, long id) {
        PrivilegioRol privilegioRol=privilegioRolRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Privielgio Rol","id privilegio rol",id));

        PrivilegioRol privilegioRolActual=privilegioRolRepository.
                save(actualizarPrivilegioRolEntidad(privilegioRolDTO,privilegioRol));
        return mapearDTO(privilegioRolActual);
    }

    @Override
    public void eliminarPrivilegioRol(long id) {
        PrivilegioRol privilegioRol=privilegioRolRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Privielgio Rol","id privilegio rol",id));
        privilegioRolRepository.delete(privilegioRol);
    }

    private PrivilegioRolDTO mapearDTO(PrivilegioRol privilegioRol){
        PrivilegioRolDTO privilegioRolDTO=new PrivilegioRolDTO();

        privilegioRolDTO.setId(privilegioRol.getId());
        privilegioRolDTO.setDescripcion(privilegioRol.getDescripcion());
        privilegioRolDTO.setEstado(privilegioRol.getEstado());
        privilegioRolDTO.setFechaRegistro(privilegioRol.getFechaRegistro());
        privilegioRolDTO.setUserRegistro(privilegioRol.getUserRegistro());
        privilegioRolDTO.setFechaActualizacion(privilegioRol.getFechaActualizacion());
        privilegioRolDTO.setUserActualizacion(privilegioRol.getUserActualizacion());
        privilegioRolDTO.setId_rol(privilegioRol.getRol().getIdRol());
        privilegioRolDTO.setId_opcion_interfaz(privilegioRol.getOpcionesInterfazPrivilegios().getId());

        return privilegioRolDTO;
    }

    private PrivilegioRol mapearEntidad(PrivilegioRolDTO privilegioRolDTO){
        PrivilegioRol privilegioRol=new PrivilegioRol();
        Rol rol=rolRepository.findById(privilegioRolDTO.getId_rol()).orElseThrow();
        OpcionesInterfazPrivilegios opcionesInterfazPrivilegios=
                opcionesInterfazPrivilegioRepository.findById(privilegioRolDTO.getId_opcion_interfaz()).orElseThrow();
        privilegioRol.setDescripcion(privilegioRolDTO.getDescripcion());
        privilegioRol.setEstado(privilegioRolDTO.getEstado());
        privilegioRol.setFechaRegistro(privilegioRolDTO.getFechaRegistro());
        privilegioRol.setUserRegistro(privilegioRolDTO.getUserRegistro());
        privilegioRol.setFechaActualizacion(privilegioRolDTO.getFechaActualizacion());
        privilegioRol.setUserActualizacion(privilegioRolDTO.getUserActualizacion());
        privilegioRol.setRol(rol);
        privilegioRol.setOpcionesInterfazPrivilegios(opcionesInterfazPrivilegios);

        return privilegioRol;
    }
    private PrivilegioRol actualizarPrivilegioRolEntidad(PrivilegioRolDTO privilegioRolDTO,PrivilegioRol privilegioRol){
        Rol rol=rolRepository.findById(privilegioRolDTO.getId_rol()).orElseThrow();
        OpcionesInterfazPrivilegios opcionesInterfazPrivilegios=
                opcionesInterfazPrivilegioRepository.findById(privilegioRolDTO.getId_opcion_interfaz()).orElseThrow();
        privilegioRol.setDescripcion(privilegioRolDTO.getDescripcion());
        privilegioRol.setEstado(privilegioRolDTO.getEstado());
        privilegioRol.setFechaRegistro(privilegioRolDTO.getFechaRegistro());
        privilegioRol.setUserRegistro(privilegioRolDTO.getUserRegistro());
        privilegioRol.setFechaActualizacion(privilegioRolDTO.getFechaActualizacion());
        privilegioRol.setUserActualizacion(privilegioRolDTO.getUserActualizacion());
        privilegioRol.setRol(rol);
        privilegioRol.setOpcionesInterfazPrivilegios(opcionesInterfazPrivilegios);
        return privilegioRol;
    }

}
