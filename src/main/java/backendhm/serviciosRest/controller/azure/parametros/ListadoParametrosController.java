package backendhm.serviciosRest.controller.azure.parametros;

import backendhm.serviciosRest.models.azure.dtos.parametros.ListaParametrosDTO;
import backendhm.serviciosRest.models.azure.services.parametros.IListaParametrosService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/listadoParametros")
@CrossOrigin
public class ListadoParametrosController {

    @Autowired
    private IListaParametrosService listaParametrosService;

    private static JSONObject json=null;

    @GetMapping()
    public ResponseEntity<List<ListaParametrosDTO>> obtenerListadoParametros(){

        return ResponseEntity.ok(listaParametrosService.listadoParametros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaParametrosDTO> obtenerListadoParametrosPorID(@PathVariable(name = "id") long id) {

        return ResponseEntity.ok(listaParametrosService.obtenerListadoParametrosPorID(id));
    }

    @PostMapping
    public ResponseEntity<ListaParametrosDTO> guardarListadoParametros(@Valid @RequestBody ListaParametrosDTO listaParametrosDTO) {

        return new ResponseEntity<>(listaParametrosService.crearListadoParametros(listaParametrosDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaParametrosDTO> actualizarListaParametros(@Valid @RequestBody ListaParametrosDTO listaParametrosDTO,
                                                          @PathVariable(name = "id") long id) {

        ListaParametrosDTO listaParametrosDTOActualizado = listaParametrosService.actualizarListaParametros(listaParametrosDTO, id);
        return new ResponseEntity<>(listaParametrosDTOActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarListaParametros(@PathVariable(name = "id") long id) {
        listaParametrosService.eliminarListaParametros(id);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }



}
