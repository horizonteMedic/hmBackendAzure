package backendhm.serviciosRest.models.spTrujilloNP.services;

import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestNOrdenOcupacionalDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RespuestaBackendDTO;

public interface IRespuestaBackendService {
    RespuestaBackendDTO registrarDatosPaciente(RequestDatosPacienteDTO requestDatosPacienteDTO);

    RespuestaBackendDTO registroHistoriaClinica(RequestNOrdenOcupacionalDTO requestNOrdenOcupacionalDTO);
    RespuestaBackendDTO busquedaDniPorNOrden(long nOrden);
    RespuestaBackendDTO busquedaDniPorReferencia(String referencia);


}
