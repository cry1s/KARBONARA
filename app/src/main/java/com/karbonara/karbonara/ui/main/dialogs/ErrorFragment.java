package com.karbonara.karbonara.ui.main.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karbonara.karbonara.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ErrorFragment extends DialogFragment {
    private static String text = "null";

    public ErrorFragment() {
        // Required empty public constructor
    }

    public ErrorFragment(String mess) {
        text = mess;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        TextView tv = view.findViewById(R.id.textMessage);
        tv.setText(text);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Важное сообщение!")
                .setMessage((text.equals("Attempt to invoke virtual method 'int java.lang.String.indexOf(java.lang.String)' on a null object reference") ? "Нет подключения к интернету" : text) + "\n"
                        + "Приложение будет закрыто")
                .setPositiveButton("bruh", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getActivity().finish();
                    }
                });
        return builder.create();
    }
}
