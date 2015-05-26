package br.com.sistema.siefcotacao;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.siefcotacao.adapters.CustomListViewConcAdapter;
import br.com.sistema.siefcotacao.adapters.CustomListViewProdAdapter;
import br.com.sistema.siefcotacao.dao.Cpr_Cot_Conc_ProdDao;
import br.com.sistema.siefcotacao.dao.Cpr_Cot_ConcorrenteDao;
import br.com.sistema.siefcotacao.dao.Prd_ProdutoDao;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_ConcorrenteVO;
import br.com.sistema.siefcotacao.vo.Prd_ProdutoVO;


public class ConcFornecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conc_prod);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        List rowItems;
        ListView listview = (ListView) findViewById(R.id.listView_conc_prod);
        TextView textView_conc_prod_titulo = (TextView) findViewById(R.id.textView_conc_prod_titulo);
        Intent intent = getIntent();

        if (intent.getStringExtra("TABELA").equals(Prd_ProdutoVO.TABLE)){
            setTitle("Lista Produtos");
            textView_conc_prod_titulo.setText("PRODUTOS CADASTRADOS");

            rowItems = new ArrayList<Prd_ProdutoVO>();

            Prd_ProdutoDao dao = new Prd_ProdutoDao(this);
            rowItems = dao.getAllList();

            CustomListViewProdAdapter adapter = new CustomListViewProdAdapter(this, R.layout.list_item_listview_conc_prod,rowItems);
            listview.setAdapter(adapter);

        } else {
            setTitle("Lista Concorrentes");

            rowItems = new ArrayList<Cpr_Cot_ConcorrenteVO>();

            Cpr_Cot_ConcorrenteDao dao = new Cpr_Cot_ConcorrenteDao(this);
            rowItems = dao.getAllList();


            if (rowItems.size() > 0){
                Cpr_Cot_Conc_ProdDao dao_conc_prod = new Cpr_Cot_Conc_ProdDao(this);
                try {
                    textView_conc_prod_titulo.setText("COTACAO DIA: " + dao_conc_prod.getDtCotacao());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                textView_conc_prod_titulo.setText("NENHUMA COTAÇÃO LOCALIZADA");
            }

            CustomListViewConcAdapter adapter = new CustomListViewConcAdapter(this, R.layout.list_item_listview_conc_prod,rowItems);
            listview.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_conc_fornec, menu);
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
        }else if (id == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
