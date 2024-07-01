package backendhm.serviciosRest.models.spTrujilloNP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "BackendEntityHistoriaOcupacional")
public class BackendEntityHistoriaOcupacional  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "n_orden")
    private long n_orden;

    @Column(name = "cod_pa")
    private long codPa;

    @Column(name = "razon_empresa")
    private String razonEmpresa;

    @Column(name = "razon_contrata")
    private String razonContrata;

    @Column(name = "nom_ex")
    private String nomEx;

    @Column(name = "altura_po")
    private String alturaPo;

    @Column(name = "mineral_po")
    private String mineralPo;

    @Column(name = "fecha_apertura_po")
    private String fechaAperturaPo;

    @Column(name = "precio_po")
    private String precioPo;

    @Column(name = "estado_ex")
    private String estadoEx;

    @Column(name = "nom_examen")
    private String nomExamen;

    @Column(name = "cargo_de")
    private String cargoDe;

    @Column(name = "area_o")
    private String areaO;

    @Column(name = "n_medico")
    private String n_medico;

    @Column(name = "n_hora")
    private String n_hora;

    @Column(name = "tipo_pago")
    private String tipoPago;

    private Boolean n_fisttest;

    private Boolean n_psicosen;

    private Boolean n_testaltura;

    private long color;

    @Column(name = "gruposan")
    private String grupoSan;

    @Column(name = "grupofactorsan")
    private String grupoFactorSan;

    @Column(name = "cod_clinica")
    private String codClinica;

    @Column(name = "visual_compl")
    private Boolean visualCompl;

    @Column(name = "trab_calientes")
    private Boolean trabCalientes;

    @Column(name = "chkcovid1")
    private Boolean chk_covid1;

    @Column(name = "chkcovid2")
    private Boolean chk_covid2;

    @Column(name = "manip_alimentos")
    private Boolean manipAlimentos;

    @Column(name = "txtobserv1")
    private String textObserv1;

    @Column(name = "txtobserv2")
    private String textObserv2;

    @Column(name = "cod_sede")
    private String codSede;

    @Column(name = "tipo_prueba_covid")
    private String tipoPruebaCovid;

    @Column(name = "tipoprueba")
    private String tipoPrueba;

    @Column(name = "nombrehotel")
    private String nombreHotel;

    @Column(name = "protocolo")
    private String protocolo;

    @Column(name = "precio_adic")
    private String precioAdic;

    @Column(name = "autoriza")
    private String autoriza;

    @Column(name = "n_operacion")
    private String n_operacion;

    @Column(name = "herra_manuales")
    private Boolean herraManuales;

    @Column(name = "rxc_dorso_lumbar")
    private Boolean rxcDorsoLumbar;

    @Column(name = "rxc_lumbar")
    private Boolean rxcLumbar;

    @Column(name = "rxc_lumbosacra")
    private Boolean rxcLumbosacra;

    @Column(name = "rxc_plomos")
    private Boolean rxcPlomos;

    @Column(name = "mercurioo")
    private Boolean mercurioo;

}
