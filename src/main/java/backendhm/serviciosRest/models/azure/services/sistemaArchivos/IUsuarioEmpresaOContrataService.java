package backendhm.serviciosRest.models.azure.services.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpContDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.UsuarioEmpresaOContraTaDTO;

import java.util.List;

public interface IUsuarioEmpresaOContrataService {


    List<EmpContDTO> listadoEmpOCont(String descripcion);
    UsuarioEmpresaOContraTaDTO Crear(UsuarioEmpresaOContraTaDTO usuarioEOCDto);
    List<UsuarioEmpresaOContraTaDTO> listadoUserEOC();
    UsuarioEmpresaOContraTaDTO obtenerUserEOCPorID(long id);
    UsuarioEmpresaOContraTaDTO actualizarUserEOC(UsuarioEmpresaOContraTaDTO usuarioEOCDto, long id);
    void eliminarUserEOC(long id);
}
