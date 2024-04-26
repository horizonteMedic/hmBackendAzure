package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.PrivilegioRolDTO;
import backendhm.serviciosRest.models.azure.services.IPrivilegioRolService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/privilegioRol")
@CrossOrigin
public class PrivilegioRolController {

    @Autowired
    private IPrivilegioRolService privilegioRolService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<PrivilegioRolDTO>> obtenerListadoPrivilegioRoles(){
        return  ResponseEntity.ok(privilegioRolService.listadoPrivilegioRol());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivilegioRolDTO> obtenerPrivilegioRolPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(privilegioRolService.obtenerPrivilegioRolPorID(id));
    }


    @PostMapping
    public ResponseEntity<PrivilegioRolDTO> guardarPrivilegioRol(@Valid @RequestBody PrivilegioRolDTO privilegioRolDTO) {

        return new ResponseEntity<>(privilegioRolService.crearPrivilegioRol(privilegioRolDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PrivilegioRolDTO> actualizarPrivilegioRol(@Valid @RequestBody PrivilegioRolDTO privilegioRolDTO,
                                                @PathVariable(name = "id") long id) {

        PrivilegioRolDTO privilegioRolDTOActualizado = privilegioRolService.actualizarPrivilegioRol(privilegioRolDTO, id);
        return new ResponseEntity<>(privilegioRolDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable(name = "id") long id) {
        privilegioRolService.eliminarPrivilegioRol(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }



}
