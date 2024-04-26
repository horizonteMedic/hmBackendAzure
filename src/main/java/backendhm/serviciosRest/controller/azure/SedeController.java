package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.SedeDTO;
import backendhm.serviciosRest.models.azure.services.ISedeService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/sede")
@CrossOrigin
public class SedeController {

    @Autowired
    private ISedeService sedeService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<SedeDTO>> obtenerListadoSedes(){

        return  ResponseEntity.ok(sedeService.listadoSedes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> obtenerSedePorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(sedeService.obtenerSedePorID(id));
    }


    @PostMapping
    public ResponseEntity<SedeDTO> guardarSede(@Valid @RequestBody SedeDTO sedeDTO) {

        return new ResponseEntity<>(sedeService.crearSede(sedeDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> actualizarSede(@Valid @RequestBody SedeDTO sedeDTO,
                                                @PathVariable(name = "id") long id) {

        SedeDTO sedeActualizada = sedeService.actualizarSede(sedeDTO, id);
        return new ResponseEntity<>(sedeActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSede(@PathVariable(name = "id") long id) {
        sedeService.eliminarSede(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }



}
