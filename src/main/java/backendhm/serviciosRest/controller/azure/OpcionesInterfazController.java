package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.OpcionesInterfazDTO;
import backendhm.serviciosRest.models.azure.services.IOpcionInterfazService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/opcionesInterfaz")
@CrossOrigin
public class OpcionesInterfazController {
    @Autowired
    private IOpcionInterfazService opcionInterfazService;
    private static JSONObject json=null;

    @GetMapping()
    public ResponseEntity<List<OpcionesInterfazDTO>> obtenerListadoOpcionesInterfaz(){

        return ResponseEntity.ok(opcionInterfazService.listadoOpcionesInterfaz());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpcionesInterfazDTO> obtenerOpcionesInterfazPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(opcionInterfazService.obtenerOpcionInterdazPorID(id));
    }

    @PostMapping
    public ResponseEntity<OpcionesInterfazDTO> guardarOpcionesInterfaz(@Valid @RequestBody OpcionesInterfazDTO opcionesInterfazDTO) {

        return new ResponseEntity<>(opcionInterfazService.crearOpcionInterfaz(opcionesInterfazDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<OpcionesInterfazDTO> actualizarOpcionesInterfaz(@Valid @RequestBody OpcionesInterfazDTO opcionesInterfazDTO,
                                                          @PathVariable(name = "id") long id) {

        OpcionesInterfazDTO opcionesInterfazDTOActualizado = opcionInterfazService.actualizarOpcionInterfaz(opcionesInterfazDTO, id);
        return new ResponseEntity<>(opcionesInterfazDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOpcionesInterfaz(@PathVariable(name = "id") long id) {
        opcionInterfazService.eliminarOpcionInterfaz(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }

}
