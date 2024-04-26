package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.*;
import backendhm.serviciosRest.models.azure.entity.*;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.*;
import backendhm.serviciosRest.models.azure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaWebServiceImpl implements IEmpresaWebService{

    @Autowired
    private IHistorialUsuarioRepository historialUsuarioRepository;

    @Autowired
    private IEmpresaWebRepository empresaWebRepository;

    @Autowired
    private IDetalleHistoriaClinicaRepository detalleHistoriaClinicaRepository;

    @Autowired
    private IHistorialPacienteRepository historialPacienteRepository;

    @Autowired
    private IEntidadDTORepository entidadDTORepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<HistorialUsuarioDTO> listadoHistoriaUsuario() {
        List<HistorialUsuarios> listadoHistoriasUser=historialUsuarioRepository.listadoHistorialUsuarios().orElseThrow();
        return listadoHistoriasUser.stream().map(this::mapearDTOHistorialUsuario).collect(Collectors.toList());
    }

    @Override
    public List<DetalleHistoriaClinicaDTO> listaDetalleHistoriaClinica(long id) {
        List<DetalleHistoriaClinica> listadoDetalleHistoriaClinica= detalleHistoriaClinicaRepository.
                listadoDetalleHistoriaClinica(id).orElseThrow();


        return listadoDetalleHistoriaClinica.stream().map(this::mapearDTODetalleHistoriaClinicaListado).collect(Collectors.toList());
    }

    @Override
    public List<HistorialPacienteDTO> listadoHistorialPaciente(long id) {
        List<HistorialPaciente> listadoHistorialPaciente=historialPacienteRepository.listadoHistorialPaciente(id).orElseThrow();
        return listadoHistorialPaciente.stream().map(this::mapearDTOHistorialPaicenteListado).collect(Collectors.toList());
    }

    @Override
    public List<ListadoEmpresaContrataDTO> listadoEmpresasContratas() {
        List<EntidadDTO> listadoEmpresaContrata=entidadDTORepository.listadoContrataEmpresasHM().orElseThrow();
        return listadoEmpresaContrata.stream().map(this::mapearDTOListado).collect(Collectors.toList());
    }

    @Override
    public EmpresaWebDTO crearEmpresaWeb(EmpresaWebDTO empresaWebDTO) {
        EmpresaWeb empresaWeb=mapearEntidad(empresaWebDTO);

        EmpresaWeb nuevaEmpresaWeb=empresaWebRepository.save(empresaWeb);
        EmpresaWebDTO empresaWebDTORespuesta=mapearDTO(nuevaEmpresaWeb);

        return empresaWebDTORespuesta;
    }

    @Override
    public List<EmpresaWebDTO> listadoEmpresasWeb() {
        List<EmpresaWeb> listadoEmpresasWeb=empresaWebRepository.findAll();
        return listadoEmpresasWeb.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public EmpresaWebDTO obtenerEmpresaWebPorID(long id) {
        EmpresaWeb empresaWeb=empresaWebRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Empresa Web","id empresa web",id));
        return mapearDTO(empresaWeb);
    }

    @Override
    public EmpresaWebDTO actualizarEmpresaWeb(EmpresaWebDTO empresaWebDTO, long id) {
        EmpresaWeb empresaWeb=empresaWebRepository.findById(id).
        orElseThrow(()-> new ResourceNotFoundException("Empresa Web","id empresa web",id));

        EmpresaWeb empresaWebActual=empresaWebRepository.save(actualizarEmpresaWebEntidad(empresaWebDTO,empresaWeb));
        return mapearDTO(empresaWebActual);
    }

    @Override
    public void eliminarEmpresaWeb(long id) {
        EmpresaWeb empresaWeb=empresaWebRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Empresa Web","id empresa web",id));
        empresaWebRepository.delete(empresaWeb);
    }

    private ListadoEmpresaContrataDTO mapearDTOListado(EntidadDTO entidadDTO){
        ListadoEmpresaContrataDTO listadoEmpresaContrataDTO=new ListadoEmpresaContrataDTO();
        listadoEmpresaContrataDTO.setId(entidadDTO.getId());
        listadoEmpresaContrataDTO.setRuc(entidadDTO.getRuc());
        listadoEmpresaContrataDTO.setRazonSocial(entidadDTO.getRazonSocial());
        return listadoEmpresaContrataDTO;
    }

    private EmpresaWebDTO mapearDTO(EmpresaWeb empresaWeb){
        EmpresaWebDTO empresaWebDTO=new EmpresaWebDTO();
        empresaWebDTO.setId(empresaWeb.getId());
        empresaWebDTO.setRuc(empresaWeb.getRuc());
        empresaWebDTO.setEstado(empresaWeb.getEstado());
        empresaWebDTO.setFechaRegistro(empresaWeb.getFechaRegistro());
        empresaWebDTO.setUserRegistro(empresaWeb.getUserRegistro());
        empresaWebDTO.setFechaActualizacion(empresaWeb.getFechaActualizacion());
        empresaWebDTO.setUserActualizacion(empresaWeb.getUserActualizacion());
        empresaWebDTO.setIdUser(empresaWeb.getUsuario().getIdUser());
        return empresaWebDTO;
    }

    private EmpresaWeb mapearEntidad(EmpresaWebDTO empresaWebDTO){
        EmpresaWeb empresaWeb=new EmpresaWeb();
        Usuario usuario= userRepository.findById(empresaWebDTO.getIdUser()).orElseThrow();

        empresaWeb.setRuc(empresaWebDTO.getRuc());
        empresaWeb.setEstado(empresaWebDTO.getEstado());
        empresaWeb.setFechaRegistro(empresaWebDTO.getFechaRegistro());
        empresaWeb.setUserRegistro(empresaWebDTO.getUserRegistro());
        empresaWeb.setFechaActualizacion(empresaWebDTO.getFechaActualizacion());
        empresaWeb.setUserActualizacion(empresaWebDTO.getUserActualizacion());
        empresaWeb.setUsuario(usuario);
        return empresaWeb;
    }

    private EmpresaWeb actualizarEmpresaWebEntidad(EmpresaWebDTO empresaWebDTO,EmpresaWeb empresaWeb){
        Usuario usuario= userRepository.findById(empresaWebDTO.getIdUser()).orElseThrow();

        empresaWeb.setRuc(empresaWebDTO.getRuc());
        empresaWeb.setEstado(empresaWebDTO.getEstado());
        empresaWeb.setFechaRegistro(empresaWebDTO.getFechaRegistro());
        empresaWeb.setUserRegistro(empresaWebDTO.getUserRegistro());
        empresaWeb.setFechaActualizacion(empresaWebDTO.getFechaActualizacion());
        empresaWeb.setUserActualizacion(empresaWebDTO.getUserActualizacion());
        empresaWeb.setUsuario(usuario);
        return empresaWeb;
    }

    private HistorialPacienteDTO mapearDTOHistorialPaicenteListado(HistorialPaciente historialPaciente){

        HistorialPacienteDTO listadoHistorialPacienteDTO=new HistorialPacienteDTO();
        listadoHistorialPacienteDTO.setDni(historialPaciente.getDni());
        listadoHistorialPacienteDTO.setNombres(historialPaciente.getNombres());
        listadoHistorialPacienteDTO.setApellidos(historialPaciente.getApellidos());
        listadoHistorialPacienteDTO.setFechaExamen(historialPaciente.getFechaExamen());

        return listadoHistorialPacienteDTO;
    }

    private DetalleHistoriaClinicaDTO mapearDTODetalleHistoriaClinicaListado(DetalleHistoriaClinica detalleHistoriaClinica){

        DetalleHistoriaClinicaDTO listadoDetalleHistoriaClinica=new DetalleHistoriaClinicaDTO();

        listadoDetalleHistoriaClinica.setHistoriaClinica(detalleHistoriaClinica.getHistoriaClinica());
        listadoDetalleHistoriaClinica.setEmpresa(detalleHistoriaClinica.getEmpresa());
        listadoDetalleHistoriaClinica.setContrata(detalleHistoriaClinica.getContrata());
        listadoDetalleHistoriaClinica.setFechaApertura(detalleHistoriaClinica.getFechaApertura());
        listadoDetalleHistoriaClinica.setExamen(detalleHistoriaClinica.getExamen());
        listadoDetalleHistoriaClinica.setArea(detalleHistoriaClinica.getArea());
        listadoDetalleHistoriaClinica.setEstado(detalleHistoriaClinica.getEstado());
        listadoDetalleHistoriaClinica.setCargo(detalleHistoriaClinica.getCargo());
        listadoDetalleHistoriaClinica.setGrupoSanguineo(detalleHistoriaClinica.getGrupoSanguineo());

        return listadoDetalleHistoriaClinica;
    }



    private HistorialUsuarioDTO mapearDTOHistorialUsuario(HistorialUsuarios historialUsuarios){

        HistorialUsuarioDTO listadoHistorialUsuarios=new HistorialUsuarioDTO();

        listadoHistorialUsuarios.setNombres(historialUsuarios.getNombres());
        listadoHistorialUsuarios.setApellidos(historialUsuarios.getApellidos());
        listadoHistorialUsuarios.setRol(historialUsuarios.getRol());
        listadoHistorialUsuarios.setVigente(historialUsuarios.getVigente());
        listadoHistorialUsuarios.setTipoDocumento(historialUsuarios.getTipoDocumento());
        listadoHistorialUsuarios.setNumDocumento(historialUsuarios.getNumDocumento());
        listadoHistorialUsuarios.setUsuario(historialUsuarios.getUsuario());
        listadoHistorialUsuarios.setIdUser(historialUsuarios.getIdUser());
        listadoHistorialUsuarios.setIdUserRol(historialUsuarios.getIdUserRol());

        return listadoHistorialUsuarios;
    }
}
