package com.karbonara.karbonara.ui.main.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karbonara.karbonara.Anime;
import com.karbonara.karbonara.R;
import com.karbonara.karbonara.ui.main.dialogs.ErrorFragment;
import com.karbonara.karbonara.ui.main.adapters.AnimeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFragment extends Fragment {
    public static JSONObject table;
    private Context context;

    public MainFragment() {
    }

    public MainFragment(JSONObject table, Context context) {
        MainFragment.table = table;
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.animeRecView);
        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList images = new ArrayList(Arrays.asList(makeAnime()));
        AnimeAdapter adapter = new AnimeAdapter(getContext().getApplicationContext(), images, getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(adapter);
        RecyclerView rv = view.findViewById(R.id.animeRecView);

        Log.i("AAAAAAAAAAAAAAAAAAAA", "OnCreateViewDone");
        return view;
    }
    /*
    Полученные данные перерабатываем в массив со всеми тайтлами
     */
    private Anime[] makeAnime() {
        Anime[] arr;
        try {
            JSONArray data = table.getJSONArray("rows");
            int number = data.length();
            arr = new Anime[number];
            for (int i = 0; i < number; i++) {
                JSONArray k = data.getJSONObject(i).getJSONArray("c");
                Anime anime = new Anime(
                        i,
                        k.getJSONObject(1).getString("v"),
                        k.getJSONObject(2).getString("v"),
                        k.getJSONObject(3).getString("v"),
                        k.getJSONObject(4).getInt("v"),
                        k.getJSONObject(5).getInt("v"),
                        k.getJSONObject(6).getString("v"),
                        k.getJSONObject(7).getString("v"),
                        k.getJSONObject(8).getString("v"),
                        k.getJSONObject(9).getString("v"),
                        k.getJSONObject(10).getString("v"),
                        k.getJSONObject(11).getString("v")
                        );
                arr[i] = anime;
                Log.i("ANIME SUCCESSFUL", anime.getTitle());
            }
            return arr;
        } catch (JSONException e) {
            Log.e("RRRRA", e.getLocalizedMessage());
            DialogFragment newFragment = new ErrorFragment(e.getLocalizedMessage());
            newFragment.show(getParentFragmentManager(), "Error happened :c");
        }
        return new Anime[] {new Anime()};
    }
}


