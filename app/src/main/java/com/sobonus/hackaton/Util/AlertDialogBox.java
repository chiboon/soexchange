package com.sobonus.hackaton.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;

/**
 * Created by muhamad.kamal on 27/5/2015.
 */
public class AlertDialogBox {
    public static Async async;
    public  interface Async{
        public  void onclickOK();
    }
    public static void alertDialogBoxOk(Context contex,String title,String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                contex);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        //    dialog.dismiss();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    public static void alertDialogBoxInput(Context contex,String title, String msg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                contex);

        // set title
        alertDialogBuilder.setTitle(title);
// Set an EditText view to get user input
        final EditText input = new EditText(contex);
        input.setGravity(Gravity.CENTER);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        // set dialog message
        alertDialogBuilder
                .setView(input)
                .setMessage(msg)
                .setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        //    dialog.dismiss();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    public static void alertDialogBoxOk(Context contex,String title,String message, final Async async){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                contex);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setNeutralButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        async.onclickOK();
                        // if this button is clicked, close
                        // current activity
                        //    dialog.dismiss();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
