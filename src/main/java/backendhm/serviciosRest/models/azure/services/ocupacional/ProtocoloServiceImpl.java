package backendhm.serviciosRest.models.azure.services.ocupacional;

import backendhm.serviciosRest.models.azure.dtos.Ocupacional.ProtocoloDTO;
import backendhm.serviciosRest.models.azure.entity.ocupacional.Protocolo;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.ocupacional.IProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProtocoloServiceImpl implements IProtocoloService{

    @Autowired
    private IProtocoloRepository protocoloRepository;

    @Override
    public ProtocoloDTO crearProtocolo(ProtocoloDTO protocoloDTO) {
        Protocolo protocolo=mapearEntidad(protocoloDTO);

        Protocolo nuevoProtocolo=protocoloRepository.save(protocolo);
        ProtocoloDTO protocoloRespuesta=mapearDTO(nuevoProtocolo);

        return protocoloRespuesta;
    }

    @Override
    public List<ProtocoloDTO> listadoProtocolos() {
        List<Protocolo> listarProtocolos=protocoloRepository.findAll();
        return listarProtocolos.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ProtocoloDTO obtenerProtocoloPorID(long id) {
        Protocolo protocolo=protocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("protocolos","id",id));
        return mapearDTO(protocolo);
    }

    @Override
    public ProtocoloDTO actualizarProtocolo(ProtocoloDTO protocoloDTO, long id) {
        Protocolo protocolo=protocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("protocolo","id",id));

        Protocolo protocoloActual=protocoloRepository.save(actualizarProtocoloEntidad(protocoloDTO,protocolo));
        return mapearDTO(protocoloActual);
    }

    @Override
    public void eliminarProtocolo(long id) {
        Protocolo protocolo=protocoloRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("protocolo","id",id));
        protocoloRepository.delete(protocolo);
    }

    private ProtocoloDTO mapearDTO(Protocolo protocolo){
        ProtocoloDTO protocoloDTO=new ProtocoloDTO();

        protocoloDTO.setIdProtocolo(protocolo.getIdProtocolo());
        protocoloDTO.setNombreProtocolo(protocolo.getNombreProtocolo());
        protocoloDTO.setEstado(protocolo.getEstado());
        protocoloDTO.setPrecio(protocolo.getPrecio());
        protocoloDTO.setObservacion(protocolo.getObservacion());
        protocoloDTO.setRucEmpresa(protocolo.getRucEmpresa());
        protocoloDTO.setFechaRegistro(protocolo.getFechaRegistro());
        protocoloDTO.setUserRegistro(protocolo.getUserRegistro());
        protocoloDTO.setFechaActualizacion(protocolo.getFechaActualizacion());
        protocoloDTO.setUserActualizacion(protocolo.getUserActualizacion());

        return protocoloDTO;
    }

    private Protocolo mapearEntidad(ProtocoloDTO protocoloDTO){
        Protocolo protocolo=new Protocolo();

        protocolo.setNombreProtocolo(protocoloDTO.getNombreProtocolo());
        protocolo.setEstado(protocoloDTO.getEstado());
        protocolo.setPrecio(protocoloDTO.getPrecio());
        protocolo.setObservacion(protocoloDTO.getObservacion());
        protocolo.setRucEmpresa(protocoloDTO.getRucEmpresa());
        protocolo.setFechaRegistro(protocoloDTO.getFechaRegistro());
        protocolo.setUserRegistro(protocoloDTO.getUserRegistro());
        protocolo.setFechaActualizacion(protocoloDTO.getFechaActualizacion());
        protocolo.setUserActualizacion(protocoloDTO.getUserActualizacion());

        return protocolo;
    }


    private Protocolo actualizarProtocoloEntidad(ProtocoloDTO protocoloDTO, Protocolo protocolo){
        protocolo.setNombreProtocolo(protocoloDTO.getNombreProtocolo());
        protocolo.setEstado(protocoloDTO.getEstado());
        protocolo.setPrecio(protocoloDTO.getPrecio());
        protocolo.setObservacion(protocoloDTO.getObservacion());
        protocolo.setRucEmpresa(protocoloDTO.getRucEmpresa());
        protocolo.setFechaRegistro(protocoloDTO.getFechaRegistro());
        protocolo.setUserRegistro(protocoloDTO.getUserRegistro());
        protocolo.setFechaActualizacion(protocoloDTO.getFechaActualizacion());
        protocolo.setUserActualizacion(protocoloDTO.getUserActualizacion());
        return protocolo;
    }

}
