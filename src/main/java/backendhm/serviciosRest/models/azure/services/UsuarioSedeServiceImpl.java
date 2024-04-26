package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.entity.SedeHmWeb;
import backendhm.serviciosRest.models.azure.entity.Usuario;
import backendhm.serviciosRest.models.azure.entity.UsuarioSede;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.ISedeHmRepository;
import backendhm.serviciosRest.models.azure.repository.IUsuarioSedeRepository;
import backendhm.serviciosRest.models.azure.repository.UserRepository;
import backendhm.serviciosRest.models.azure.dtos.UsuarioSedeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioSedeServiceImpl implements IUsuarioSedeService {

    @Autowired
    private IUsuarioSedeRepository usuarioSedeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ISedeHmRepository sedeHmRepository;

    @Override
    public UsuarioSedeDTO crearUsuarioSede(UsuarioSedeDTO usuarioSedeDTO) {
        UsuarioSede usuarioSede=mapearEntidad(usuarioSedeDTO);

        UsuarioSede nuevoUsuarioSede=usuarioSedeRepository.save(usuarioSede);
        UsuarioSedeDTO usuarioSedeDTORespuesta=mapearDTO(nuevoUsuarioSede);

        return usuarioSedeDTORespuesta;
    }

    @Override
    public List<UsuarioSedeDTO> listadoUsuarioSedes() {
        List<UsuarioSede> listaUsuarioSedes=usuarioSedeRepository.findAll();
        return listaUsuarioSedes.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioSedeDTO obtenerUsuarioSedePorIDUSER(long id) {
        UsuarioSede usuarioSede=usuarioSedeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario sede","id",id));
        return mapearDTO(usuarioSede);
    }

    @Override
    public List<UsuarioSedeDTO> ListaoUsuarioSedePorIDUSER(long id) {
        List<UsuarioSede> listaUsuarioSedes=usuarioSedeRepository.listadoUsuariosSedesPorIdUSER(id).orElseThrow(()-> new ResourceNotFoundException("Usuario sede","id",id));
        return listaUsuarioSedes.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioSedeDTO actualizarUsuarioSede(UsuarioSedeDTO usuarioSedeDTO, long id) {
        UsuarioSede usuarioSede=usuarioSedeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario sede","id",id));
        UsuarioSede usuarioSedeActual=usuarioSedeRepository.save(actualizarUsuarioSedeEntidad(usuarioSedeDTO,usuarioSede));
        return mapearDTO(usuarioSedeActual);
    }

    @Override
    public void eliminarUsuarioSede(long id) {
        UsuarioSede usuarioSede=usuarioSedeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario sede","id",id));
        usuarioSedeRepository.delete(usuarioSede);
    }

    private UsuarioSedeDTO mapearDTO(UsuarioSede usuarioSede){
        UsuarioSedeDTO usuarioSedeDTO=new UsuarioSedeDTO();
        Usuario usuario=userRepository.findById(usuarioSede.getUsuario().getIdUser()).orElseThrow();
        SedeHmWeb sedeHmWeb=sedeHmRepository.findById(usuarioSede.getSede().getId()).orElseThrow();

        usuarioSedeDTO.setIdUsuarioSede(usuarioSede.getIdUsuarioSede());
        usuarioSedeDTO.setUserRegistro(usuarioSede.getUserRegistro());
        usuarioSedeDTO.setFechaRegistro(usuarioSede.getFechaRegistro());
        usuarioSedeDTO.setId_sede(usuarioSede.getSede().getId());
        usuarioSedeDTO.setId_user(usuarioSede.getUsuario().getIdUser());
        usuarioSedeDTO.setUsername(usuario.getUsername());
        usuarioSedeDTO.setSede(sedeHmWeb.getNombreSede());

        return usuarioSedeDTO;
    }

    private UsuarioSede mapearEntidad(UsuarioSedeDTO usuarioSedeDTO){
        UsuarioSede usuarioSede=new UsuarioSede();
        Usuario usuario=userRepository.findById(usuarioSedeDTO.getId_user()).orElseThrow();
        SedeHmWeb sedeHmWeb=sedeHmRepository.findById(usuarioSedeDTO.getId_sede()).orElseThrow();

        usuarioSede.setUserRegistro(usuarioSedeDTO.getUserRegistro());
        usuarioSede.setFechaRegistro(usuarioSedeDTO.getFechaRegistro());
        usuarioSede.setUsuario(usuario);
        usuarioSede.setSede(sedeHmWeb);


        return usuarioSede;
    }

    private UsuarioSede actualizarUsuarioSedeEntidad(UsuarioSedeDTO usuarioSedeDTO,UsuarioSede usuarioSede){
        Usuario usuario=userRepository.findById(usuarioSedeDTO.getId_user()).orElseThrow();
        SedeHmWeb sedeHmWeb=sedeHmRepository.findById(usuarioSedeDTO.getId_sede()).orElseThrow();

        usuarioSede.setUserRegistro(usuarioSedeDTO.getUserRegistro());
        usuarioSede.setFechaRegistro(usuarioSedeDTO.getFechaRegistro());
        usuarioSede.setUsuario(usuario);
        usuarioSede.setSede(sedeHmWeb);

        return usuarioSede;
    }
}
