package com.karbonara.karbonara.ui.main.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.karbonara.karbonara.R;

public class SeriesListAdapter extends ArrayAdapter<String> {
    TextView tv;
    public SeriesListAdapter(Context context, String[] arr) {
        super(context, R.layout.adapter_series, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_series, null);
        }
        tv = convertView.findViewById(R.id.seriaN);
        tv.setText("Серия " + (position+1));
        return convertView;
    }
}