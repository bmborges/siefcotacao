package br.com.sistema.siefcotacao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.sistema.siefcotacao.db.DBHelper;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_Conc_ProdVO;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_ConcorrenteVO;

/**
 * Created by root on 11/05/15.
 */
public class Cpr_Cot_ConcorrenteDao {
    private DBHelper dbHelper;

    public Cpr_Cot_ConcorrenteDao(Context context) {
        dbHelper = new DBHelper(context);
    }
    public int insert(Cpr_Cot_ConcorrenteVO vo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente,vo.getId_cot_concorrente());
        values.put(Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente,vo.getNmconcorrente());
        values.put(Cpr_Cot_ConcorrenteVO.KEY_ativo,vo.getAtivo());
        values.put(Cpr_Cot_ConcorrenteVO.KEY_nmoperador,vo.getNmoperador());

        long id_cot_concorrente = db.insert(Cpr_Cot_ConcorrenteVO.TABLE, null, values);
        Log.d("App", "Insert " + Cpr_Cot_ConcorrenteVO.TABLE + " : " + values);
        db.close();
        return (int) id_cot_concorrente;
    }

    public void delete(int id_cot_concorrente){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Cpr_Cot_ConcorrenteVO.TABLE, Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente + "= ?", new String[]{String.valueOf(id_cot_concorrente)});
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Cpr_Cot_ConcorrenteVO.TABLE,null,null);
        db.close();
    }
    public void update(Cpr_Cot_ConcorrenteVO vo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente,vo.getId_cot_concorrente());
        values.put(Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente,vo.getNmconcorrente());
        values.put(Cpr_Cot_ConcorrenteVO.KEY_ativo,vo.getAtivo());
        values.put(Cpr_Cot_ConcorrenteVO.KEY_nmoperador,vo.getNmoperador());

        db.update(Cpr_Cot_ConcorrenteVO.TABLE, values, Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente + " = ?", new String[]{String.valueOf(vo.getId_cot_concorrente())});
        db.close();
    }
    public List getAllList(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery =  "SELECT DISTINCT " +
                Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente + "," +
                Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente + "," +
                Cpr_Cot_ConcorrenteVO.KEY_ativo +
                " FROM " + Cpr_Cot_ConcorrenteVO.TABLE +
                " INNER JOIN " + Cpr_Cot_Conc_ProdVO.TABLE + " USING (ID_COT_CONCORRENTE)";

        List concorrenteList = new ArrayList<Cpr_Cot_ConcorrenteVO>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Cpr_Cot_ConcorrenteVO concorrente = new Cpr_Cot_ConcorrenteVO();
                concorrente.setId_cot_concorrente(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente)));
                concorrente.setNmconcorrente(cursor.getString(cursor.getColumnIndex(Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente)));
                concorrenteList.add(concorrente);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return concorrenteList;
    }

    public Cpr_Cot_ConcorrenteVO getById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente + "," +
                Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente +
                " FROM " + Cpr_Cot_ConcorrenteVO.TABLE
                + " WHERE " +
                Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente + "=?";

        int iCount =0;
        List concorrenteList = new ArrayList<Cpr_Cot_ConcorrenteVO>();

        Cpr_Cot_ConcorrenteVO concorrenteVO = new Cpr_Cot_ConcorrenteVO();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                concorrenteVO.setId_cot_concorrente(cursor.getInt(cursor.getColumnIndex(Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente)));
                concorrenteVO.setNmconcorrente(cursor.getString(cursor.getColumnIndex(Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente)));
                concorrenteVO.setAtivo(cursor.getString(cursor.getColumnIndex(Cpr_Cot_ConcorrenteVO.KEY_ativo)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return concorrenteVO;
    }

}
