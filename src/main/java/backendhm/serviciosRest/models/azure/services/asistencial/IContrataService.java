package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.ContrataDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpContDTO;

import java.util.List;

public interface IContrataService {
    ContrataDTO crearContrata(ContrataDTO contrataDTO);
    List<ContrataDTO> listadoContratas();

    ContrataDTO obtenerContrataPorRuc(String ruc);
    ContrataDTO actualizarContrata(ContrataDTO contrataDTO, String ruc);
    void eliminarContrata(String ruc);
}
