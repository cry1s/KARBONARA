package com.karbonara.karbonara.ui.main.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.karbonara.karbonara.R;

public class MessageDialog extends DialogFragment {
    static String title;
    static String msg;

    public MessageDialog(){}

    public MessageDialog(String title, String msg) {
        MessageDialog.msg = msg;
        MessageDialog.title = title;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
