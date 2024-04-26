package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.UsuarioSedeDTO;
import backendhm.serviciosRest.models.azure.services.IUsuarioSedeService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/usuarioSede")
@CrossOrigin
public class UsuarioSedeController {

    @Autowired
    private IUsuarioSedeService usuarioSedeService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<UsuarioSedeDTO>> obtenerListadoUsuariosSedes(){

        return  ResponseEntity.ok(usuarioSedeService.listadoUsuarioSedes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioSedeDTO> obtenerUsuarioSedePorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(usuarioSedeService.obtenerUsuarioSedePorIDUSER(id));
    }


    @GetMapping("/listadoPorBusquedaIDUSER/{id_user}")
    public ResponseEntity<List<UsuarioSedeDTO>> obtenerListadoUsuariosSedesporIDUSER(@PathVariable(name = "id_user") long id_user){

        return  ResponseEntity.ok(usuarioSedeService.ListaoUsuarioSedePorIDUSER(id_user));
    }

    @PostMapping
    public ResponseEntity<UsuarioSedeDTO> guardarUsuarioSede(@Valid @RequestBody UsuarioSedeDTO usuarioSedeDTO) {

        return new ResponseEntity<>(usuarioSedeService.crearUsuarioSede(usuarioSedeDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioSedeDTO> actualizarUsuarioSede(@Valid @RequestBody UsuarioSedeDTO usuarioSedeDTO,
                                                  @PathVariable(name = "id") long id) {

        UsuarioSedeDTO usuarioSedeDTOActualizado = usuarioSedeService.actualizarUsuarioSede(usuarioSedeDTO, id);
        return new ResponseEntity<>(usuarioSedeDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuarioSede(@PathVariable(name = "id") long id) {
        usuarioSedeService.eliminarUsuarioSede(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }



}
