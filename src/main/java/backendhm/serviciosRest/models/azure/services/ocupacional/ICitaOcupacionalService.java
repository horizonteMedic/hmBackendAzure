package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.*;

import java.util.List;

public interface ICitaOcupacionalService {
    CitaOcupacionalDTO crearCitaOcupacional(CitaOcupacionalDTO citaOcupacionalDTO);

    ConsultaReservaDTO consultarReservaDatosPaciente(long dni);
    List<CitaOcupacionalDTO> listadoCitaOcupacional();
    List<BackendEntityReservaListaDTO> listadoReserevaPorUsername(String nameUser);

    List<BackendDetalleReservaDTO> listadoDetalleReservaPorFiltros(RequestDetalleReservaDTO rq);

    CitaOcupacionalDTO obtenerCitaOcupacionalPorID(long id);
    CitaOcupacionalDTO actualizarCitaOcupacional(CitaOcupacionalDTO citaOcupacionalDTO, long id);
    void eliminarCitaOcupacional(long id);
}
