package com.karbonara.karbonara.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.karbonara.karbonara.Anime;
import com.karbonara.karbonara.R;
import com.karbonara.karbonara.ui.main.adapters.SeriesListAdapter;
import com.karbonara.karbonara.ui.main.dialogs.MessageDialog;
import com.karbonara.karbonara.ui.main.fragments.WatchFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesListFragment extends DialogFragment {
    public static Anime anime;

    public SeriesListFragment() {
        // Required empty public constructor
    }

    public SeriesListFragment(Anime a) {
        anime = a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series_list, container, false);
        SeriesListAdapter adapter = new SeriesListAdapter(getContext(), anime.episodes);
        ListView lv = view.findViewById(R.id.listSeries);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!anime.episodes[position].equals("0")) {
                    Fragment newFragment = new WatchFragment(anime.episodes[position]);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, newFragment)
                            .addToBackStack(null)
                            .commit();
                    getDialog().cancel();
                } else {
                    DialogFragment newFragment = new MessageDialog("Видео удалено в связи с обращением правообладателя", "Наш телеграм канал t.me/karbonar");
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    newFragment.show(fm, "0");
                }
            }
        });
        return view;
    }
}
