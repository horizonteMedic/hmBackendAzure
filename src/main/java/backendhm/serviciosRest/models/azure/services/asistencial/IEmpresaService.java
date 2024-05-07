package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.EmpresaDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpContDTO;

import java.util.List;

public interface IEmpresaService {
    EmpresaDTO crearEmpresa(EmpresaDTO empresaDTO);
    List<EmpresaDTO> listadoEmpresas();
    List<EmpContDTO> listadocONTPorUsername(String userName, String tipoEmpCont);

    List<EmpContDTO> listadoEmpresaPorUsername(String userName, String tipoEmpCont);
    EmpresaDTO obtenerEmpresaPorRuc(String ruc);
    EmpresaDTO actualizarEmpresa(EmpresaDTO empresaDTO, String ruc);
    void eliminarEmpresa(String ruc);
}
