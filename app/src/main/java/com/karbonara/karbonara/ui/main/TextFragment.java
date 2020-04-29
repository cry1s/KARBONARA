package com.karbonara.karbonara.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karbonara.karbonara.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {
    private String text = "null";
    public TextFragment() {
        // Required empty public constructor
    }
    public TextFragment(String mess) {
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
}
