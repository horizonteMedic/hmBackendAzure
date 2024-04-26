package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.EmailDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v01/st/email")
@CrossOrigin
public class EmailController {
    @Autowired
    private IEmailService emailService;

    @PostMapping("/enviar-correo")
    public ResponseEntity<RespuestaBackendDTO> enviarCorreo(@RequestBody EmailDTO emailDTO) {
        RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();
        try {
            return new ResponseEntity<>(emailService.enviarCorreo(emailDTO), HttpStatus.OK);
        } catch (Exception e) {
            respuestaBackendDTO.setId(Long.valueOf(0));
            respuestaBackendDTO.setMensaje("Error al enviar el correo");
            return new ResponseEntity<>(respuestaBackendDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usarCodigo/{email}/{codigo}")
    public ResponseEntity<RespuestaBackendDTO> usarCodigo(@PathVariable String email, @PathVariable String codigo) {

        return ResponseEntity.ok(emailService.usarCodigoGeneradoValidar(email,codigo));
    }

    @GetMapping("/actualizarPassword/{email}/{password}")
    public ResponseEntity<RespuestaBackendDTO> cambiarpassword(@PathVariable String email, @PathVariable String password) {
        try{
            return new  ResponseEntity<>(emailService.actualizarUsuario(email,password),HttpStatus.OK);
        }
        catch (Exception e){
            RespuestaBackendDTO respuestaBackendDTO=new RespuestaBackendDTO();
            respuestaBackendDTO.setId(Long.valueOf(0));
            respuestaBackendDTO.setMensaje("Error al actualizar " + e.toString());
            return new ResponseEntity<>(respuestaBackendDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
