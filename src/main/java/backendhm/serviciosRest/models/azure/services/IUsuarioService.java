package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDTO> listadoUsuario();

    List<UsuarioDTO> listadoUsuarioPorIDEmpleado(long idEmpleado);
    UsuarioDTO obtenerUsuarioPorID(long id);
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, long id);
    void eliminarUsuario(long id);
}
