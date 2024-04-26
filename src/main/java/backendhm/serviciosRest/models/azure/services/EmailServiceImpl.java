package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.entity.Usuario;
import backendhm.serviciosRest.models.azure.repository.UserRepository;
import backendhm.serviciosRest.models.azure.repository.parametros.IRespuestaBackendRepository;
import backendhm.serviciosRest.models.azure.dtos.EmailDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.dtos.UsuarioDTO;
import backendhm.serviciosRest.models.azure.entity.RespuestaBackend;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements IEmailService{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    private IRespuestaBackendRepository respuestaBackendRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private UserRepository userRepository;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }
    @Override
    public RespuestaBackendDTO enviarCorreo(EmailDTO emailDTO) {
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailDTO.getDestinatario());
            helper.setSubject(emailDTO.getAsunto());

            // Procesar la plantilla Thymeleaf
            Context context = new Context();
            RespuestaBackend respuestaBackend=respuestaBackendRepository.generarCodigo(emailDTO.getDestinatario()).orElseThrow();
            context.setVariable("codigo", respuestaBackend.getId());
            String contenidoHtml = templateEngine.process("re_password", context);


            helper.setText(contenidoHtml, true);

            javaMailSender.send(message);
            respuestaBackendDTO.setId(Long.valueOf(1));
            respuestaBackendDTO.setMensaje("Se envio el codigo con exito!");
        } catch (Exception e) {
            respuestaBackendDTO.setId(Long.valueOf(0));
            respuestaBackendDTO.setMensaje("Error al enviar el codigo");
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
        return respuestaBackendDTO;
    }

    @Override
    public RespuestaBackendDTO usarCodigoGeneradoValidar(String emailUser, String codigoGenerado) {

        RespuestaBackend respuestaBackend=respuestaBackendRepository
                .usarCodigoGenerado(emailUser,codigoGenerado).orElseThrow();
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();
        respuestaBackendDTO.setId(respuestaBackend.getId());
        respuestaBackendDTO.setMensaje(respuestaBackend.getMensaje());

        return respuestaBackendDTO;
    }

    @Override
    public RespuestaBackendDTO actualizarUsuario(String emailUser, String password) {
        Usuario usuario=userRepository.detalleUsuario(emailUser).orElseThrow();
        UsuarioDTO usuarioDTO=mapearDTO(usuario);
        long id=usuarioDTO.getIdUser();
        usuarioDTO.setPassword(password);
        usuarioDTO=usuarioService.actualizarUsuario(usuarioDTO,id);
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();
        respuestaBackendDTO.setId(Long.valueOf(1));
        respuestaBackendDTO.setMensaje("Se actualizo con exito!");

        return respuestaBackendDTO;
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
}
