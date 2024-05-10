package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.UsuarioRolDTO;
import backendhm.serviciosRest.models.azure.repository.UserRepository;
import backendhm.serviciosRest.models.azure.entity.Rol;
import backendhm.serviciosRest.models.azure.entity.Usuario;
import backendhm.serviciosRest.models.azure.entity.UsuarioRol;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IRolRepository;
import backendhm.serviciosRest.models.azure.repository.IUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioRolServiceImpl implements IUsuarioRolService {

    @Autowired
    private IUsuarioRol usuarioRolRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UsuarioRolDTO crearUsuarioRol(UsuarioRolDTO usuarioRolDTO) {
        UsuarioRol usuarioRol=mapearEntidad(usuarioRolDTO);

        UsuarioRol nuevoUsuario=usuarioRolRepository.save(usuarioRol);
        UsuarioRolDTO usuarioRolDTORespuesta= mapearDTO(nuevoUsuario);

        return usuarioRolDTORespuesta;
    }

    @Override
    public List<UsuarioRolDTO> listadoUsuarioRol() {
        List<UsuarioRol> listaUsuarioRoles=usuarioRolRepository.findAll();
        return listaUsuarioRoles.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioRolDTO> listadoUsuarioRolPorIdUser(long idUser) {
        List<UsuarioRol> listaUsuarioRoles=usuarioRolRepository.listadoUsuariosRolPorIdUser(idUser).orElseThrow();
        return listaUsuarioRoles.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioRolDTO obtenerUsuarioRolPorID(long id) {
        UsuarioRol usuarioRol=usuarioRolRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("UsuarioRol","id_user_rol",id));
        return mapearDTO(usuarioRol);
    }

    @Override
    public UsuarioRolDTO actualizarUsuarioRolDTO(UsuarioRolDTO usuarioRolDTO, long id) {
        UsuarioRol usuarioRol=usuarioRolRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("UsuarioRol","id_user_rol",id));

        UsuarioRol usuarioActual=usuarioRolRepository.save(actualizarUsuarioRol(usuarioRolDTO,usuarioRol));
        return mapearDTO(usuarioActual);
    }

    @Override
    public void eliminarUsuarioRolDTO(long id) {
        UsuarioRol usuarioRol=usuarioRolRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("UsuarioRol","id_user_rol",id));
        usuarioRolRepository.delete(usuarioRol);
    }

    private UsuarioRolDTO mapearDTO(UsuarioRol usuarioRol){
        UsuarioRolDTO usuarioRolDTO=new UsuarioRolDTO();

        usuarioRolDTO.setIdUserRol(usuarioRol.getIdUserRol());
        usuarioRolDTO.setEstado(usuarioRol.getEstado());
        usuarioRolDTO.setFechaRegistro(usuarioRol.getFechaRegistro());
        usuarioRolDTO.setUserRegistro(usuarioRol.getUserRegistro());
        usuarioRolDTO.setFechaUpt(usuarioRol.getFechaUpt());
        usuarioRolDTO.setUserUpt(usuarioRol.getUserUpt());
        usuarioRolDTO.setId_rol(usuarioRol.getRol().getIdRol());
        usuarioRolDTO.setId_user(usuarioRol.getUsuario().getIdUser());

        return usuarioRolDTO;
    }

    private UsuarioRol mapearEntidad(UsuarioRolDTO usuarioRolDTO){
        UsuarioRol usuarioRol=new UsuarioRol();

        Usuario usuario= new Usuario();
        usuario= userRepository.findById(usuarioRolDTO.getId_user()).
                orElseThrow(()->new ResourceNotFoundException("UsuarioRol","id_user",usuarioRolDTO.getId_user()));

        Rol rol= new Rol();
        rol= rolRepository.findById(usuarioRolDTO.getId_rol()).
                orElseThrow(()-> new ResourceNotFoundException("UsuarioRol","id_rol", usuarioRolDTO.getId_rol()));

        usuarioRol.setEstado(usuarioRolDTO.getEstado());
        usuarioRol.setFechaRegistro(usuarioRolDTO.getFechaRegistro());
        usuarioRol.setUserRegistro(usuarioRolDTO.getUserRegistro());
        usuarioRol.setFechaUpt(usuarioRolDTO.getFechaUpt());
        usuarioRol.setUserUpt(usuarioRolDTO.getUserUpt());
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        return usuarioRol;
    }

    private UsuarioRol actualizarUsuarioRol(UsuarioRolDTO usuarioRolDTO, UsuarioRol usuarioRol){
        Usuario usuario=userRepository.findById(usuarioRolDTO.getId_user()).
                orElseThrow(()-> new ResourceNotFoundException("UsuarioRol","id_usuario", usuarioRolDTO.getId_user()));

        Rol rol=rolRepository.findById(usuarioRolDTO.getId_rol()).
                orElseThrow(()-> new ResourceNotFoundException("UsuarioRol","id_rol", usuarioRolDTO.getId_rol()));

        usuarioRol.setEstado(usuarioRolDTO.getEstado());
        usuarioRol.setFechaRegistro(usuarioRolDTO.getFechaRegistro());
        usuarioRol.setUserRegistro(usuarioRolDTO.getUserRegistro());
        usuarioRol.setFechaUpt(usuarioRolDTO.getFechaUpt());
        usuarioRol.setUserUpt(usuarioRolDTO.getUserUpt());
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        return usuarioRol;
    }
}
