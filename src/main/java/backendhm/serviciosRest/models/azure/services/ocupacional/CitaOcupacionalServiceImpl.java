package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.*;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import backendhm.serviciosRest.models.azure.entity.asistencial.Contrata;
import backendhm.serviciosRest.models.azure.entity.asistencial.Empresa;
import backendhm.serviciosRest.models.azure.entity.ocupacional.BackendDetalleReservaOcupacionalEntity;
import backendhm.serviciosRest.models.azure.entity.ocupacional.BackendEntityReservaLista;
import backendhm.serviciosRest.models.azure.entity.ocupacional.CitaOcupacional;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.asistencial.IContrataRepository;
import backendhm.serviciosRest.models.azure.repository.asistencial.IEmpresaRepository;
import backendhm.serviciosRest.models.azure.repository.ocupacional.BackendEntityReservaListaRepository;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IBackendDetalleReservaOcupacionalRepository;
import backendhm.serviciosRest.models.azure.repository.ocupacional.ICitaOcupacionalRepository;
import backendhm.serviciosRest.models.azure.repository.parametros.IRespuestaBackendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaOcupacionalServiceImpl implements ICitaOcupacionalService{

    @Autowired
    private IBackendDetalleReservaOcupacionalRepository backendDetalleReservaOcupacionalRepository;
    @Autowired
    private ICitaOcupacionalRepository citaOcupacionalRepository;

    @Autowired
    private IContrataRepository contrataRepository;

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Autowired
    private BackendEntityReservaListaRepository backendEntityReservaListaRepository;

    @Autowired
    private IRespuestaBackendRepository respuestaBackendRepository;

    @Override
    public CitaOcupacionalDTO crearCitaOcupacional(CitaOcupacionalDTO citaOcupacionalDTO) {
        CitaOcupacional citaOcupacional=mapearEntidad(citaOcupacionalDTO);

        CitaOcupacional nuevaCitaOcupacional=citaOcupacionalRepository.save(citaOcupacional);

        return mapearDTO(nuevaCitaOcupacional);

    }

    @Override
    public ConsultaReservaDTO consultarReservaDatosPaciente(long dni) {
        RespuestaBackend rB=respuestaBackendRepository.validarRserva(dni).orElseThrow();
        ConsultaReservaDTO consultaReservaDTO=new ConsultaReservaDTO();
        CitaOcupacional citaOcupacional=new CitaOcupacional();
        if(rB.getId()==1){
            citaOcupacional=citaOcupacionalRepository.buscarContratoCitaOcupacionaPorDniLimit1(dni).orElseThrow();
                    consultaReservaDTO.setId_resp(rB.getId());
            consultaReservaDTO.setRucEmpresa(String.valueOf(citaOcupacional.getRucEmpresa()));
            consultaReservaDTO.setRucContrata(String.valueOf(citaOcupacional.getRucContrata()));
            consultaReservaDTO.setCargo(citaOcupacional.getCargo());
            consultaReservaDTO.setArea(citaOcupacional.getArea());
            consultaReservaDTO.setTipoExamen(citaOcupacional.getTipoExamen());
            consultaReservaDTO.setFechaReserva(citaOcupacional.getFechaReserva());
        }
        else {

            consultaReservaDTO.setId_resp(0);
            consultaReservaDTO.setRucContrata("");
            consultaReservaDTO.setRucContrata("");
            consultaReservaDTO.setCargo("");
            consultaReservaDTO.setArea("");
            consultaReservaDTO.setTipoExamen("");
            consultaReservaDTO.setFechaReserva(LocalDate.parse("2024-07-16"));
        }
        return consultaReservaDTO;
    }

    @Override
    public List<CitaOcupacionalDTO> listadoCitaOcupacional() {
        List<CitaOcupacional> listarCitaOcupacional=citaOcupacionalRepository.findAll();
        return listarCitaOcupacional.stream().map(this::mapearDTO).collect(Collectors.toList());    }

    @Override
    public List<BackendEntityReservaListaDTO> listadoReserevaPorUsername(String nameUser) {
        List<BackendEntityReservaLista> backendEntityReservaListas=backendEntityReservaListaRepository.listadoReservaPorUsername(nameUser).orElseThrow();
        return backendEntityReservaListas.stream().map(this::mapearDTOReserva).collect(Collectors.toList());
    }

    @Override
    public List<BackendDetalleReservaDTO> listadoDetalleReservaPorFiltros(RequestDetalleReservaDTO rq) {
       List<BackendDetalleReservaOcupacionalEntity> backendDetalle= backendDetalleReservaOcupacionalRepository.litaDetalleReservaPorFiltros(rq.getNombreSede(),rq.getFechaReserva(),rq.getNombreUser()).orElseThrow();
        return backendDetalle.stream().map(this::mapearDTODetalleReserva).collect(Collectors.toList());
    }

    @Override
    public CitaOcupacionalDTO obtenerCitaOcupacionalPorID(long id) {
        CitaOcupacional citaOcupacional=citaOcupacionalRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cita Ocupacional","id cita Ocupacional",id));
        return mapearDTO(citaOcupacional);
    }

    @Override
    public CitaOcupacionalDTO actualizarCitaOcupacional(CitaOcupacionalDTO citaOcupacionalDTO, long id) {
        CitaOcupacional citaOcupacional=citaOcupacionalRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cita Ocupacional","id cita Ocupacional",id));

        CitaOcupacional citaOcupacionalActual=citaOcupacionalRepository.save(actualizarEntidadMapeado(citaOcupacionalDTO,citaOcupacional));
        return mapearDTO(citaOcupacionalActual);
    }

    @Override
    public void eliminarCitaOcupacional(long id) {
        CitaOcupacional citaOcupacional=citaOcupacionalRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cita Ocupacional","id Cita Ocupacional",id));
        citaOcupacionalRepository.delete(citaOcupacional);
    }

    BackendDetalleReservaDTO mapearDTODetalleReserva(BackendDetalleReservaOcupacionalEntity bck){
        BackendDetalleReservaDTO bdto= new BackendDetalleReservaDTO();

        bdto.setIdResp(bck.getIdResp());
        bdto.setSede(bck.getSede());
        bdto.setEmpresa(bck.getEmpresa());
        bdto.setContrata(bck.getContrata());
        bdto.setProgramador(bck.getProgramador());
        bdto.setFecha(bck.getFecha());
        bdto.setDni(bck.getDni());

        return bdto;

    }

    BackendEntityReservaListaDTO mapearDTOReserva(BackendEntityReservaLista bck){
        BackendEntityReservaListaDTO bkl=new BackendEntityReservaListaDTO();
        bkl.setId(bck.getId());
        bkl.setSede(bck.getSede());
        bkl.setCantidad(bck.getCantidad());
        bkl.setNomensede(bck.getNomensede());
        bkl.setUsuario(bck.getUsuario());
        bkl.setFechaReserva(bck.getFechaReserva());
        bkl.setFechaRegistro(bck.getFechaRegistro());

        return bkl;
    }

    CitaOcupacionalDTO mapearDTO(CitaOcupacional citaOcupacional){
        CitaOcupacionalDTO citaOcupacionalDTO=new CitaOcupacionalDTO();
        if (citaOcupacional.getRucEmpresa() != null) {
            Empresa empresa = empresaRepository.findById(String.valueOf(citaOcupacional.getRucEmpresa())).orElseThrow();
            citaOcupacionalDTO.setRazonEmpresa(empresa.getRazonEmpresa());
            citaOcupacionalDTO.setRucEmpresa(citaOcupacional.getRucEmpresa());

        }
        else {
            citaOcupacionalDTO.setRazonEmpresa("");
            citaOcupacionalDTO.setRucEmpresa(null);

        }
        if (citaOcupacional.getRucContrata() != null) {
            Contrata contrata=contrataRepository.findById(citaOcupacional.getRucContrata().toString()).orElseThrow();
            citaOcupacionalDTO.setRazonContrata(contrata.getRazonContrata());
            citaOcupacionalDTO.setRucContrata(citaOcupacional.getRucContrata());

        }
        else {
            citaOcupacionalDTO.setRazonContrata("");
            citaOcupacionalDTO.setRucContrata(null);

        }

        citaOcupacionalDTO.setCelular(citaOcupacional.getCelular());
        citaOcupacionalDTO.setIdCitaOcupacional(citaOcupacional.getIdCitaOcupacional());
        citaOcupacionalDTO.setDni(citaOcupacional.getDni());
        citaOcupacionalDTO.setFechaRegistro(citaOcupacional.getFechaRegistro());
        citaOcupacionalDTO.setFechaActualizacion(citaOcupacional.getFechaActualizacion());
        citaOcupacionalDTO.setFechaReserva(citaOcupacional.getFechaReserva());
        citaOcupacionalDTO.setNomenSede(citaOcupacional.getNomenSede());
        citaOcupacionalDTO.setUserRegistro(citaOcupacional.getUserRegistro());
        citaOcupacionalDTO.setUserActualizacion(citaOcupacional.getUserActualizacion());
        citaOcupacionalDTO.setCargo(citaOcupacional.getCargo());
        citaOcupacionalDTO.setArea(citaOcupacional.getArea());
        citaOcupacionalDTO.setTipoExamen(citaOcupacional.getTipoExamen());


        return citaOcupacionalDTO;
    }

    CitaOcupacional mapearEntidad(CitaOcupacionalDTO citaOcupacionalDTO){
        CitaOcupacional citaOcupacional=new CitaOcupacional();

        citaOcupacional.setCelular(citaOcupacionalDTO.getCelular());
        citaOcupacional.setDni(citaOcupacionalDTO.getDni());
        citaOcupacional.setFechaRegistro(citaOcupacionalDTO.getFechaRegistro());
        citaOcupacional.setFechaActualizacion(citaOcupacionalDTO.getFechaActualizacion());
        citaOcupacional.setFechaReserva(citaOcupacionalDTO.getFechaReserva());
        citaOcupacional.setNomenSede(citaOcupacionalDTO.getNomenSede());
        citaOcupacional.setRucEmpresa(citaOcupacionalDTO.getRucEmpresa());
        citaOcupacional.setRucContrata(citaOcupacionalDTO.getRucContrata());
        citaOcupacional.setUserRegistro(citaOcupacionalDTO.getUserRegistro());
        citaOcupacional.setUserActualizacion(citaOcupacionalDTO.getUserActualizacion());
        citaOcupacional.setCargo(citaOcupacionalDTO.getCargo());
        citaOcupacional.setArea(citaOcupacionalDTO.getArea());
        citaOcupacional.setTipoExamen(citaOcupacionalDTO.getTipoExamen());

        return citaOcupacional;
    }

    CitaOcupacional actualizarEntidadMapeado(CitaOcupacionalDTO citaOcupacionalDTO, CitaOcupacional citaOcupacional){

        citaOcupacional.setCelular(citaOcupacionalDTO.getCelular());
        citaOcupacional.setDni(citaOcupacionalDTO.getDni());
        citaOcupacional.setFechaRegistro(citaOcupacionalDTO.getFechaRegistro());
        citaOcupacional.setFechaActualizacion(citaOcupacionalDTO.getFechaActualizacion());
        citaOcupacional.setFechaReserva(citaOcupacionalDTO.getFechaReserva());
        citaOcupacional.setNomenSede(citaOcupacionalDTO.getNomenSede());
        citaOcupacional.setRucEmpresa(citaOcupacionalDTO.getRucEmpresa());
        citaOcupacional.setRucContrata(citaOcupacionalDTO.getRucContrata());
        citaOcupacional.setUserRegistro(citaOcupacionalDTO.getUserRegistro());
        citaOcupacional.setUserActualizacion(citaOcupacionalDTO.getUserActualizacion());
        citaOcupacional.setCargo(citaOcupacionalDTO.getCargo());
        citaOcupacional.setArea(citaOcupacionalDTO.getArea());
        citaOcupacional.setTipoExamen(citaOcupacionalDTO.getTipoExamen());
        return citaOcupacional;
    }


}
