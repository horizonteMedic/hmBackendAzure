package backendhm.serviciosRest.models.spTrujilloSD.Service;

import backendhm.serviciosRest.models.spTrujilloSD.dto.RespuestaBackendDTOTSD;

public interface IRespuestaBackendServiceSD {
    RespuestaBackendDTOTSD busquedaDniPorNOrden(long nOrden);
}
