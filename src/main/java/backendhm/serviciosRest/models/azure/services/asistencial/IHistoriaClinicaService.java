package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.HistoriaClinicaDTO;

import java.util.List;

public interface IHistoriaClinicaService {
    HistoriaClinicaDTO crearHistoriaClinica(HistoriaClinicaDTO historiaClinicaDTO);
    List<HistoriaClinicaDTO> listadoHistoriasClinicas();
    HistoriaClinicaDTO obtenerHistoriaClinicaPorNOrden(String nOrden);
    HistoriaClinicaDTO actualizarHistoriaClinica(HistoriaClinicaDTO historiaClinicaDTO, String nOrden);
    void eliminarHistoriaClinica(String nOrden);
}
