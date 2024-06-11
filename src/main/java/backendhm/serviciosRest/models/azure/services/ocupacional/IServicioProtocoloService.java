package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ServicioProtocoloDTO;

import java.util.List;

public interface IServicioProtocoloService {
    ServicioProtocoloDTO crearServiciosProtocolo(ServicioProtocoloDTO servicioProtocoloDTO);
    List<ServicioProtocoloDTO> listadoServiciosProtocolos();
    List<ServicioProtocoloDTO> busquedaServicioProtocoloPorIDProtocolo(long idProtocolo);
    ServicioProtocoloDTO obtenerServicioProtocoloPorID(long id);
    ServicioProtocoloDTO actualizarServiciProtocolo(ServicioProtocoloDTO servicioProtocoloDTO, long id);
    void eliminarServicioProtocolo(long id);
}
