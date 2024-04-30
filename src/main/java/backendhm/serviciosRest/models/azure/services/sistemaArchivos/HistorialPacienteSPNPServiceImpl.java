package backendhm.serviciosRest.models.azure.services.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import backendhm.serviciosRest.models.azure.entity.SedeHmWeb;
import backendhm.serviciosRest.models.azure.repository.ISedeHmRepository;
import backendhm.serviciosRest.models.azure.repository.parametros.IRespuestaBackendRepository;
import backendhm.serviciosRest.models.spTrujilloNP.dto.DetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.HistorialPacienteMultiservidorDto;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestDetalleHistorialPacienteMultiservidorDTO;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestHistorialPacienteMultiservidor;
import backendhm.serviciosRest.models.spTrujilloNP.entity.DetalleHistorialPaciente;
import backendhm.serviciosRest.models.spTrujilloNP.entity.HistorialPACIENTE;
import backendhm.serviciosRest.models.spTrujilloNP.repository.IDetalleHistorialUsuarioRepository;
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
    private IDetalleHistorialUsuarioRepository detalleHistorialUsuarioRepository;

    @Autowired
    private IRespuestaBackendRepository respuestaBackendRepository;

    @Override
    public List<HistorialPacienteMultiservidorDto> listadoHistorialPaciente(RequestHistorialPacienteMultiservidor request) {
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

    @Override
    public List<DetalleHistorialPacienteMultiservidorDTO> detalleHistoruialUsuario(RequestDetalleHistorialPacienteMultiservidorDTO request) {
        String sede=request.getSedeUser();
        RespuestaBackend resp=respuestaBackendRepository.obtenerRucUsuario(request.getUserName()).orElseThrow();
        RespuestaBackend resp2=respuestaBackendRepository.obtenerTipoUsuario(request.getUserName()).orElseThrow();
        List<DetalleHistorialPaciente> detalleHistorialPaciente=null;

        if(sede.contains("T-NP") || sede.contains("HNCY"))
        {
            request.setTipoUsuario(resp2.getId());
            request.setRucUser(String.valueOf(resp.getId()));
            System.out.println("EL detalle del historial usaurio: "+request);
            detalleHistorialPaciente=detalleHistorialUsuarioRepository.obtenerdetalleHistorialPacienteUsuariosNP(request.getUserName(),request.getFechaInicio(),request.getFechaFin(), request.getTipoUsuario(), request.getRucUser(), request.getSedeUser(),request.getDniUser()).orElseThrow();
        }


        return detalleHistorialPaciente.stream().map(this::mapearDTOdetalleHistorialPaciente).collect(Collectors.toList());
    }

    public DetalleHistorialPacienteMultiservidorDTO mapearDTOdetalleHistorialPaciente(DetalleHistorialPaciente detalleHistorialPaciente){

        DetalleHistorialPacienteMultiservidorDTO detalleHP=new DetalleHistorialPacienteMultiservidorDTO();

        detalleHP.setHistoriaClinica(detalleHistorialPaciente.getHistoriaClinica());
        detalleHP.setOrden(detalleHistorialPaciente.getOrden());
        detalleHP.setEmpresa(detalleHistorialPaciente.getEmpresa());
        detalleHP.setContrata(detalleHistorialPaciente.getContrata());
        detalleHP.setFechaExamen(detalleHistorialPaciente.getFechaExamen());
        detalleHP.setExamen(detalleHistorialPaciente.getExamen());
        detalleHP.setEstado(detalleHistorialPaciente.getEstado());
        detalleHP.setCargo(detalleHistorialPaciente.getCargo());
        detalleHP.setArea(detalleHistorialPaciente.getArea());
        detalleHP.setGrupoSanguineo(detalleHistorialPaciente.getGrupoSanguineo());

        return detalleHP;

    }

    public SedePorUserDTO mapearDTOListaSede(SedeHmWeb sedeHmWeb){
        SedePorUserDTO sedePorUserDTO=new SedePorUserDTO();

        sedePorUserDTO.setCod_sede(sedeHmWeb.getCodigoSede());
        sedePorUserDTO.setNombre_sede(sedeHmWeb.getNombreSede());
        return sedePorUserDTO;
    }


    public HistorialPacienteMultiservidorDto mapearDTO(HistorialPACIENTE historialPaciente ){
        HistorialPacienteMultiservidorDto historialPacienteSPNPDto=new HistorialPacienteMultiservidorDto();

        historialPacienteSPNPDto.setCodigo_sucursal(historialPaciente.getCodigo());
        historialPacienteSPNPDto.setDni(historialPaciente.getDni());
        historialPacienteSPNPDto.setApellidos(historialPaciente.getApellido());
        historialPacienteSPNPDto.setNombres(historialPaciente.getNombre());
        historialPacienteSPNPDto.setFecha_examen(historialPaciente.getFecha());


        return historialPacienteSPNPDto;

    }

}
