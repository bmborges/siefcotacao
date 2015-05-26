package br.com.sistema.siefcotacao.vo;

/**
 * Created by root on 08/05/15.
 */
public class Prd_ProdutoVO {

    public static final String TABLE = "prd_produto";
    public static final String KEY_cdproduto = "cdproduto";
    public static final String KEY_nmproduto = "nmproduto";
    public static final String KEY_nm_p_pesquisa = "nm_p_pesquisa";
    public static final String KEY_ativo = "ativo";

    private Long cdproduto;
    private String nmproduto;
    private String nm_p_pesquisa;
    private String ativo;

    public Long getCdproduto() {
        return cdproduto;
    }

    public void setCdproduto(Long cdproduto) {
        this.cdproduto = cdproduto;
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

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
