package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.BackendEntityReservaListaDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.CitaOcupacionalDTO;

import java.util.List;

public interface ICitaOcupacionalService {
    CitaOcupacionalDTO crearCitaOcupacional(CitaOcupacionalDTO citaOcupacionalDTO);
    List<CitaOcupacionalDTO> listadoCitaOcupacional();
    List<BackendEntityReservaListaDTO> listadoReserevaPorUsername(String nameUser);
    CitaOcupacionalDTO obtenerCitaOcupacionalPorID(long id);
    CitaOcupacionalDTO actualizarCitaOcupacional(CitaOcupacionalDTO citaOcupacionalDTO, long id);
    void eliminarCitaOcupacional(long id);
}
