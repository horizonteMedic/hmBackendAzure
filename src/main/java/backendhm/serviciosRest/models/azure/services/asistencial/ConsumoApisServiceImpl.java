package backendhm.serviciosRest.models.azure.services.asistencial;

import backendhm.serviciosRest.models.azure.dtos.asistencial.AbstractClient;
import backendhm.serviciosRest.models.azure.dtos.asistencial.ConsultaReniecDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ConsumoApisServiceImpl extends AbstractClient implements IConsumoApisService {
    protected ConsumoApisServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public ConsultaReniecDTO consumoApis(String dni) {
        String url=baseUrl+dni;
        HttpEntity<Void> requestEntity = new HttpEntity<>(this.buildAuthToken("horizontemedic_dtcm9"));
        ResponseEntity<ConsultaReniecDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity , ConsultaReniecDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Successfully user creation: {}", response.getBody().getRespuesta());
            return response.getBody();
        }
        log.error("Error in user creation - httpStatus was: {}", response.getStatusCode());
        throw new RuntimeException("Error");
    }


}
