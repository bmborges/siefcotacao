package br.com.sistema.siefcotacao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.sistema.siefcotacao.adapters.CustomListViewMainAdapter;
import br.com.sistema.siefcotacao.beans.RowItem_ListView_Main;
import br.com.sistema.siefcotacao.util.VerificaPreferences;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_ConcorrenteVO;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public static final String[] titles = new String[4];
    public static final Integer[] images = new Integer[4];
    //public static final String[] titles = new String[]{"Iniciar Cotação","Lista Produtos","Lista Concorrentes","Configurações","Sincrolizar Dados","Sair"};
    //public static final Integer[] images = {R.mipmap.ic_action_collection,R.mipmap.ic_action_view_as_list,R.mipmap.ic_action_view_as_list,R.mipmap.ic_action_settings, R.mipmap.ic_action_refresh,R.mipmap.ic_action_remove};

    ListView listview;
    List<RowItem_ListView_Main> rowItems;
    VerificaPreferences verificaPreferences;
//    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificaPreferences = new VerificaPreferences(this);

        int i = 0;
        titles[i] = "Iniciar Cotação";
        images[i] = R.mipmap.ic_action_collection;
        //i++;
        //titles[i] = "Lista Produtos";
        //images[i] = R.mipmap.ic_action_view_as_list;
        i++;
        titles[i] = "Configurações";
        images[i] = R.mipmap.ic_action_settings;
        i++;
        titles[i] = "Sincrolizar Dados";
        images[i] = R.mipmap.ic_action_refresh;
        i++;
        titles[i] = "Sair";
        images[i] = R.mipmap.ic_action_remove;

        verificaPreferences.verifica();

        rowItems = new ArrayList<RowItem_ListView_Main>();
        for (i = 0; i < titles.length; i++){
            RowItem_ListView_Main item = new RowItem_ListView_Main(images[i],titles[i]);
            rowItems.add(item);
        }

        listview = (ListView) findViewById(R.id.listView_main);
        CustomListViewMainAdapter adapter = new CustomListViewMainAdapter(this, R.layout.list_item_listview_main,rowItems);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        } else if (id == R.id.action_sair){
            Sair();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){
            if(verificaPreferences.verifica()) {
                //startActivity(new Intent(this,CotacaoProdutoActivity.class));
                Intent intent = new Intent(this, ConcFornecActivity.class);
                intent.putExtra("TABELA", Cpr_Cot_ConcorrenteVO.TABLE);
                startActivity(intent);
            }
        //} else if (position == 1 ){
        //    Intent intent = new Intent(this,ConcFornecActivity.class);
        //    intent.putExtra("TABELA", Prd_ProdutoVO.TABLE);
        //    startActivity(intent);
        } else if (position == 1){
            verificaPreferences.abreSettingsSystem();
        } else if (position == 2){
            if (verificaPreferences.getEndServer() != null && verificaPreferences.getEndServer().toString().length() > 0){
                verificaPreferences.abreSyncDados();
            } else {
                verificaPreferences.verifica();
            }
        } else if (position == 3){
            Sair();
        }
    }

    private final class PositiveSettingsClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            startActivity(new Intent(getApplicationContext(), SettingsSystemActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        Sair();
    }
    private final class SairPositiveClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            MainActivity.this.finish();
        }
    }
    private final class SairNegativeClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
        }
    }
    private void Sair(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sistema");
        builder.setMessage("Deseja realmente Sair?");
        builder.setCancelable(true);
        builder.setPositiveButton("Sim", new SairPositiveClickListener());
        builder.setNegativeButton("Não", new SairNegativeClickListener());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
