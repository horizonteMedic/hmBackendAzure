package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.RolDTO;

import java.util.List;

public interface IRolService {
    RolDTO crearRol(RolDTO rolDTO);
    List<RolDTO> listadoRol();
    List<RolDTO> listadoRolesHabilitados();
    List<RolDTO> listadoRolesIdUser(long idUser);

    List<RolDTO> listadoRolesUsername(String userName);

    RolDTO obtenerRolPorID(long id);
    RolDTO actualizarRol(RolDTO rolDTO, long id);
    void eliminarRol(long id);
}
