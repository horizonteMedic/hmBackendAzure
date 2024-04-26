package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.auth.user.Role;
import backendhm.serviciosRest.models.azure.entity.Empleado;
import backendhm.serviciosRest.models.azure.entity.Usuario;
import backendhm.serviciosRest.models.azure.errors.ResourceNotFoundException;
import backendhm.serviciosRest.models.azure.repository.IEmpleadoRepository;
import backendhm.serviciosRest.models.azure.repository.UserRepository;
import backendhm.serviciosRest.models.azure.dtos.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDTO> listadoUsuario() {
        List<Usuario> listaUsuarios=userRepository.findAll();
        return listaUsuarios.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorID(long id) {
        Usuario usuario=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id user",id));
        return mapearDTO(usuario);
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, long id) {
        Usuario usuario=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id user",id));
        Usuario usuarioActual=userRepository.save(actualizarUsuarioEntidad(usuarioDTO,usuario));

        return mapearDTO(usuarioActual);
    }

    @Override
    public void eliminarUsuario(long id) {
        Usuario usuario=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id user",id));
        userRepository.delete(usuario);
    }

    private UsuarioDTO mapearDTO(Usuario usuario){
        UsuarioDTO usuarioDTO=new UsuarioDTO();

        usuarioDTO.setIdUser(usuario.getIdUser());

        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setEstado(usuario.getEstado());
        usuarioDTO.setRuc(usuario.getRuc());
        usuarioDTO.setId_empleado(usuario.getEmpleado().getId());

        return usuarioDTO;
    }

    private Usuario mapearEntidad(UsuarioDTO usuarioDTO){
        Usuario usuario=new Usuario();
        Empleado empleado=empleadoRepository.findById(usuarioDTO.getId_empleado()).orElseThrow();

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setRuc(usuarioDTO.getRuc());
        usuario.setEmpleado(empleado);

        return usuario;
    }

    private Usuario actualizarUsuarioEntidad(UsuarioDTO usuarioDTO,Usuario usuario){

        Empleado empleado=empleadoRepository.findById(usuarioDTO.getId_empleado()).orElseThrow();

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setRole(Role.USER);
        usuario.setEmpleado(empleado);
        usuario.setRuc(usuarioDTO.getRuc());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        return usuario;
    }


}
