package backendhm.serviciosRest.models.azure.dtos.Ocupacional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackendHistoriaOcupacionalDTO {
    private long n_orden;

    private long codPa;

    private String razonEmpresa;

    private String razonContrata;

    private String nomEx;

    private String alturaPo;

    private String mineralPo;

    private String fechaAperturaPo;

    private String precioPo;

    private String estadoEx;

    private String nomExamen;

    private String cargoDe;

    private String areaO;

    private String n_medico;

    private String n_hora;

    private String tipoPago;

    private Boolean n_fisttest;

    private Boolean n_psicosen;

    private Boolean n_testaltura;

    private long color;

    private String grupoSan;

    private String grupoFactorSan;

    private String codClinica;

    private Boolean visualCompl;

    private Boolean trabCalientes;

    private Boolean chk_covid1;

    private Boolean chk_covid2;

    private Boolean manipAlimentos;

    private String textObserv1;

    private String textObserv2;

    private String codSede;

    private String tipoPruebaCovid;

    private String tipoPrueba;

    private String nombreHotel;

    private String protocolo;

    private String precioAdic;

    private String autoriza;

    private String n_operacion;

    private Boolean herraManuales;

    private Boolean rxcDorsoLumbar;

    private Boolean rxcKLumbar;

    private Boolean rxcLumbosacra;

    private Boolean rxcPlomos;

    private Boolean mercurioo;
}
