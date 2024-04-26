package backendhm.serviciosRest.controller.azure.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.ContrataDTO;
import backendhm.serviciosRest.models.azure.services.asistencial.IContrataService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/contrata")
@CrossOrigin
public class ContrataController {

    @Autowired
    IContrataService contrataService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<ContrataDTO>> obtenerListadoContratas(){
        return  ResponseEntity.ok(contrataService.listadoContratas());
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<ContrataDTO> obtenerContrataPorRuc(@PathVariable(name = "ruc") String ruc) {

        return ResponseEntity.ok(contrataService.obtenerContrataPorRuc(ruc));
    }


    @PostMapping
    public ResponseEntity<ContrataDTO> guardarContrata(@Valid @RequestBody ContrataDTO contrataDTO) {

        return new ResponseEntity<>(contrataService.crearContrata(contrataDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{ruc}")
    public ResponseEntity<ContrataDTO> actualizarContrata(@Valid @RequestBody ContrataDTO contrataDTO,
                                                @PathVariable(name = "ruc") String ruc) {

        ContrataDTO contrataActualizada = contrataService.actualizarContrata(contrataDTO, ruc);
        return new ResponseEntity<>(contrataActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{ruc}")
    public ResponseEntity<String> eliminarContrata(@PathVariable(name = "ruc") String ruc) {
        contrataService.eliminarContrata(ruc);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
