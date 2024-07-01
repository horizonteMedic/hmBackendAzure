package backendhm.serviciosRest.models.spTrujilloNP.services;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.BackendHistoriaOcupacionalDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.RequestHistoriaClinicaOcupacionalDTO;
import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ResponseMatrizArchivosDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.*;

import java.util.List;

public interface IRespuestaBackendService {
    RespuestaBackendDTO registrarDatosPaciente(RequestDatosPacienteDTO requestDatosPacienteDTO);

    RequestDatosPacienteDTO busquedaDatosPacienteDNI(long dni);

    RespuestaBackendDTO registroHistoriaClinica(RequestNOrdenOcupacionalDTO requestNOrdenOcupacionalDTO);

    backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO registroHistoriaClinicaOcupacional(RequestHistoriaClinicaOcupacionalDTO requestHistoriaClinicaOcupacionalDTO);

    RespuestaBackendDTO busquedaDniPorNOrden(long nOrden);
    RespuestaBackendDTO busquedaDniPorReferencia(String referencia);

    List<ResponseMatrizDTO> listadoMatrizAdministrativa(RequesMatrizDTO requesMatrizDTO);

    List<ResponseMatrizSaludDTO> listadoMatrizSalud(RequesMatrizDTO requesMatrizDTO);

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoEmpresasNP();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoContratasNP();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoMedicosNP();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoTipoPruebasNP();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoCargo();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoArea();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoTipoExamen();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoExplotacion();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoMineral();

    List<backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO> listadoAltura();

    backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO precioExamen(String nameExamen);

    List<BackendHistoriaOcupacionalDTO> listadoHistoriaOcupacionalSede(String codSede);

    List<ResponseMatrizArchivosDTO> listadoMatrizArchivos();
}
