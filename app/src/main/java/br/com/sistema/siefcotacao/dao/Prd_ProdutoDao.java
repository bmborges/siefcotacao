package br.com.sistema.siefcotacao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.sistema.siefcotacao.db.DBHelper;
import br.com.sistema.siefcotacao.vo.Prd_ProdutoVO;

/**
 * Created by root on 11/05/15.
 */
public class Prd_ProdutoDao {
    private DBHelper dbHelper;

    public Prd_ProdutoDao(Context context) {
        dbHelper = new DBHelper(context);
    }
    public int insert(Prd_ProdutoVO vo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Prd_ProdutoVO.KEY_cdproduto,vo.getCdproduto());
        values.put(Prd_ProdutoVO.KEY_nmproduto,vo.getNmproduto());
        values.put(Prd_ProdutoVO.KEY_nm_p_pesquisa,vo.getNm_p_pesquisa());
        values.put(Prd_ProdutoVO.KEY_ativo,vo.getAtivo());

        long cdproduto = db.insert(Prd_ProdutoVO.TABLE, null, values);
        Log.d("App", "Insert "+ Prd_ProdutoVO.TABLE + " : " + values);

        db.close();
        return (int) cdproduto;
    }

    public void delete(int cdproduto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Prd_ProdutoVO.TABLE, Prd_ProdutoVO.KEY_cdproduto + "= ?", new String[]{String.valueOf(cdproduto)});
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Prd_ProdutoVO.TABLE,null,null);
        db.close();
    }
    public void update(Prd_ProdutoVO vo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Prd_ProdutoVO.KEY_nmproduto,vo.getNmproduto());
        values.put(Prd_ProdutoVO.KEY_nm_p_pesquisa,vo.getNm_p_pesquisa());
        values.put(Prd_ProdutoVO.KEY_ativo,vo.getAtivo());

        db.update(Prd_ProdutoVO.TABLE, values, Prd_ProdutoVO.KEY_cdproduto + " = ?", new String[]{String.valueOf(vo.getCdproduto())});
        db.close();
    }
    public List getAllList(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery =  "SELECT  " +
                Prd_ProdutoVO.KEY_cdproduto + "," +
                Prd_ProdutoVO.KEY_nmproduto + "," +
                Prd_ProdutoVO.KEY_nm_p_pesquisa + "," +
                Prd_ProdutoVO.KEY_ativo +
                " FROM " + Prd_ProdutoVO.TABLE;

        List produtoList = new ArrayList<Prd_ProdutoVO>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Prd_ProdutoVO produto = new Prd_ProdutoVO();
                produto.setCdproduto(cursor.getLong(cursor.getColumnIndex(Prd_ProdutoVO.KEY_cdproduto)));
                produto.setNmproduto(cursor.getString(cursor.getColumnIndex(Prd_ProdutoVO.KEY_nmproduto)));
                produtoList.add(produto);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return produtoList;
    }

    public Prd_ProdutoVO getById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Prd_ProdutoVO.KEY_cdproduto + "," +
                Prd_ProdutoVO.KEY_nmproduto + "," +
                Prd_ProdutoVO.KEY_nm_p_pesquisa + "," +
                Prd_ProdutoVO.KEY_ativo +
                " FROM " + Prd_ProdutoVO.TABLE
                + " WHERE " +
                Prd_ProdutoVO.KEY_cdproduto + "=?";

        int iCount =0;
        Prd_ProdutoVO produto = new Prd_ProdutoVO();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                produto.setCdproduto(cursor.getLong(cursor.getColumnIndex(Prd_ProdutoVO.KEY_cdproduto)));
                produto.setNmproduto(cursor.getString(cursor.getColumnIndex(Prd_ProdutoVO.KEY_nmproduto)));
                produto.setNm_p_pesquisa(cursor.getString(cursor.getColumnIndex(Prd_ProdutoVO.KEY_nm_p_pesquisa)));
                produto.setAtivo(cursor.getString(cursor.getColumnIndex(Prd_ProdutoVO.KEY_ativo)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return produto;
    }

}
