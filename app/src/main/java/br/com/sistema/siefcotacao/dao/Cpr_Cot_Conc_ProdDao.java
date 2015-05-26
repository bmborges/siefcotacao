package br.com.sistema.siefcotacao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sistema.siefcotacao.db.DBHelper;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_Conc_ProdVO;
import br.com.sistema.siefcotacao.vo.Prd_ProdutoVO;

/**
 * Created by root on 11/05/15.
 */
public class Cpr_Cot_Conc_ProdDao {
    private DBHelper dbHelper;

    public Cpr_Cot_Conc_ProdDao(Context context) {
        dbHelper = new DBHelper(context);
    }
    public int insert(Cpr_Cot_Conc_ProdVO vo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod,vo.getId_cot_conc_prod());
        values.put(Cpr_Cot_Conc_ProdVO.KEY_id_cot_concorrente,vo.getId_cot_concorrente());
        values.put(Cpr_Cot_Conc_ProdVO.KEY_cdproduto,vo.getCdproduto());
        values.put(Cpr_Cot_Conc_ProdVO.KEY_valor,vo.getValor());
        values.put(Cpr_Cot_Conc_ProdVO.KEY_dt_cotacao, String.valueOf(vo.getDt_cotacao()));
        values.put(Cpr_Cot_Conc_ProdVO.KEY_nmoperador, vo.getNmoperador());
        values.put(Cpr_Cot_Conc_ProdVO.KEY_promocao_conc, vo.getPromocao_conc());

        long id_cot_concorrente = db.insert(Cpr_Cot_Conc_ProdVO.TABLE, null, values);
        //Log.d("App", "Insert "+ Cpr_Cot_Conc_ProdVO.TABLE + " : " + values);
        db.close();
        return (int) id_cot_concorrente;
    }

    public void delete(int id_cot_concorrente){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Cpr_Cot_Conc_ProdVO.TABLE, Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod + "= ?", new String[]{String.valueOf(id_cot_concorrente)});
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(Cpr_Cot_Conc_ProdVO.TABLE, null, null);
        } catch (Exception e){

        }
        db.close();
    }
    public void update(Cpr_Cot_Conc_ProdVO vo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod,vo.getId_cot_conc_prod());
        //values.put(Cpr_Cot_Conc_ProdVO.KEY_id_cot_concorrente,vo.getId_cot_concorrente());
        //values.put(Cpr_Cot_Conc_ProdVO.KEY_cdproduto,vo.getCdproduto());
        if (vo.getValor() != null) {
            values.put(Cpr_Cot_Conc_ProdVO.KEY_valor, vo.getValor());
        }
        //values.put(Cpr_Cot_Conc_ProdVO.KEY_dt_cotacao,vo.getDt_cotacao());
        //values.put(Cpr_Cot_Conc_ProdVO.KEY_nmoperador,vo.getNmoperador());
        if (vo.getPromocao_conc() != null) {
            values.put(Cpr_Cot_Conc_ProdVO.KEY_promocao_conc, vo.getPromocao_conc());
        }

        db.update(Cpr_Cot_Conc_ProdVO.TABLE, values, Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod + " = ?", new String[]{String.valueOf(vo.getId_cot_conc_prod())});
        db.close();
        Log.d("App", "Update " + Cpr_Cot_Conc_ProdVO.TABLE + ": " + values + " WHERE " + Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod + " = " + vo.getId_cot_conc_prod());
    }
    public List getAllList(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery =  "SELECT * FROM " + Cpr_Cot_Conc_ProdVO.TABLE;

        List concProdList = new ArrayList<Cpr_Cot_Conc_ProdVO>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Cpr_Cot_Conc_ProdVO conc_prod = new Cpr_Cot_Conc_ProdVO();
                conc_prod.setId_cot_conc_prod(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod)));
                conc_prod.setId_cot_concorrente(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_id_cot_concorrente)));
                conc_prod.setCdproduto(cursor.getLong(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_cdproduto)));
                conc_prod.setValor(cursor.getFloat(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_valor)));
                //conc_prod.setDt_cotacao(cursor.getString(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_dt_cotacao)));
                conc_prod.setPromocao_conc(cursor.getString(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_promocao_conc)));
                concProdList.add(conc_prod);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return concProdList;
    }
    public Cpr_Cot_Conc_ProdVO getAllList(Cpr_Cot_Conc_ProdVO vo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery =  "SELECT * FROM " + Cpr_Cot_Conc_ProdVO.TABLE +
                " WHERE " +
                Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(vo.getId_cot_conc_prod())});
        // looping through all rows and adding to list

        Cpr_Cot_Conc_ProdVO conc_prod = new Cpr_Cot_Conc_ProdVO();
        if (cursor.moveToFirst()) {
            do {
                conc_prod.setId_cot_conc_prod(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod)));
                conc_prod.setId_cot_concorrente(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_id_cot_concorrente)));
                conc_prod.setCdproduto(cursor.getLong(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_cdproduto)));
                conc_prod.setValor(cursor.getFloat(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_valor)));
                conc_prod.setPromocao_conc(cursor.getString(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_promocao_conc)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

//        Log.d("App", "Select " + Cpr_Cot_Conc_ProdVO.TABLE + ": WHERE " + Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod + " = " + vo.getId_cot_conc_prod() + " VALOR " + conc_prod.getValor());

        return conc_prod;
    }

    public List getAllByIdConcorrente(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + Cpr_Cot_Conc_ProdVO.TABLE
                + " INNER JOIN PRD_PRODUTO USING (CDPRODUTO)" +
                " WHERE " +
                Cpr_Cot_Conc_ProdVO.KEY_id_cot_concorrente + "= ?" +
                " ORDER BY " + Prd_ProdutoVO.KEY_nm_p_pesquisa;

        int iCount =0;
        List concProdList = new ArrayList<Cpr_Cot_Conc_ProdVO>();


        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                Cpr_Cot_Conc_ProdVO conc_prodVO = new Cpr_Cot_Conc_ProdVO();
                conc_prodVO.setId_cot_conc_prod(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_id_cot_conc_prod)));
                conc_prodVO.setId_cot_concorrente(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_id_cot_concorrente)));
                conc_prodVO.setCdproduto(cursor.getLong(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_cdproduto)));
                conc_prodVO.setValor(cursor.getFloat(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_valor)));
                //conc_prodVO.setDt_cotacao(cursor.getString(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_dt_cotacao)));
                conc_prodVO.setNmproduto(cursor.getString(cursor.getColumnIndex(Prd_ProdutoVO.KEY_nmproduto)));
                conc_prodVO.setNm_p_pesquisa(cursor.getString(cursor.getColumnIndex(Prd_ProdutoVO.KEY_nm_p_pesquisa)));
                concProdList.add(conc_prodVO);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return concProdList;
    }

    public String getDtCotacao() throws ParseException {
        String sReturn = "";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery =  "SELECT DISTINCT DT_COTACAO FROM " + Cpr_Cot_Conc_ProdVO.TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                sReturn = cursor.getString(cursor.getColumnIndex(Cpr_Cot_Conc_ProdVO.KEY_dt_cotacao));
            } while (cursor.moveToNext());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date date = sdf.parse(sReturn);
        DateFormat dateFormatNeeded = new SimpleDateFormat("dd/MM/yyyy");
        sReturn = dateFormatNeeded.format(date);


        cursor.close();
        db.close();

        return sReturn;
    }

}
