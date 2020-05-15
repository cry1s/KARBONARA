package com.karbonara.karbonara.ui.main.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karbonara.karbonara.Anime;
import com.karbonara.karbonara.R;
import com.karbonara.karbonara.ui.main.fragments.AnimeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder>{
    private ArrayList<Anime> android_versions;
    private Context context;
    FragmentManager fragmentManager;

    public AnimeAdapter(Context context, ArrayList<Anime> android_versions, FragmentManager fragmentManager) {
        this.context = context;
        this.android_versions = android_versions;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public AnimeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_anime, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Anime anime = android_versions.get(i);

        viewHolder.title.setText(anime.titleRus);
        viewHolder.description.setText(anime.getShortDescription());
        viewHolder.status.setText(anime.readyStatus);

        Picasso.with(context)
                .load(anime.poster)
                .resize(225, 322)
                .error(R.drawable.poster)
                .into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return android_versions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView status;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.description);
            status = view.findViewById(R.id.readyStatus);
            title = view.findViewById(R.id.title);
            img_android = view.findViewById(R.id.animePoster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Fragment newFragment = new AnimeFragment(android_versions.get(getAdapterPosition()));
            fragmentManager.beginTransaction()
                    .replace(R.id.container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

}
