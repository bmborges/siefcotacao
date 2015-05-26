package br.com.sistema.siefcotacao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.sistema.siefcotacao.vo.Cpr_Cot_Conc_ProdVO;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_ConcorrenteVO;
import br.com.sistema.siefcotacao.vo.Prd_ProdutoVO;

/**
 * Created by root on 11/05/15.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "sief.db";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_PRD_PRODUTO = "CREATE TABLE IF NOT EXISTS " + Prd_ProdutoVO.TABLE + "(" +
                Prd_ProdutoVO.KEY_cdproduto + " INTEGER PRIMARY KEY ," +
                Prd_ProdutoVO.KEY_nmproduto + " TEXT ," +
                Prd_ProdutoVO.KEY_nm_p_pesquisa + " TEXT ," +
                Prd_ProdutoVO.KEY_ativo + " TEXT )";

        try {
            db.execSQL(CREATE_TABLE_PRD_PRODUTO);
        } catch (Exception e){

        }


        String CREATE_TABLE_CPR_COT_CONCORRENTE = "CREATE TABLE IF NOT EXISTS " + Cpr_Cot_ConcorrenteVO.TABLE + "(" +
                Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente + " INTEGER PRIMARY KEY ," +
                Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente + " TEXT ," +
                Cpr_Cot_ConcorrenteVO.KEY_ativo + " TEXT ," +
                Cpr_Cot_ConcorrenteVO.KEY_nmoperador + " TEXT )";

        try {
            db.execSQL(CREATE_TABLE_CPR_COT_CONCORRENTE);
        } catch (Exception e){

        }

        String CREATE_TABLE_CPR_COT_CONC_PROD = "CREATE TABLE IF NOT EXISTS " + Cpr_Cot_Conc_ProdVO.TABLE + "(" +
                Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod + " INTEGER PRIMARY KEY ," +
                Cpr_Cot_Conc_ProdVO.KEY_id_cot_concorrente + " INTEGER ," +
                Cpr_Cot_Conc_ProdVO.KEY_cdproduto + " INTEGER ," +
                Cpr_Cot_Conc_ProdVO.KEY_valor + " FLOAT ," +
                Cpr_Cot_Conc_ProdVO.KEY_dt_cotacao + " STRING ," +
                Cpr_Cot_Conc_ProdVO.KEY_nmoperador + " TEXT," +
                Cpr_Cot_Conc_ProdVO.KEY_promocao_conc + " TEXT )";

        try {
            db.execSQL(CREATE_TABLE_CPR_COT_CONC_PROD);
        } catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL("DROP TABLE IF EXISTS " + Prd_ProdutoVO.TABLE);
        //db.execSQL("DROP TABLE IF EXISTS " + Cpr_Cot_ConcorrenteVO.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Cpr_Cot_Conc_ProdVO.TABLE);

        onCreate(db);
    }
}
