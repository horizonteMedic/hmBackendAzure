package backendhm.serviciosRest.controller.azure;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v01/demo")
public class DemoController {

    @PostMapping(value = "lademo")
    public String DemoController()
    {
        return "Bienvenido a la api con restricciones bearer, actualizado";

    }

}
