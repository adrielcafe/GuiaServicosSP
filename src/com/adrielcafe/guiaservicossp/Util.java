package com.adrielcafe.guiaservicossp;

import java.io.InputStream;

import android.content.Context;
import android.text.Html;

import com.google.gson.Gson;

public class Util {
	public static final String EXTRA_TITLE = "title";
	public static final String EXTRA_DB = "db";
	public static final String EXTRA_SERVICE = "service";
		
	public static final Gson GSON = new Gson();
	public static final String ABOUT_MESSAGE = "<b>Guia de Serviços SP</b> é um software livre não-oficial, seu código fonte está disponível no <a href='https://github.com/adrielcafe/GuiaServicosSP'>GitHub</a>."
											 + "<br><br>Desenvolvido por:<br>Adriel Café (ac@adrielcafe.com)";
	
	public static String loadJSON(Context context, String path) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            return null;
        }
        return json;
    }
	
	public static void showDialog(Context context, String title, String message){
		QustomDialogBuilder dialog = new QustomDialogBuilder(context);
		dialog.setTitleColor(context.getResources().getColor(R.color.red));
		dialog.setDividerColor(context.getResources().getColor(R.color.red));
		dialog.setTitle(Html.fromHtml(title));
		dialog.setMessage(Html.fromHtml(message));
		dialog.setIcon(R.drawable.ic_launcher);
		dialog.show();
	}

}