package backendhm.serviciosRest.controller.azure;

import backendhm.serviciosRest.models.azure.dtos.ArchivoServidorDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;
import backendhm.serviciosRest.models.azure.services.IArchivoServidorService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/archivos")
@CrossOrigin
public class FileController {

    @Autowired
    private IArchivoServidorService archivoServidorService;

    private static JSONObject json=null;


    @GetMapping("/detalleArchivo/{hc}/{ta}")
    public ResponseEntity<ArchivoServidorDTO> obtenerArchivoDetalle(@PathVariable String hc, @PathVariable Long ta) {
        System.out.println("EL HC ES:"+hc);
        System.out.println("EL TA ES:"+ta);
        return ResponseEntity.ok(archivoServidorService.detalleArchivoServidor(hc,ta));
    }


    @GetMapping()
    public ResponseEntity<List<ArchivoServidorDTO>> obtenerListadoArchivoServidorDTO(){

        return ResponseEntity.ok(archivoServidorService.listadoArchivoServidor());
    }

    @GetMapping("/busquedaPorHC/{hc}")
    public ResponseEntity<List<ArchivoServidorDTO>> obtenerListadoArchivosPorHc(@PathVariable(name = "hc") String hc){

        return ResponseEntity.ok(archivoServidorService.listadoArchivoPorHC(hc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchivoServidorDTO> obtenerArchivoServidorPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(archivoServidorService.obtenerArchivoServidorPorID(id));
    }

    @PostMapping
    public ResponseEntity<ArchivoServidorDTO> guardarArchivoServidor(@Valid @RequestBody ArchivoServidorDTO archivoServidorDTO) {

        return new ResponseEntity<>(archivoServidorService.creararchivoServidor(archivoServidorDTO), HttpStatus.CREATED);

    }

    @PostMapping("/registrarArchivo")
    public ResponseEntity<RespuestaBackendDTO> guardarOActualizarArchivoServidor(@Valid @RequestBody ArchivoServidorDTO archivoServidorDTO) {

        return new ResponseEntity<>(archivoServidorService.registrarArchivoOActualizar(archivoServidorDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchivoServidorDTO> actualizarArchivoServidor(@Valid @RequestBody ArchivoServidorDTO archivoServidorDTO,
                                                          @PathVariable(name = "id") long id) {

        ArchivoServidorDTO archivoServidorDTOActualizado = archivoServidorService.actualizarArchivoServidor(archivoServidorDTO, id);
        return new ResponseEntity<>(archivoServidorDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminararchivoServidor(@PathVariable(name = "id") long id) {
        archivoServidorService.eliminarArchivoServidor(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }


}
