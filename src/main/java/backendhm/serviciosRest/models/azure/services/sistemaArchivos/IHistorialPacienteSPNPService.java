package backendhm.serviciosRest.models.azure.services.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.DetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.HistorialPacienteMultiservidorDto;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestHistorialPacienteMultiservidor;

import java.util.List;

public interface IHistorialPacienteSPNPService {

    List<HistorialPacienteMultiservidorDto> listadoHistorialPaciente(RequestHistorialPacienteMultiservidor requestHistorialPacienteSPTNP);
    List<SedePorUserDTO> listadoSedesPorUsuario(String username);

    List<DetalleHistorialPacienteMultiservidorDTO> detalleHistoruialUsuario(RequestDetalleHistorialPacienteMultiservidorDTO requestDetalleHistorialPacienteMultiservidorDTO);
}
