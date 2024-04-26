package backendhm.serviciosRest.models.azure.services.combobox;

import backendhm.serviciosRest.models.azure.dtos.combobox.DepartamentoDTO;

import java.util.List;

public interface IDepartamentoService {
    DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO);

    List<DepartamentoDTO> listadoDepartamentos();
    DepartamentoDTO obtenerDepartamentoPorID(String id);
    DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, String id);
    void eliminarDepartamento(String id);
}
