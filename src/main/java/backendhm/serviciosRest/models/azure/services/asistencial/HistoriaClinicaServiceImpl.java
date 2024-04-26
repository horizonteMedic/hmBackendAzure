package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.entity.asistencial.HistoriaClinica;
import backendhm.serviciosRest.models.azure.dtos.asistencial.HistoriaClinicaDTO;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.asistencial.IHistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoriaClinicaServiceImpl implements IHistoriaClinicaService{

    @Autowired
    private IHistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public HistoriaClinicaDTO crearHistoriaClinica(HistoriaClinicaDTO historiaClinicaDTO) {
        HistoriaClinica historiaClinica=mapearEntidad(historiaClinicaDTO);
        String nroOrden=historiaClinicaRepository.nroHistoriaClinicaRegistrar();
        historiaClinica.setId(nroOrden);
        HistoriaClinica nuevaHistoriaClinia=historiaClinicaRepository.save(historiaClinica);
        HistoriaClinicaDTO historiaClinicaDTORespuesta=mapearDTO(nuevaHistoriaClinia);

        return historiaClinicaDTO;
    }

    @Override
    public List<HistoriaClinicaDTO> listadoHistoriasClinicas() {
        List<HistoriaClinica> listaHistoriaClinica=historiaClinicaRepository.findAll();
        return listaHistoriaClinica.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public HistoriaClinicaDTO obtenerHistoriaClinicaPorNOrden(String nOrden) {

        HistoriaClinica historiaClinica=historiaClinicaRepository.findById(nOrden)
                .orElseThrow(()-> new ResourceNotFoundException("Historia Clinica","nro orden",Long.parseLong(nOrden)));
        return mapearDTO(historiaClinica);
    }

    @Override
    public HistoriaClinicaDTO actualizarHistoriaClinica(HistoriaClinicaDTO historiaClinicaDTO, String nOrden) {
        HistoriaClinica historiaClinica=historiaClinicaRepository.findById(nOrden)
                .orElseThrow(()-> new ResourceNotFoundException("Historia Clinica","nro orden",Long.parseLong(nOrden)));

        HistoriaClinica historiaClinicaActual=historiaClinicaRepository.save(actualizarHistoriaClinicaEntidad(historiaClinicaDTO,historiaClinica));
        return mapearDTO(historiaClinicaActual);
    }

    @Override
    public void eliminarHistoriaClinica(String nOrden) {
        HistoriaClinica historiaClinica=historiaClinicaRepository.findById(nOrden)
                .orElseThrow(()-> new ResourceNotFoundException("Historia Clinica","nro orden",Long.parseLong(nOrden)));

        historiaClinicaRepository.delete(historiaClinica);
    }


    private HistoriaClinicaDTO mapearDTO(HistoriaClinica historiaClinica){
        HistoriaClinicaDTO historiaClinicaDTO=new HistoriaClinicaDTO();

        historiaClinicaDTO.setId(historiaClinica.getId());
        historiaClinicaDTO.setNroDoc(historiaClinica.getNroDoc());
        historiaClinicaDTO.setRucEmpresa(historiaClinica.getRucEmpresa());
        historiaClinicaDTO.setRucContrata(historiaClinica.getRucContrata());
        historiaClinicaDTO.setFechaRegistro(historiaClinica.getFechaRegistro());
        historiaClinicaDTO.setUserRegistro(historiaClinica.getUserRegistro());
        historiaClinicaDTO.setSede(historiaClinica.getSede());

        return historiaClinicaDTO;
    }

    private HistoriaClinica mapearEntidad(HistoriaClinicaDTO historiaClinicaDTO){
        HistoriaClinica historiaClinica=new HistoriaClinica();

        historiaClinica.setNroDoc(historiaClinicaDTO.getNroDoc());
        historiaClinica.setRucEmpresa(historiaClinicaDTO.getRucEmpresa());
        historiaClinica.setRucContrata(historiaClinicaDTO.getRucContrata());
        historiaClinica.setFechaRegistro(historiaClinicaDTO.getFechaRegistro());
        historiaClinica.setUserRegistro(historiaClinicaDTO.getUserRegistro());
        historiaClinica.setSede(historiaClinicaDTO.getSede());

        return historiaClinica;
    }


    private HistoriaClinica actualizarHistoriaClinicaEntidad(HistoriaClinicaDTO historiaClinicaDTO,HistoriaClinica historiaClinica){

        historiaClinica.setNroDoc(historiaClinicaDTO.getNroDoc());
        historiaClinica.setRucEmpresa(historiaClinicaDTO.getRucEmpresa());
        historiaClinica.setRucContrata(historiaClinicaDTO.getRucContrata());
        historiaClinica.setFechaRegistro(historiaClinicaDTO.getFechaRegistro());
        historiaClinica.setUserRegistro(historiaClinicaDTO.getUserRegistro());
        historiaClinica.setSede(historiaClinicaDTO.getSede());

        return historiaClinica;
    }



}
