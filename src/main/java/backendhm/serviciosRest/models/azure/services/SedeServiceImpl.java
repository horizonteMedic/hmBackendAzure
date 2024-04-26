package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.SedeDTO;
import backendhm.serviciosRest.models.azure.entity.SedeHmWeb;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.ISedeHmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SedeServiceImpl implements ISedeService{

    @Autowired
    ISedeHmRepository sedeHmRepository;

    @Override
    public SedeDTO crearSede(SedeDTO sedeDTO) {
        SedeHmWeb sedeHmWeb=mapearEntidad(sedeDTO);

        SedeHmWeb nuevaSede=sedeHmRepository.save(sedeHmWeb);
        SedeDTO sedeRespuesta=mapearDTO(nuevaSede);

        return sedeRespuesta;
    }

    @Override
    public List<SedeDTO> listadoSedes() {
        List<SedeHmWeb> listaSedes=sedeHmRepository.findAll();
        return listaSedes.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public SedeDTO obtenerSedePorID(long id) {
        SedeHmWeb sedeHmWeb=sedeHmRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sede","id",id));
        return mapearDTO(sedeHmWeb);
    }

    @Override
    public SedeDTO actualizarSede(SedeDTO sedeDTO, long id) {
        SedeHmWeb sedeHmWeb=sedeHmRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sede","id",id));
        SedeHmWeb sedeActual=sedeHmRepository.save(actualizarSedeEntidad(sedeDTO,sedeHmWeb));
        return mapearDTO(sedeActual);
    }

    @Override
    public void eliminarSede(long id) {
        SedeHmWeb sedeHmWeb=sedeHmRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sede","id",id));
        sedeHmRepository.delete(sedeHmWeb);

    }

    private SedeDTO mapearDTO(SedeHmWeb sedeHmWeb){
        SedeDTO sedeDTO=new SedeDTO();

        sedeDTO.setId(sedeHmWeb.getId());
        sedeDTO.setNombreSede(sedeHmWeb.getNombreSede());
        sedeDTO.setEstado(sedeHmWeb.getEstado());
        sedeDTO.setFechaRegistro(sedeHmWeb.getFechaRegistro());
        sedeDTO.setUserRegistro(sedeHmWeb.getUserRegistro());
        sedeDTO.setFechaActualizacion(sedeHmWeb.getFechaActualizacion());
        sedeDTO.setUserActualizacion(sedeHmWeb.getUserActualizacion());
        sedeDTO.setCodigoSede(sedeHmWeb.getCodigoSede());

        return sedeDTO;
    }

    private SedeHmWeb mapearEntidad(SedeDTO sedeDTO){
        SedeHmWeb sedeHmWeb=new SedeHmWeb();

        sedeHmWeb.setNombreSede(sedeDTO.getNombreSede());
        sedeHmWeb.setEstado(sedeDTO.getEstado());
        sedeHmWeb.setFechaRegistro(sedeDTO.getFechaRegistro());
        sedeHmWeb.setUserRegistro(sedeDTO.getUserRegistro());
        sedeHmWeb.setFechaActualizacion(sedeDTO.getFechaActualizacion());
        sedeHmWeb.setUserActualizacion(sedeDTO.getUserActualizacion());
        sedeHmWeb.setCodigoSede(sedeDTO.getCodigoSede());

        return sedeHmWeb;
    }

    private SedeHmWeb actualizarSedeEntidad(SedeDTO sedeDTO,SedeHmWeb sedeHmWeb){

        sedeHmWeb.setNombreSede(sedeDTO.getNombreSede());
        sedeHmWeb.setEstado(sedeDTO.getEstado());
        sedeHmWeb.setFechaRegistro(sedeDTO.getFechaRegistro());
        sedeHmWeb.setUserRegistro(sedeDTO.getUserRegistro());
        sedeHmWeb.setFechaActualizacion(sedeDTO.getFechaActualizacion());
        sedeHmWeb.setUserActualizacion(sedeDTO.getUserActualizacion());
        sedeHmWeb.setCodigoSede(sedeDTO.getCodigoSede());

        return sedeHmWeb;
    }

}
