package br.com.sistema.siefcotacao;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sistema.siefcotacao.dao.Cpr_Cot_Conc_ProdDao;
import br.com.sistema.siefcotacao.dao.Cpr_Cot_ConcorrenteDao;
import br.com.sistema.siefcotacao.dao.Prd_ProdutoDao;
import br.com.sistema.siefcotacao.util.VerificaPreferences;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_Conc_ProdVO;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_ConcorrenteVO;
import br.com.sistema.siefcotacao.vo.Prd_ProdutoVO;
import br.com.sistema.siefcotacao.volley.CustomJsonObjectRequest;


public class SyncDataActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    String endServer;
    private Map<String, String> params = new HashMap<String, String>();
    private ProgressDialog pDialog;
    int jumpTime = 0;
    private VerificaPreferences verificaPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_data);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        verificaPreferences = new VerificaPreferences(this);
        endServer = verificaPreferences.getEndServer();

        //pDialog.setIndeterminate(true);

        Button button = (Button) findViewById(R.id.button_syncdata);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pDialog != null){
                    pDialog.dismiss();
                }

                pDialog = new ProgressDialog(SyncDataActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setProgress(0);

                pDialog.show();

                ClientToServer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_sync_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private final class PositiveSettingsClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            startActivity(new Intent(getApplicationContext(), SettingsSystemActivity.class));
        }
    }
    private void SyncProduto(){

        params.remove("tabela");
        params.put("tabela", Prd_ProdutoVO.TABLE);

        //RequestQueue rq = Volley.newRequestQueue(SyncDataActivity.this);
        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(Request.Method.POST,
                endServer,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("App", "Success:" + response);
                        //pDialog.hide();
                        jumpTime += 20;
                        pDialog.setProgress(jumpTime);

                        gravaDados(response.toString(), Prd_ProdutoVO.TABLE);
                        SyncConcorrente();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("App", error.getMessage());
                        pDialog.hide();
                        Toast.makeText(getApplicationContext(),"Erro ao Sincronizar Tabela " + Prd_ProdutoVO.TABLE ,Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(cjor);
        //cjor.setTag("App");
        //rq.add(cjor);
    }
    private void SyncConcorrente(){

        params.remove("tabela");
        params.put("tabela", Cpr_Cot_ConcorrenteVO.TABLE);

        //RequestQueue rq = Volley.newRequestQueue(SyncDataActivity.this);
        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(Request.Method.POST,
                endServer,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("App", "Success:" + response);
                        //pDialog.hide();
                        jumpTime += 20;
                        pDialog.setProgress(jumpTime);
                        gravaDados(response.toString(), Cpr_Cot_ConcorrenteVO.TABLE);
                        SyncConcProd();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("App", error.getMessage());
                        pDialog.hide();
                        Toast.makeText(getApplicationContext(),"Erro ao Sincronizar Tabela " + Cpr_Cot_ConcorrenteVO.TABLE ,Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(cjor);
        //cjor.setTag("App");
        //rq.add(cjor);
    }
    private void SyncConcProd(){

        params.remove("tabela");
        params.put("tabela", Cpr_Cot_Conc_ProdVO.TABLE);

        //RequestQueue rq = Volley.newRequestQueue(SyncDataActivity.this);
        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(Request.Method.POST,
                endServer,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("App", "Success:" + response);
                        pDialog.hide();
                        jumpTime += 20;
                        pDialog.setProgress(jumpTime);
                        gravaDados(response.toString(), Cpr_Cot_Conc_ProdVO.TABLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("App", error.getMessage());
                        pDialog.hide();
                        Toast.makeText(getApplicationContext(),"Erro ao Sincronizar Tabela " + Cpr_Cot_Conc_ProdVO.TABLE ,Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(cjor);
        //cjor.setTag("App");
        //rq.add(cjor);
    }
    private void gravaDados(String response, String tabela) {
        JSONObject responseObj = null;

        try {
            Gson gson = new Gson();
            //Log.i("Script", response);
            responseObj = new JSONObject(response);
            JSONArray ListObj = null;
            Prd_ProdutoDao dao_produto = null;
            Cpr_Cot_ConcorrenteDao dao_concorrente = null;
            Cpr_Cot_Conc_ProdDao dao_concprod = null;
            if(tabela.equals(Prd_ProdutoVO.TABLE)){
                ListObj = responseObj.getJSONArray(tabela);

                dao_produto = new Prd_ProdutoDao(this);
                dao_produto.deleteAll();
            } else if (tabela.equals(Cpr_Cot_ConcorrenteVO.TABLE)){
                ListObj = responseObj.getJSONArray(tabela);

                dao_concorrente = new Cpr_Cot_ConcorrenteDao(this);
                dao_concorrente.deleteAll();
            } else if (tabela.equals(Cpr_Cot_Conc_ProdVO.TABLE)){
                ListObj = responseObj.getJSONArray(tabela);

                dao_concprod = new Cpr_Cot_Conc_ProdDao(this);
                //dao_concprod.deleteAll();
            }
            //Log.i("Script ListObj", " Tamanho " + ListObj.length());

            for (int i = 0; i < ListObj.length(); i++) {
                //get the country information JSON object
                String listInfo = ListObj.getJSONObject(i).toString();
                //create java object from the JSON object

                if(tabela.equals(Prd_ProdutoVO.TABLE)) {

                    Prd_ProdutoVO produto = gson.fromJson(listInfo, Prd_ProdutoVO.class);
                    int cdproduto = dao_produto.insert(produto);

                } else if (tabela.equals(Cpr_Cot_ConcorrenteVO.TABLE)){

                    Cpr_Cot_ConcorrenteVO concorrente = gson.fromJson(listInfo,Cpr_Cot_ConcorrenteVO.class);
                    int idconcorrente = dao_concorrente.insert(concorrente);

                } else if (tabela.equals(Cpr_Cot_Conc_ProdVO.TABLE)){

                    Cpr_Cot_Conc_ProdVO conc_prod = gson.fromJson(listInfo,Cpr_Cot_Conc_ProdVO.class);
                    int idconcprod = dao_concprod.insert(conc_prod);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void ServerToClient() {
        params.clear();
        params.put("request", "servertoclient");
        SyncProduto();

    }
    private void ClientToServer(){
        Gson gson = new Gson();
        params.clear();
        params.put("request", "clienttoserver");

        final Cpr_Cot_Conc_ProdDao dao_conc_prod = new Cpr_Cot_Conc_ProdDao(this);
        List rowItems = new ArrayList<Cpr_Cot_Conc_ProdVO>();
        rowItems = dao_conc_prod.getAllList();

        params.put("tabela", Cpr_Cot_Conc_ProdVO.TABLE);
        params.put("dados", gson.toJson(rowItems));

        //Log.d("App", "params:" + params);

        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(Request.Method.POST,
                endServer,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("App", "Success ClientToServer:" + response);
                        jumpTime += 20;
                        pDialog.setProgress(jumpTime);
                        //pDialog.hide();
                        //if(apagarDados.equals("V")) {
                            dao_conc_prod.deleteAll();
                        //}
                        ServerToClient();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("App", error.getMessage());
                        pDialog.hide();
                        Toast.makeText(getApplicationContext(),"Erro ao Sincronizar Tabela " + Cpr_Cot_Conc_ProdVO.TABLE +": Verifique o End. Servidor",Toast.LENGTH_LONG).show();
                    }
                });

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(cjor);

        //cjor.setTag("App");
        //rq.add(cjor);

    }
}
