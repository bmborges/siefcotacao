package br.com.sistema.siefcotacao.vo;

import java.sql.Timestamp;

/**
 * Created by root on 11/05/15.
 */
public class Cpr_Cot_Conc_ProdVO {

    public static final String TABLE = "cpr_cot_conc_prod";
    public static final String KEY_id_cot_conc_prod = "id_cot_conc_prod";
    public static final String KEY_id_cot_concorrente = "id_cot_concorrente";
    public static final String KEY_cdproduto = "cdproduto";
    public static final String KEY_valor = "valor";
    public static final String KEY_dt_cotacao = "dt_cotacao";
    public static final String KEY_nmoperador = "nmoperador";
    public static final String KEY_promocao_conc = "promocao_conc";


    private Integer id_cot_conc_prod;
    private Integer id_cot_concorrente;
    private Long cdproduto;
    private Float valor;
    private Timestamp dt_cotacao;
    private String nmoperador;
    private String nmproduto;
    private String nm_p_pesquisa;
    private String promocao_conc;

    public Integer getId_cot_conc_prod() {
        return id_cot_conc_prod;
    }

    public void setId_cot_conc_prod(Integer id_cot_conc_prod) {
        this.id_cot_conc_prod = id_cot_conc_prod;
    }

    public Integer getId_cot_concorrente() {
        return id_cot_concorrente;
    }

    public void setId_cot_concorrente(Integer id_cot_concorrente) {
        this.id_cot_concorrente = id_cot_concorrente;
    }

    public Long getCdproduto() {
        return cdproduto;
    }

    public void setCdproduto(Long cdproduto) {
        this.cdproduto = cdproduto;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Timestamp getDt_cotacao() {
        return dt_cotacao;
    }

    public void setDt_cotacao(Timestamp dt_cotacao) {
        this.dt_cotacao = dt_cotacao;
    }

    public String getNmoperador() {
        return nmoperador;
    }

    public void setNmoperador(String nmoperador) {
        this.nmoperador = nmoperador;
    }

    public String getNmproduto() {
        return nmproduto;
    }

    public void setNmproduto(String nmproduto) {
        this.nmproduto = nmproduto;
    }

    public String getNm_p_pesquisa() {
        return nm_p_pesquisa;
    }

    public void setNm_p_pesquisa(String nm_p_pesquisa) {
        this.nm_p_pesquisa = nm_p_pesquisa;
    }

    public String getPromocao_conc() {
        return promocao_conc;
    }

    public void setPromocao_conc(String promocao_conc) {
        this.promocao_conc = promocao_conc;
    }
}
