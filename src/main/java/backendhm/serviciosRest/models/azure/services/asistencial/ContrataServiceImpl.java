package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.ContrataDTO;
import backendhm.serviciosRest.models.azure.dtos.sistemaArchivos.EmpContDTO;
import backendhm.serviciosRest.models.azure.entity.asistencial.Contrata;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.asistencial.IContrataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContrataServiceImpl implements IContrataService{

    @Autowired
    private IContrataRepository contrataRepository;


    @Override
    public ContrataDTO crearContrata(ContrataDTO contrataDTO) {
        Contrata contrata=mapearEntidad(contrataDTO);

        Contrata nuevaContrata=contrataRepository.save(contrata);
        ContrataDTO contrataRespuesta=mapearDTO(nuevaContrata);

        return contrataRespuesta;
    }

    @Override
    public List<ContrataDTO> listadoContratas() {
        List<Contrata> listaContratas=contrataRepository.findAll();
        return listaContratas.stream().map(this::mapearDTO).collect(Collectors.toList());
    }


    @Override
    public ContrataDTO obtenerContrataPorRuc(String ruc) {
        Contrata contrata=contrataRepository.findById(ruc)
                .orElseThrow(()-> new ResourceNotFoundException("Contrata","ruc contrata",Long.parseLong(ruc)));
        return mapearDTO(contrata);
    }

    @Override
    public ContrataDTO actualizarContrata(ContrataDTO contrataDTO, String ruc) {
        Contrata contrata=contrataRepository.findById(ruc)
                .orElseThrow(()-> new ResourceNotFoundException("Contrata","ruc contrata",Long.parseLong(ruc)));

        Contrata contrataActual=contrataRepository.save(actualizarContrataEntidad(contrataDTO,contrata));
        return mapearDTO(contrataActual);
    }

    @Override
    public void eliminarContrata(String ruc) {
        Contrata contrata=contrataRepository.findById(ruc)
                .orElseThrow(()-> new ResourceNotFoundException("Contrata","ruc contrata",Long.parseLong(ruc)));
        contrataRepository.delete(contrata);
    }


    private EmpContDTO mapearDTOContrataPorUsername(Contrata contrata){

        EmpContDTO empContDTO=new EmpContDTO();

        empContDTO.setRuc(contrata.getId());
        empContDTO.setRazonSocial(contrata.getRazonContrata());

        return empContDTO;

    }

    private ContrataDTO mapearDTO(Contrata contrata){
        ContrataDTO contrataDTO=new ContrataDTO();

        contrataDTO.setRucContrata(contrata.getId());
        contrataDTO.setRazonContrata(contrata.getRazonContrata());
        contrataDTO.setEmailContrata(contrata.getEmailContrata());
        contrataDTO.setDireccionContrata(contrata.getDireccionContrata());
        contrataDTO.setTelefonoContrata(contrata.getTelefonoContrata());
        contrataDTO.setResponsableContrata(contrata.getResponsableContrata());

        return contrataDTO;
    }

    private Contrata mapearEntidad(ContrataDTO contrataDTO){
        Contrata contrata=new Contrata();

        contrata.setId(contrataDTO.getRucContrata());
        contrata.setRazonContrata(contrataDTO.getRazonContrata());
        contrata.setEmailContrata(contrataDTO.getEmailContrata());
        contrata.setDireccionContrata(contrataDTO.getDireccionContrata());
        contrata.setTelefonoContrata(contrataDTO.getTelefonoContrata());
        contrata.setResponsableContrata(contrataDTO.getResponsableContrata());

        return contrata;
    }

    private Contrata actualizarContrataEntidad(ContrataDTO contrataDTO,Contrata contrata){

        contrata.setRazonContrata(contrataDTO.getRazonContrata());
        contrata.setEmailContrata(contrataDTO.getEmailContrata());
        contrata.setDireccionContrata(contrataDTO.getDireccionContrata());
        contrata.setTelefonoContrata(contrataDTO.getTelefonoContrata());
        contrata.setResponsableContrata(contrataDTO.getResponsableContrata());

        return contrata;
    }
}
