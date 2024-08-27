package backendhm.serviciosRest.models.azure.services.campaña;

import backendhm.serviciosRest.models.azure.dtos.RolDTO;
import backendhm.serviciosRest.models.azure.dtos.campaña.CIE10CAMPAÑADTO;

import java.util.List;

public interface ICampañaService {

    CIE10CAMPAÑADTO crearCie10(CIE10CAMPAÑADTO cie10CAMPAÑADTO);
    List<CIE10CAMPAÑADTO> listadoCie10();

}
