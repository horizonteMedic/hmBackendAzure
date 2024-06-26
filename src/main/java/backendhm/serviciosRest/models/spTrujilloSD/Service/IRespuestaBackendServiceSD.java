package backendhm.serviciosRest.models.spTrujilloSD.Service;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.RequestHistoriaClinicaOcupacionalDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDatosPacienteDTO;
import backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD;

import java.util.List;

public interface IRespuestaBackendServiceSD {
    RequestDatosPacienteDTO busquedaDatosPacienteDNI(long dni);
    RespuestaBackendDTOTSD registrarDatosPaciente(RequestDatosPacienteDTO requestDatosPacienteDTO);
    RespuestaBackendDTOTSD busquedaDniPorNOrden(long nOrden);

    backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO registroHistoriaClinicaOcupacional(RequestHistoriaClinicaOcupacionalDTO requestHistoriaClinicaOcupacionalDTO);

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
}
