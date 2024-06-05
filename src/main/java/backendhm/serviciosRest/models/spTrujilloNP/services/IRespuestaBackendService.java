package backendhm.serviciosRest.models.spTrujilloNP.services;

import backendhm.serviciosRest.models.spTrujilloNP.dto.*;

import java.util.List;

public interface IRespuestaBackendService {
    RespuestaBackendDTO registrarDatosPaciente(RequestDatosPacienteDTO requestDatosPacienteDTO);

    RespuestaBackendDTO registroHistoriaClinica(RequestNOrdenOcupacionalDTO requestNOrdenOcupacionalDTO);
    RespuestaBackendDTO busquedaDniPorNOrden(long nOrden);
    RespuestaBackendDTO busquedaDniPorReferencia(String referencia);

    List<ResponseMatrizDTO> listadoMatrizAdministrativa(RequesMatrizDTO requesMatrizDTO);

}
