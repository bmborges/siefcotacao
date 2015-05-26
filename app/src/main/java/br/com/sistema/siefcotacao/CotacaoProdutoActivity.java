package br.com.sistema.siefcotacao;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.sistema.siefcotacao.adapters.CustomListViewCotacaoProdutoAdapter;
import br.com.sistema.siefcotacao.dao.Cpr_Cot_Conc_ProdDao;
import br.com.sistema.siefcotacao.dao.Cpr_Cot_ConcorrenteDao;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_Conc_ProdVO;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_ConcorrenteVO;


public class CotacaoProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotacao_produto);

        Intent intent = getIntent();
        List rowItems = new ArrayList<Cpr_Cot_Conc_ProdVO>();
        Cpr_Cot_Conc_ProdDao dao = new Cpr_Cot_Conc_ProdDao(this);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ListView listview = (ListView) findViewById(R.id.listView_cotacao_produto);
        TextView textView_nmconcorrente = (TextView) findViewById(R.id.textView_cotacao_produto_nmconcorrente);




        rowItems = dao.getAllByIdConcorrente(intent.getIntExtra(Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente, 0));
        textView_nmconcorrente.setText(intent.getStringExtra(Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente));

        CustomListViewCotacaoProdutoAdapter adapter = new CustomListViewCotacaoProdutoAdapter(this, R.layout.list_item_listview_cotacao_produto,rowItems);
        listview.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_cotacao_produto, menu);
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
        } else if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
