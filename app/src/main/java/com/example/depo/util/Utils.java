package com.example.depo.util;



import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.depo.R;


public class Utils {
    private static final int REQUEST_CODE_ADAPTER = 1;
    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean areNullOrEmpty(String... datas) {
        for(String data : datas){
            if(data==null || data.isEmpty()){
                return true;
            }
        }
        //We need false according to this method. Because if method return false then there is no null or empty input from user.
        return false;
    }



    public static void showAlertDialog(Context context, String positiveButtonText, String negativeButtonText, DialogInterface.OnClickListener positiveClickListener, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewBox = inflater.inflate(R.layout.alert_box_design, null);
        builder.setView(viewBox);

        AlertDialog alertDialog = builder.create();

        viewBox.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (positiveClickListener != null) {
                    positiveClickListener.onClick(alertDialog, DialogInterface.BUTTON_POSITIVE);
                }
            }
        });

        viewBox.findViewById(R.id.negative_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (negativeClickListener != null) {
                    negativeClickListener.onClick(alertDialog, DialogInterface.BUTTON_NEGATIVE);
                }
            }
        });

        alertDialog.show();
    }
}

