package br.com.sistema.siefcotacao;

import br.com.sistema.siefcotacao.util.SystemUiHider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SplashActivity extends Activity implements Runnable{

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String EndServer = "EndServer";
    public static final String ApagarDados = "ApagarDados";
    public static final String SenhaSyncDados = "SenhaSyncDados";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Handler handler = new Handler();
        handler.postDelayed(this,3000);

    }


    @Override
    public void run() {
            startActivity(new Intent(this, MainActivity.class));
            finish();
    }


}
