package com.karbonara.karbonara.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.karbonara.karbonara.Anime;
import com.karbonara.karbonara.R;
import com.squareup.picasso.Picasso;

public class AnimeFragment extends Fragment {
    public static Anime a;
    ImageView poster;
    TextView title;
    TextView descriprion;
    TextView status1; //статус всего сериала, сколько серий готово из скольки
    TextView status2; //статус серии
    TextView dubbers;
    Button toWatchButton;

    public AnimeFragment() {
    }

    public AnimeFragment(Anime a) {
        AnimeFragment.a = a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime, container, false);
        poster = view.findViewById(R.id.backPoster);
        title = view.findViewById(R.id.titleDetails);
        descriprion = view.findViewById(R.id.descriptionDetails);
        status1 = view.findViewById(R.id.StatusSeriesDetails);
        status2 = view.findViewById(R.id.StatusNowDetails);
        dubbers = view.findViewById(R.id.authorsDetails);
        toWatchButton = view.findViewById(R.id.butWatch);
        toWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new SeriesListFragment(a);
                newFragment.show(getActivity().getSupportFragmentManager(), "chooseSeria");
            }
        });
        Picasso.with(getContext())
                .load(a.posterHor)
                .fit()
                .centerCrop()
                .into(poster);
        title.setText(a.titleRus);
        descriprion.setText(a.description);
        status1.setText(a.readyStatus);
        status2.setText("Статус следующей серии: " + a.statusSerii);
        dubbers.setText("Над тайтлом работали: " + a.listMembers);
        return view;
    }
}
