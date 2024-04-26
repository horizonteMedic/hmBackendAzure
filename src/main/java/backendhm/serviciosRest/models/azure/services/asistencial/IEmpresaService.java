package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.EmpresaDTO;

import java.util.List;

public interface IEmpresaService {
    EmpresaDTO crearEmpresa(EmpresaDTO empresaDTO);
    List<EmpresaDTO> listadoEmpresas();
    EmpresaDTO obtenerEmpresaPorRuc(String ruc);
    EmpresaDTO actualizarEmpresa(EmpresaDTO empresaDTO, String ruc);
    void eliminarEmpresa(String ruc);
}
