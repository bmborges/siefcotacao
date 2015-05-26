package br.com.sistema.siefcotacao.vo;

/**
 * Created by root on 11/05/15.
 */
public class Cpr_Cot_ConcorrenteVO {

    public static final String TABLE = "cpr_cot_concorrente";
    public static final String KEY_id_cot_concorrente = "id_cot_concorrente";
    public static final String KEY_nmconcorrente = "nmconcorrente";
    public static final String KEY_ativo = "ativo";
    public static final String KEY_nmoperador = "nmoperador";

    private Integer id_cot_concorrente;
    private String nmconcorrente;
    private String ativo;
    private String nmoperador;

    public Integer getId_cot_concorrente() {
        return id_cot_concorrente;
    }

    public void setId_cot_concorrente(Integer id_cot_concorrente) {
        this.id_cot_concorrente = id_cot_concorrente;
    }

    public String getNmconcorrente() {
        return nmconcorrente;
    }

    public void setNmconcorrente(String nmconcorrente) {
        this.nmconcorrente = nmconcorrente;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getNmoperador() {
        return nmoperador;
    }

    public void setNmoperador(String nmoperador) {
        this.nmoperador = nmoperador;
    }
}
