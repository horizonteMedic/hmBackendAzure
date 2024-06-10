package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ContratoProtocoloDTO;

import java.util.List;

public interface IContratoProtocoloService {
    ContratoProtocoloDTO crearContratoProtocolo(ContratoProtocoloDTO contratoProtocoloDTO);
    List<ContratoProtocoloDTO> listadoContratosProtocolos();
    ContratoProtocoloDTO obtenerContratoProtocoloPorID(long id);
    ContratoProtocoloDTO actualizarContratoProtocolo(ContratoProtocoloDTO contratoProtocoloDTO, long id);
    void eliminarContratoProtocolo(long id);
}
