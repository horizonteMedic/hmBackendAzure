package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.PrivilegioRolDTO;

import java.util.List;

public interface IPrivilegioRolService {
    PrivilegioRolDTO crearPrivilegioRol(PrivilegioRolDTO privilegioRolDTO);
    List<PrivilegioRolDTO> listadoPrivilegioRol();
    PrivilegioRolDTO obtenerPrivilegioRolPorID(long id);
    PrivilegioRolDTO actualizarPrivilegioRol(PrivilegioRolDTO privilegioRolDTO, long id);
    void eliminarPrivilegioRol(long id);
}
