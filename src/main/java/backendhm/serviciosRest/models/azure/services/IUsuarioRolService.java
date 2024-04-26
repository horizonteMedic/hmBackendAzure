package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.UsuarioRolDTO;

import java.util.List;

public interface IUsuarioRolService {
    UsuarioRolDTO crearUsuarioRol(UsuarioRolDTO usuarioRolDTO);
    List<UsuarioRolDTO> listadoUsuarioRol();
    UsuarioRolDTO obtenerUsuarioRolPorID(long id);
    UsuarioRolDTO actualizarUsuarioRolDTO(UsuarioRolDTO usuarioRolDTO, long id);
    void eliminarUsuarioRolDTO(long id);
}
