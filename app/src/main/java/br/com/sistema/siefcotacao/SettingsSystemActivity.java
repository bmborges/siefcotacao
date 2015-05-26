package br.com.sistema.siefcotacao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.sistema.siefcotacao.util.VerificaPreferences;


public class SettingsSystemActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText editText_endserver;
    EditText editText_senhaSyncDados;
    //CheckBox checkBox;
    String endServer;
    String senhaSyncDados;
    //String apagarDados;
    VerificaPreferences verificaPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_system);

        verificaPreferences = new VerificaPreferences(this);

        sharedpreferences = getSharedPreferences(SplashActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        editText_endserver = (EditText) findViewById(R.id.editText_settings_endserver);
        editText_senhaSyncDados = (EditText) findViewById(R.id.editText_settings_senhaSyncDados);

        endServer = sharedpreferences.getString(SplashActivity.EndServer,"");
        senhaSyncDados = sharedpreferences.getString(SplashActivity.SenhaSyncDados,"");

        if (endServer != null && endServer.trim().length() > 0){
            editText_endserver.setText(endServer);
            editText_endserver.setSelection(editText_endserver.getText().length());
        }

        if (senhaSyncDados != null && senhaSyncDados.trim().length() > 0){
            editText_senhaSyncDados.setText(senhaSyncDados);
            editText_senhaSyncDados.setSelection(editText_senhaSyncDados.getText().length());
        }

        //checkBox = (CheckBox) findViewById(R.id.chk_settings_apagadados);
        //apagarDados = sharedpreferences.getString(SplashActivity.ApagarDados,"");
        //if(apagarDados != null && apagarDados.trim().length() > 0){
        //    if(apagarDados.equals("V")){
        //        checkBox.setChecked(true);
        //    } else {
        //        checkBox.setChecked(false);
        //    }
        //} else {
        //    checkBox.setChecked(false);
        //}

        Button button = (Button) findViewById(R.id.button_settings_salvar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Sistema");
                    builder.setMessage("Confirma Alterações?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Sim", new SairPositiveClickListener());
                    builder.setNegativeButton("Não", new ConfirmaNegativeClickListener());
                    AlertDialog dialog = builder.create();
                    dialog.show();
            }
        });
    }

    private final class SairPositiveClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(SplashActivity.EndServer, editText_endserver.getText().toString());
            editor.putString(SplashActivity.SenhaSyncDados, editText_senhaSyncDados.getText().toString());

            editor.commit();
            verificaPreferences.verificaSairSimNao();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_settings_system, menu);
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
            if (verificaPreferences.verificaSairSimNao()) {
                this.finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (verificaPreferences.verificaSairSimNao()){
            this.finish();
        }
    }
    private final class ConfirmaNegativeClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
        }
    }
}
