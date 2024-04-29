package backendhm.serviciosRest.models.azure.services.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.HistorialPacienteSPNPDto;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestHistorialPacienteSPTNP;

import java.util.List;

public interface IHistorialPacienteSPNPService {

    List<HistorialPacienteSPNPDto> listadoHistorialPaciente(RequestHistorialPacienteSPTNP requestHistorialPacienteSPTNP);
    List<SedePorUserDTO> listadoSedesPorUsuario(String username);
}
