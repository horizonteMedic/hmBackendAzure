package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ProtocoloDTO;

import java.util.List;

public interface IProtocoloService {
    ProtocoloDTO crearProtocolo(ProtocoloDTO protocoloDTO);
    List<ProtocoloDTO> listadoProtocolos();
    ProtocoloDTO obtenerProtocoloPorID(long id);
    ProtocoloDTO actualizarProtocolo(ProtocoloDTO protocoloDTO, long id);
    void eliminarProtocolo(long id);
}
