package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ServicioDTO;

import java.util.List;

public interface IServicioService {
    ServicioDTO crearServicio(ServicioDTO servicioDTO);
    List<ServicioDTO> listadoServicios();

    ServicioDTO obtenerServicioPorID(long id);
    ServicioDTO actualizarServicio(ServicioDTO servicioDTO, long id);
    void eliminarServicio(long id);
}
