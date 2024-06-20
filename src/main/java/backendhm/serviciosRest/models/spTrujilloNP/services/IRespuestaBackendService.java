package backendhm.serviciosRest.models.spTrujilloNP.services;

import backendhm.serviciosRest.models.spTrujilloNP.dto.*;

import java.util.List;

public interface IRespuestaBackendService {
    RespuestaBackendDTO registrarDatosPaciente(RequestDatosPacienteDTO requestDatosPacienteDTO);

    RequestDatosPacienteDTO busquedaDatosPacienteDNI(long dni);

    RespuestaBackendDTO registroHistoriaClinica(RequestNOrdenOcupacionalDTO requestNOrdenOcupacionalDTO);
    RespuestaBackendDTO busquedaDniPorNOrden(long nOrden);
    RespuestaBackendDTO busquedaDniPorReferencia(String referencia);

    List<ResponseMatrizDTO> listadoMatrizAdministrativa(RequesMatrizDTO requesMatrizDTO);

    List<ResponseMatrizSaludDTO> listadoMatrizSalud(RequesMatrizDTO requesMatrizDTO);

    List<RespuestaBackendDTO> listadoEmpresasNP();

    List<RespuestaBackendDTO> listadoContratasNP();

    List<RespuestaBackendDTO> listadoMedicosNP();

    List<RespuestaBackendDTO> listadoTipoPruebasNP();

    List<RespuestaBackendDTO> listadoCargo();

    List<RespuestaBackendDTO> listadoArea();

}
