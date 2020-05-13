package com.karbonara.karbonara.ui.main;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.karbonara.karbonara.Anime;
import com.karbonara.karbonara.MainActivity;
import com.karbonara.karbonara.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.karbonara.karbonara.ui.main.MainFragment.table;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder>{
    private ArrayList<Anime> android_versions;
    private Context context;

    public AnimeAdapter(Context context, ArrayList<Anime> android_versions) {
        this.context = context;
        this.android_versions = android_versions;
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
        viewHolder.description.setText(anime.description);
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
            FragmentTransaction transaction = beginTransaction(); //!!!!!!!!!!!!!!!!
            // Замена контейнер в разметке на фрагмент
            transaction.replace(R.id.container, newFragment);
            // выполнение транзакции
            transaction.commit();
        }
    }
}
