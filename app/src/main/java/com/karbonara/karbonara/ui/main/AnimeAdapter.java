package com.karbonara.karbonara.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.karbonara.karbonara.Anime;
import com.karbonara.karbonara.MainActivity;
import com.karbonara.karbonara.R;
import com.squareup.picasso.Picasso;

public class AnimeAdapter extends ArrayAdapter<Anime> {

    public AnimeAdapter(Context context, Anime[] arr) {
        super(context, R.layout.adapter_anime, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Anime anime = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_anime, null);
        }
        ((TextView) convertView.findViewById(R.id.title)).setText(anime.titleRus);
        ((TextView) convertView.findViewById(R.id.readyStatus)).setText(anime.readyStatus);
        ((TextView) convertView.findViewById(R.id.description)).setText(anime.description);
        ImageView iv = convertView.findViewById(R.id.animePoster);
        Picasso.with(convertView.getContext()).load(anime.poster).error(R.drawable.poster).into(iv);
        return convertView;
    }

}
