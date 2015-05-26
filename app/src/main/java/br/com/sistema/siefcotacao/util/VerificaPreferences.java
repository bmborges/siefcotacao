package br.com.sistema.siefcotacao.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.sistema.siefcotacao.SettingsSystemActivity;
import br.com.sistema.siefcotacao.SplashActivity;
import br.com.sistema.siefcotacao.SyncDataActivity;

/**
 * Created by root on 22/05/15.
 */
public class VerificaPreferences {
    SharedPreferences sharedpreferences;
    String endServer;
    String senhaSyncDados;
    Context context;

    public VerificaPreferences(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences(SplashActivity.MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public String getSenhaSyncDados() {
        return sharedpreferences.getString(SplashActivity.SenhaSyncDados,"");
    }
    public String getEndServer() {
        return sharedpreferences.getString(SplashActivity.EndServer,"");
    }
    public String getApagarDados(){
        return sharedpreferences.getString(SplashActivity.ApagarDados,"");
    }

    public boolean verifica(){
        boolean bReturn = true;
        endServer = sharedpreferences.getString(SplashActivity.EndServer,"");
        senhaSyncDados = sharedpreferences.getString(SplashActivity.SenhaSyncDados, "");
        if ((endServer == null || endServer.trim().length() <= 0)
                || (senhaSyncDados == null || senhaSyncDados.trim().length() <= 0)){
            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)context);
            builder.setTitle("Sistema");
            builder.setMessage("Configure os Parâmetros do Sistema");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new ConfirmaPositiveVerificaClickListener());
            AlertDialog dialog = builder.create();
            dialog.show();
            bReturn = false;
        }
        return bReturn;
    }
    public boolean verificaSairSimNao(){
        boolean bReturn = true;
        endServer = sharedpreferences.getString(SplashActivity.EndServer,"");
        senhaSyncDados = sharedpreferences.getString(SplashActivity.SenhaSyncDados,"");
        if ((endServer == null || endServer.trim().length() <= 0)
                || (senhaSyncDados == null || senhaSyncDados.trim().length() <= 0)){
            AlertDialog.Builder builder = new AlertDialog.Builder((Activity) context);
            builder.setTitle("Sistema");
            builder.setMessage("Parâmetros não Configurados, Deseja Sair?");
            builder.setCancelable(false);
            builder.setPositiveButton("Sim", new ConfirmaPositiveVerificaSairSimNaoClickListener());
            builder.setNegativeButton("Não", new ConfirmaNegativeVerificaSairSimNaoClickListener());
            AlertDialog dialog = builder.create();
            dialog.show();
            bReturn = false;
        }
        return bReturn;
    }
    private final class ConfirmaNegativeVerificaClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
        }
    }
    private final class ConfirmaPositiveVerificaClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {

            abreSettingsSystem();

        }
    }

    private final class ConfirmaNegativeVerificaSairSimNaoClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
        }
    }
    private final class ConfirmaPositiveVerificaSairSimNaoClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            ((Activity)context).finish();
        }
    }
    public void abreSettingsSystem(){
        final EditText input = new EditText(((Activity)context));
        input.setHint("Senha");
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        new AlertDialog.Builder(((Activity)context))
                .setTitle("Acesso Restrito")
                .setMessage("Digite a Senha")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value = input.getText().toString();
                        String senha = "";

                        Calendar c = new GregorianCalendar();
                        String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 2);
                        String mes = String.valueOf(c.get(Calendar.MONTH) + 1 + 2);
                        if (mes.length() < 2){
                            mes = "0" + mes;
                        }
                        String ano = String.valueOf(c.get(Calendar.YEAR));

                        senha = dia+mes+ano;
                        //Log.d("App", "Senha:" + senha);

                        if (value.equals(senha)) {
                            ((Activity)context).startActivity(new Intent(((Activity) context), SettingsSystemActivity.class));
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
    public void abreSyncDados(){
        final EditText input = new EditText(((Activity) context));
        input.setHint("Senha");
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        new AlertDialog.Builder(((Activity) context))
                .setTitle("Acesso Restrito")
                .setMessage("Digite a Senha")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value = input.getText().toString();
                        String senha = getSenhaSyncDados();

//                        Log.d("App", "Senha:" + senha);

                        if (value.equals(senha)) {
                            ((Activity) context).startActivity(new Intent(((Activity) context), SyncDataActivity.class));
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

}
