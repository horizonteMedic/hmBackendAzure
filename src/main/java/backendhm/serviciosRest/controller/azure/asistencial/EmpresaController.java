package backendhm.serviciosRest.controller.azure.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.EmpresaDTO;
import backendhm.serviciosRest.models.azure.services.asistencial.IEmpresaService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/empresa")
@CrossOrigin
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

    private static JSONObject json=null;


    @GetMapping()
    public ResponseEntity<List<EmpresaDTO>> obtenerListadoEmpresas(){
        return  ResponseEntity.ok(empresaService.listadoEmpresas());
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<EmpresaDTO> obtenerEmpresaPorRuc(@PathVariable(name = "ruc") String ruc) {

        return ResponseEntity.ok(empresaService.obtenerEmpresaPorRuc(ruc));
    }


    @PostMapping
    public ResponseEntity<EmpresaDTO> guardarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO) {

        return new ResponseEntity<>(empresaService.crearEmpresa(empresaDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{ruc}")
    public ResponseEntity<EmpresaDTO> actualizarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO,
                                                          @PathVariable(name = "ruc") String ruc) {

        EmpresaDTO empresaActualizada = empresaService.actualizarEmpresa(empresaDTO, ruc);
        return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{ruc}")
    public ResponseEntity<String> eliminarEmpresa(@PathVariable(name = "ruc") String ruc) {
        empresaService.eliminarEmpresa(ruc);
        json=new JSONObject();
        return new ResponseEntity<>(json.put("message","Registro Eliminado exitosamente!").toString(),HttpStatus.OK);
    }



}
