package com.lastmilelink.bs.tictactoe.Utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.lastmilelink.bs.tictactoe.R;

/**
 * Created by binusadanand on 07/06/2017.
 */

public class ShowMessage {

    public static void now(Context aContext, View aView, String aMsg) {
        show(aContext, aView, aMsg, 0) ;
    }

    public static void now(Context aContext, View aView, int aResId, int aColorID) {
        show(aContext, aView, aContext.getString(aResId), aColorID) ;
    }

    private static void show(Context aContext, View aView, final String aMsg, int aColorID) {
        Snackbar snackbar;
        snackbar = Snackbar.make(aView, aMsg, 5000);

        if (aColorID == 0) {
            aColorID = R.color.topaz;
        }

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(aContext, aColorID));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(aContext,R.color.petroleum));

        snackbar.show();
    }
}
