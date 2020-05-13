package com.karbonara.karbonara.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
    Anime a;
    ImageView poster;
    TextView title;
    TextView descriprion;
    TextView status1; //статус всего сериала, сколько серий готово из скольки
    TextView status2; //статус серии
    TextView dubbers;
    Button toWatchButton;

    public AnimeFragment() {
        // Required empty public constructor
    }

    public AnimeFragment(Anime a) {
        this.a = a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime, container, false);
        if (a != null) {
            poster = view.findViewById(R.id.backPoster);
            title = view.findViewById(R.id.titleDetails);
            descriprion = view.findViewById(R.id.descriptionDetails);
            status1 = view.findViewById(R.id.StatusSeriesDetails);
            status2 = view.findViewById(R.id.StatusNowDetails);
            dubbers = view.findViewById(R.id.authorsDetails);
            toWatchButton = view.findViewById(R.id.butWatch);

            Picasso.with(getContext())
                    .load(a.poster)
                    .error(R.drawable.poster)
                    .into(poster);
            title.setText(a.titleRus);
            descriprion.setText(a.description);
            status1.setText(a.readyStatus);
        } else {
            Log.e("HHHHHHHH", "field anime is empty");
        }
        return view;
    }
}
