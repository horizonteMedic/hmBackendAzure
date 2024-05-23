package backendhm.serviciosRest.models.azure.services.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
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
import backendhm.serviciosRest.models.spTrujilloSD.entity.DetalleHistorialPacienteSD;
import backendhm.serviciosRest.models.spTrujilloSD.entity.HistorialPacienteSD;
import backendhm.serviciosRest.models.spTrujilloSD.repository.IDetalleHistorialUsuarioRepositorySD;
import backendhm.serviciosRest.models.spTrujilloSD.repository.IHistorialPacienteRepositorySD;
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

    @Autowired
    private IHistorialPacienteRepositorySD historialPacienteRepositorySD;

    @Autowired
    private IDetalleHistorialUsuarioRepositorySD detalleHistorialUsuarioRepositorySD;

    @Override
    public List<HistorialPacienteMultiservidorDto> listadoHistorialPaciente(RequestHistorialPacienteMultiservidor request) {
        String sede=request.getSedeUser();
        String tipo=null;
        if(request.getRucEmpresa().length() >1){
            tipo="EMPRESA";
            request.setTipo(tipo);
        }
        if(request.getRucContrata().length()>1)
        {
            tipo="CONTRATA";
            request.setTipo(tipo);

        }

        List<HistorialPACIENTE> listadoHP=null;
        if(sede.contains("T-NP") || sede.contains("HNCY") || sede.contains("T-SD")|| sede.contains("HMAC") )
        {
            if(sede.contains("T-NP") || sede.contains("HNCY") || sede.contains("HMAC")) {
                listadoHP = pruebRepository.obtenerHistorialPacienteUsuariosNP(request.getUserName(), request.getFechaInicio(), request.getFechaFin(), request.getTipo(), request.getRucContrata(), request.getRucEmpresa(), request.getSedeUser()).orElseThrow();
                return listadoHP.stream().map(this::mapearDTO).collect(Collectors.toList());
            }

            else
            if(sede.contains("T-SD"))
            {

                List<HistorialPacienteSD> listadoSD=null;
                listadoSD=historialPacienteRepositorySD.obtenerHistorialPacienteUsuariosSD(request.getUserName(),request.getFechaInicio(),request.getFechaFin(), request.getTipo(), request.getRucContrata(), request.getRucEmpresa(), request.getSedeUser()).orElseThrow();
                return listadoSD.stream().map(this::mapearDTOSD).collect(Collectors.toList());

            }
        }

    return null;

    }

    @Override
    public List<SedePorUserDTO> listadoSedesPorUsuario(String username) {
        List<SedeHmWeb> listadoSedes=sedeHmRepository.listadoSedes(username).orElseThrow();

        return listadoSedes.stream().map(this::mapearDTOListaSede).collect(Collectors.toList());
    }

    @Override
    public List<DetalleHistorialPacienteMultiservidorDTO> detalleHistoruialUsuario(RequestDetalleHistorialPacienteMultiservidorDTO request) {
        String sede=request.getSedeUser();
        String tipo=null;
       List<DetalleHistorialPaciente> detalleHistorialPaciente=null;
        if(request.getRucEmpresa().length() >1){
            tipo="EMPRESA";
            request.setTipo(tipo);
        }
        if(request.getRucContrata().length()>1)
        {
            tipo="CONTRATA";
            request.setTipo(tipo);

        }
        if(sede.contains("T-NP") || sede.contains("HNCY") || sede.contains("T-SD") || sede.contains("HMAC"))
        {
            if(sede.contains("T-NP") || sede.contains("HNCY")|| sede.contains("HMAC")) {
                detalleHistorialPaciente = detalleHistorialUsuarioRepository.obtenerdetalleHistorialPacienteUsuariosNP(request.getUserName(), request.getFechaInicio(), request.getFechaFin(), request.getTipo(), request.getRucContrata(), request.getRucEmpresa(), request.getSedeUser(), request.getDniUser()).orElseThrow();
                return detalleHistorialPaciente.stream().map(this::mapearDTOdetalleHistorialPaciente).collect(Collectors.toList());

            }

            if(sede.contains("T-SD"))
            {
                List<DetalleHistorialPacienteSD>
                        detalleHistorialPacienteSD=detalleHistorialUsuarioRepositorySD.obtenerdetalleHistorialPacienteUsuariosNP(request.getUserName(), request.getFechaInicio(), request.getFechaFin(), request.getTipo(), request.getRucContrata(), request.getRucEmpresa(), request.getSedeUser(), request.getDniUser()).orElseThrow();
                return detalleHistorialPacienteSD.stream().map(this::mapearDTOdetalleHistorialPacienteSD).collect(Collectors.toList());

            }

        }
        return null;

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
    public DetalleHistorialPacienteMultiservidorDTO mapearDTOdetalleHistorialPacienteSD(DetalleHistorialPacienteSD detalleHistorialPaciente){

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

    public HistorialPacienteMultiservidorDto mapearDTOSD(HistorialPacienteSD historialPaciente ){
        HistorialPacienteMultiservidorDto historialPacienteSPNPDto=new HistorialPacienteMultiservidorDto();

        historialPacienteSPNPDto.setCodigo_sucursal(historialPaciente.getCodigo());
        historialPacienteSPNPDto.setDni(historialPaciente.getDni());
        historialPacienteSPNPDto.setApellidos(historialPaciente.getApellido());
        historialPacienteSPNPDto.setNombres(historialPaciente.getNombre());
        historialPacienteSPNPDto.setFecha_examen(historialPaciente.getFecha());


        return historialPacienteSPNPDto;

    }
}
