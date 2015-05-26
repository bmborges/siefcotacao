package br.com.sistema.siefcotacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.sistema.siefcotacao.adapters.CustomListViewSettingsAdapter;
import br.com.sistema.siefcotacao.beans.RowItem_ListView_Settings;


public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final String[] titles = new String[]{"Sistema","Sincronizar Dados"};
    public static final Integer[] images = {R.mipmap.ic_action_computer,R.mipmap.ic_action_refresh};

    ListView listview;
    List<RowItem_ListView_Settings> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        rowItems = new ArrayList<RowItem_ListView_Settings>();
        for (int i = 0; i < titles.length; i++){
            RowItem_ListView_Settings item = new RowItem_ListView_Settings(images[i],titles[i]);
            rowItems.add(item);
        }

        listview = (ListView) findViewById(R.id.listView_settings);
        CustomListViewSettingsAdapter adapter = new CustomListViewSettingsAdapter(this, R.layout.list_item_listview_settings,rowItems);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_settings, menu);
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
        } else if(id == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if (position == 0){
           startActivity(new Intent(this, SettingsSystemActivity.class));
       } else if (position == 1){
           startActivity(new Intent(this, SyncDataActivity.class));
       }
        /*Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        */
    }
}
