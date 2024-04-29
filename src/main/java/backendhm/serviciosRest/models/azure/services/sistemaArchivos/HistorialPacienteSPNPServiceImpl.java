package backendhm.serviciosRest.models.azure.services.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import backendhm.serviciosRest.models.azure.entity.SedeHmWeb;
import backendhm.serviciosRest.models.azure.repository.ISedeHmRepository;
import backendhm.serviciosRest.models.azure.repository.parametros.IRespuestaBackendRepository;
import backendhm.serviciosRest.models.spTrujilloNP.dto.HistorialPacienteSPNPDto;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestHistorialPacienteSPTNP;
import backendhm.serviciosRest.models.spTrujilloNP.entity.HistorialPACIENTE;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IPruebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialPacienteSPNPServiceImpl implements  IHistorialPacienteSPNPService{

    @Autowired
    private IPruebRepository pruebRepository;

    @Autowired
    private ISedeHmRepository sedeHmRepository;

    @Autowired
    private IRespuestaBackendRepository respuestaBackendRepository;

    @Override
    public List<HistorialPacienteSPNPDto> listadoHistorialPaciente(RequestHistorialPacienteSPTNP request) {
        String sede=request.getSedeUser();
        RespuestaBackend resp=respuestaBackendRepository.obtenerRucUsuario(request.getUserName()).orElseThrow();
        RespuestaBackend resp2=respuestaBackendRepository.obtenerTipoUsuario(request.getUserName()).orElseThrow();

        List<HistorialPACIENTE> listadoHP=null;
        if(sede.contains("T-NP") || sede.contains("HNCY"))
        {
            request.setTipoUsuario(resp2.getId());
            request.setRucUser(String.valueOf(resp.getId()));
            System.out.println("el objeto es:"+request);
           listadoHP= pruebRepository.obtenerHistorialPacienteUsuariosNP(request.getUserName(),request.getFechaInicio(),request.getFechaFin(), request.getTipoUsuario(), request.getRucUser(), request.getSedeUser()).orElseThrow();

        }


        return listadoHP.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<SedePorUserDTO> listadoSedesPorUsuario(String username) {
        List<SedeHmWeb> listadoSedes=sedeHmRepository.listadoSedes(username).orElseThrow();

        return listadoSedes.stream().map(this::mapearDTOListaSede).collect(Collectors.toList());
    }

    public SedePorUserDTO mapearDTOListaSede(SedeHmWeb sedeHmWeb){
        SedePorUserDTO sedePorUserDTO=new SedePorUserDTO();

        sedePorUserDTO.setCod_sede(sedeHmWeb.getCodigoSede());
        sedePorUserDTO.setNombre_sede(sedeHmWeb.getNombreSede());
        return sedePorUserDTO;
    }


    public HistorialPacienteSPNPDto mapearDTO(HistorialPACIENTE historialPaciente ){
        HistorialPacienteSPNPDto historialPacienteSPNPDto=new HistorialPacienteSPNPDto();

        historialPacienteSPNPDto.setCodigo_sucursal(historialPaciente.getCodigo());
        historialPacienteSPNPDto.setDni(historialPaciente.getDni());
        historialPacienteSPNPDto.setApellidos(historialPaciente.getApellido());
        historialPacienteSPNPDto.setNombres(historialPaciente.getNombre());
        historialPacienteSPNPDto.setFecha_examen(historialPaciente.getFecha());


        return historialPacienteSPNPDto;

    }

}
