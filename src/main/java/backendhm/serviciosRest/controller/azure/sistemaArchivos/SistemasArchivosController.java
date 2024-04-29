package backendhm.serviciosRest.controller.azure.sistemaArchivos;

import backendhm.serviciosRest.models.azure.dtos.RolDTO;
import backendhm.serviciosRest.models.azure.dtos.SedeDTO;
import backendhm.serviciosRest.models.azure.dtos.SedePorUserDTO;
import backendhm.serviciosRest.models.azure.services.sistemaArchivos.IHistorialPacienteSPNPService;
import backendhm.serviciosRest.models.spTrujilloNP.dto.HistorialPacienteSPNPDto;
import backendhm.serviciosRest.models.spTrujilloNP.dto.RequestHistorialPacienteSPTNP;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/ct/sistemaArchivos")
@CrossOrigin
public class SistemasArchivosController {

    @Autowired
    private IHistorialPacienteSPNPService historialPacienteSPNPService;

    @PostMapping("/listadoHistorialPacientesConFiltros")
    public ResponseEntity<List<HistorialPacienteSPNPDto>> multiservidores(@Valid @RequestBody RequestHistorialPacienteSPTNP requestHistorialPacienteSPTNP) {

        return new ResponseEntity<>(historialPacienteSPNPService.listadoHistorialPaciente(requestHistorialPacienteSPTNP), HttpStatus.OK);

    }

    @GetMapping("/sedePorUsuario/{username}")
    public ResponseEntity<List<SedePorUserDTO>> obtenerSedeOorUsuario(@PathVariable(name = "username") String username) {

        return ResponseEntity.ok(historialPacienteSPNPService.listadoSedesPorUsuario(username));
    }
}
