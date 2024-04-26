package backendhm.serviciosRest.models.azure.services.combobox;

import backendhm.serviciosRest.models.azure.dtos.combobox.DistritoDTO;

import java.util.List;

public interface IDistritoService {
    DistritoDTO crearDistrito(DistritoDTO distritoDTO);
    List<DistritoDTO> listadoDistritoPorProvincia(String idProvincia);
    List<DistritoDTO> listadoDistritos();
    DistritoDTO obtenerDistritoPorID(String id);
    DistritoDTO actualizarDistrito(DistritoDTO distritoDTO, String id);
    void eliminarDistrito(String id);
}
