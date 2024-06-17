package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.CitaOcupacionalDTO;
import backendhm.serviciosRest.models.azure.entity.asistencial.Contrata;
import backendhm.serviciosRest.models.azure.entity.asistencial.Empresa;
import backendhm.serviciosRest.models.azure.entity.ocupacional.CitaOcupacional;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.asistencial.IContrataRepository;
import backendhm.serviciosRest.models.azure.repository.asistencial.IEmpresaRepository;
import backendhm.serviciosRest.models.azure.repository.ocupacional.ICitaOcupacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaOcupacionalServiceImpl implements ICitaOcupacionalService{

    @Autowired
    private ICitaOcupacionalRepository citaOcupacionalRepository;

    @Autowired
    private IContrataRepository contrataRepository;

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Override
    public CitaOcupacionalDTO crearCitaOcupacional(CitaOcupacionalDTO citaOcupacionalDTO) {
        CitaOcupacional citaOcupacional=mapearEntidad(citaOcupacionalDTO);

        CitaOcupacional nuevaCitaOcupacional=citaOcupacionalRepository.save(citaOcupacional);

        return mapearDTO(nuevaCitaOcupacional);

    }

    @Override
    public List<CitaOcupacionalDTO> listadoCitaOcupacional() {
        List<CitaOcupacional> listarCitaOcupacional=citaOcupacionalRepository.findAll();
        return listarCitaOcupacional.stream().map(this::mapearDTO).collect(Collectors.toList());    }

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

    CitaOcupacionalDTO mapearDTO(CitaOcupacional citaOcupacional){
        CitaOcupacionalDTO citaOcupacionalDTO=new CitaOcupacionalDTO();
        if (citaOcupacional.getRucEmpresa().toString().length()>0) {
            Empresa empresa = empresaRepository.findById(String.valueOf(citaOcupacional.getRucEmpresa())).orElseThrow();
            citaOcupacionalDTO.setRazonEmpresa(empresa.getRazonEmpresa());
        }
        else
            citaOcupacionalDTO.setRazonEmpresa("");
        if (citaOcupacional.getRucContrata().toString().length()>0) {
            Contrata contrata=contrataRepository.findById(citaOcupacional.getRucContrata().toString()).orElseThrow();
            citaOcupacionalDTO.setRazonContrata(contrata.getRazonContrata());
        }
        else
        citaOcupacionalDTO.setRazonContrata("");


        citaOcupacionalDTO.setIdCitaOcupacional(citaOcupacional.getIdCitaOcupacional());
        citaOcupacionalDTO.setDni(citaOcupacional.getDni());
        citaOcupacionalDTO.setFechaRegistro(citaOcupacional.getFechaRegistro());
        citaOcupacionalDTO.setFechaActualizacion(citaOcupacional.getFechaActualizacion());
        citaOcupacionalDTO.setFechaReserva(citaOcupacional.getFechaReserva());
        citaOcupacionalDTO.setNomenSede(citaOcupacional.getNomenSede());
        citaOcupacionalDTO.setRucEmpresa(citaOcupacional.getRucEmpresa());
        citaOcupacionalDTO.setRucContrata(citaOcupacional.getRucContrata());
        citaOcupacionalDTO.setUserRegistro(citaOcupacional.getUserRegistro());
        citaOcupacionalDTO.setUserActualizacion(citaOcupacional.getUserActualizacion());



        return citaOcupacionalDTO;
    }

    CitaOcupacional mapearEntidad(CitaOcupacionalDTO citaOcupacionalDTO){
        CitaOcupacional citaOcupacional=new CitaOcupacional();

        citaOcupacional.setDni(citaOcupacionalDTO.getDni());
        citaOcupacional.setFechaRegistro(citaOcupacionalDTO.getFechaRegistro());
        citaOcupacional.setFechaActualizacion(citaOcupacionalDTO.getFechaActualizacion());
        citaOcupacional.setFechaReserva(citaOcupacionalDTO.getFechaReserva());
        citaOcupacional.setNomenSede(citaOcupacionalDTO.getNomenSede());
        citaOcupacional.setRucEmpresa(citaOcupacionalDTO.getRucEmpresa());
        citaOcupacional.setRucContrata(citaOcupacionalDTO.getRucContrata());
        citaOcupacional.setUserRegistro(citaOcupacionalDTO.getUserRegistro());
        citaOcupacional.setUserActualizacion(citaOcupacionalDTO.getUserActualizacion());

        return citaOcupacional;
    }

    CitaOcupacional actualizarEntidadMapeado(CitaOcupacionalDTO citaOcupacionalDTO, CitaOcupacional citaOcupacional){

        citaOcupacional.setDni(citaOcupacionalDTO.getDni());
        citaOcupacional.setFechaRegistro(citaOcupacionalDTO.getFechaRegistro());
        citaOcupacional.setFechaActualizacion(citaOcupacionalDTO.getFechaActualizacion());
        citaOcupacional.setFechaReserva(citaOcupacionalDTO.getFechaReserva());
        citaOcupacional.setNomenSede(citaOcupacionalDTO.getNomenSede());
        citaOcupacional.setRucEmpresa(citaOcupacionalDTO.getRucEmpresa());
        citaOcupacional.setRucContrata(citaOcupacionalDTO.getRucContrata());
        citaOcupacional.setUserRegistro(citaOcupacionalDTO.getUserRegistro());
        citaOcupacional.setUserActualizacion(citaOcupacionalDTO.getUserActualizacion());

        return citaOcupacional;
    }


}
