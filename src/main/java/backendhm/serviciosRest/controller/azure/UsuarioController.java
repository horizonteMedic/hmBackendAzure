package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.UsuarioDTO;
import backendhm.serviciosRest.models.azure.services.IUsuarioService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    private static JSONObject json=null;



    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> obtenerListadoUsuarios(){
        return  ResponseEntity.ok(usuarioService.listadoUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorID(id));
    }



    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO,
                                                @PathVariable(name = "id") long id) {

        UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(usuarioDTO, id);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable(name = "id") long id) {
        usuarioService.eliminarUsuario(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
