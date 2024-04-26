package backendhm.serviciosRest.models.azure.services;

import backendhm.serviciosRest.models.azure.dtos.EmailDTO;
import backendhm.serviciosRest.models.azure.dtos.RespuestaBackendDTO;

public interface IEmailService {
    RespuestaBackendDTO enviarCorreo(EmailDTO emailDTO);

    RespuestaBackendDTO usarCodigoGeneradoValidar(String emailUser, String codigoGenerado);

    RespuestaBackendDTO actualizarUsuario(String emailUser, String codigoGenerado);
}
