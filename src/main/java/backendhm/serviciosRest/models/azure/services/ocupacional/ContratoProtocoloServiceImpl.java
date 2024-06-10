package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ContratoProtocoloDTO;
import backendhm.serviciosRest.models.azure.entity.ocupacional.ContratoProtocolo;
import backendhm.serviciosRest.models.azure.entity.ocupacional.Protocolo;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IContratoProtocoloRepository;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoProtocoloServiceImpl implements IContratoProtocoloService{

    @Autowired
    private IContratoProtocoloRepository contratoProtocoloRepository;

    @Autowired
    private IProtocoloRepository protocoloRepository;

    @Override
    public ContratoProtocoloDTO crearContratoProtocolo(ContratoProtocoloDTO contratoProtocoloDTO) {
        ContratoProtocolo contratoProtocolo=mapearENTIDAD(contratoProtocoloDTO);

        ContratoProtocolo nuevoContratoProtocolo=contratoProtocoloRepository.save(contratoProtocolo);
        ContratoProtocoloDTO contratoProtocoloDTORespusta=mapearDTO(nuevoContratoProtocolo);

        return contratoProtocoloDTORespusta;
    }

    @Override
    public List<ContratoProtocoloDTO> listadoContratosProtocolos() {
        List<ContratoProtocolo> listarContratoProtocolo=contratoProtocoloRepository.findAll();
        return listarContratoProtocolo.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ContratoProtocoloDTO obtenerContratoProtocoloPorID(long id) {
        ContratoProtocolo contratoProtocolo=contratoProtocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contrato Protocolo","id",id));
        return mapearDTO(contratoProtocolo);
    }

    @Override
    public ContratoProtocoloDTO actualizarContratoProtocolo(ContratoProtocoloDTO contratoProtocoloDTO, long id) {
        ContratoProtocolo contratoProtocolo=contratoProtocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("contrato protocolo","id",id));

        ContratoProtocolo contratoProtocoloActual=contratoProtocoloRepository.save(actualizarContratosProtocolos(contratoProtocoloDTO,contratoProtocolo));
        return mapearDTO(contratoProtocoloActual);
    }

    @Override
    public void eliminarContratoProtocolo(long id) {
        ContratoProtocolo contratoProtocolo=contratoProtocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contrato protocolo","id",id));
        contratoProtocoloRepository.delete(contratoProtocolo);
    }


    ContratoProtocoloDTO mapearDTO(ContratoProtocolo contratoProtocolo){
        ContratoProtocoloDTO contratoProtocoloDTO=new ContratoProtocoloDTO();

        contratoProtocoloDTO.setIdContratoProtocolo(contratoProtocolo.getIdContratoProtocolo());
        contratoProtocoloDTO.setId_protocolo(contratoProtocolo.getProtocolo_hm().getIdProtocolo());
        contratoProtocoloDTO.setPrecio(contratoProtocolo.getPrecio());
        contratoProtocoloDTO.setRucContrata(contratoProtocolo.getRucContrata());
        contratoProtocoloDTO.setFechaRegistro(contratoProtocolo.getFechaRegistro());
        contratoProtocoloDTO.setUserRegistro(contratoProtocolo.getUserRegistro());
        contratoProtocoloDTO.setFechaActualizacion(contratoProtocolo.getFechaActualizacion());
        contratoProtocoloDTO.setUserActualizacion(contratoProtocolo.getUserActualizacion());

        return  contratoProtocoloDTO;
    }

    ContratoProtocolo mapearENTIDAD(ContratoProtocoloDTO contratoProtocoloDTO){
        ContratoProtocolo contratoProtocolo=new ContratoProtocolo();
        Protocolo protocolo=protocoloRepository.findById(contratoProtocoloDTO.getId_protocolo()).orElseThrow();

        contratoProtocolo.setProtocolo_hm(protocolo);
        contratoProtocolo.setPrecio(contratoProtocoloDTO.getPrecio());
        contratoProtocolo.setRucContrata(contratoProtocoloDTO.getRucContrata());
        contratoProtocolo.setFechaRegistro(contratoProtocoloDTO.getFechaRegistro());
        contratoProtocolo.setUserRegistro(contratoProtocoloDTO.getUserRegistro());
        contratoProtocolo.setFechaActualizacion(contratoProtocoloDTO.getFechaActualizacion());
        contratoProtocolo.setUserActualizacion(contratoProtocoloDTO.getUserActualizacion());


        return contratoProtocolo;

    }


    ContratoProtocolo actualizarContratosProtocolos(ContratoProtocoloDTO contratoProtocoloDTO,ContratoProtocolo contratoProtocolo){
        Protocolo protocolo=protocoloRepository.findById(contratoProtocoloDTO.getId_protocolo()).orElseThrow();

        contratoProtocolo.setProtocolo_hm(protocolo);
        contratoProtocolo.setPrecio(contratoProtocoloDTO.getPrecio());
        contratoProtocolo.setRucContrata(contratoProtocoloDTO.getRucContrata());
        contratoProtocolo.setFechaRegistro(contratoProtocoloDTO.getFechaRegistro());
        contratoProtocolo.setUserRegistro(contratoProtocoloDTO.getUserRegistro());
        contratoProtocolo.setFechaActualizacion(contratoProtocoloDTO.getFechaActualizacion());
        contratoProtocolo.setUserActualizacion(contratoProtocoloDTO.getUserActualizacion());


        return contratoProtocolo;

    }


}
