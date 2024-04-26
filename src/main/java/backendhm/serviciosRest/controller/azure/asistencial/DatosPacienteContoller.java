package backendhm.serviciosRest.controller.azure.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.DatosPacienteDTO;
import backendhm.serviciosRest.models.azure.services.asistencial.IDatosPacienteService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/datosPaciente")
@CrossOrigin
public class DatosPacienteContoller {

    @Autowired
    private IDatosPacienteService datosPacienteService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<DatosPacienteDTO>> obtenerListadoDatosPacientes(){
        return  ResponseEntity.ok(datosPacienteService.listadoContratasDatosPacientes());
    }

    @GetMapping("/{nroDoc}")
    public ResponseEntity<DatosPacienteDTO> obtenerDatosPacientePorNumDoc(@PathVariable(name = "nroDoc") String nroDoc) {

        return ResponseEntity.ok(datosPacienteService.obtenerDatosPacientePorNumDoc(nroDoc));
    }


    @PostMapping
    public ResponseEntity<DatosPacienteDTO> guardarDatosPaciente(@Valid @RequestBody DatosPacienteDTO datosPacienteDTO) {

        return new ResponseEntity<>(datosPacienteService.crearDatosPaciente(datosPacienteDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{nroDoc}")
    public ResponseEntity<DatosPacienteDTO> actualizarDatosPaciente(@Valid @RequestBody DatosPacienteDTO datosPacienteDTO,
                                                        @PathVariable(name = "nroDoc") String nroDoc) {

        DatosPacienteDTO datosPacienteActualizado = datosPacienteService.actualizarDatosPaciente(datosPacienteDTO, nroDoc);
        return new ResponseEntity<>(datosPacienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{nroDoc}")
    public ResponseEntity<String> eliminarDatosPaciente(@PathVariable(name = "nroDoc") String nroDoc) {
        datosPacienteService.eliminarDatosPaciente(nroDoc);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }


}
