package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.ConsultaReniecDTO;

public interface IConsumoApisService {

    ConsultaReniecDTO consumoApis(String dni);

}
