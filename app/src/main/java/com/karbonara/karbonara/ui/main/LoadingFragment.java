package com.karbonara.karbonara.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karbonara.karbonara.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment {

    public LoadingFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new LoadingFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        new AnimeListRequestTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        return view;
    }

    private class AnimeListRequestTask extends AsyncTask<Void, Void, String> {
        boolean success = true;
        InputStream is = null;
        String msg;

        @Override
        protected String doInBackground(Void... params) {
            try {
                final URL url = new URL("https://spreadsheets.google.com/tq?key=1H4FKgLnJXOqSZhvwwlXzmqCg4oHhjSGTdjzjGLByP9E&gid=796020870");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                int responseCode = conn.getResponseCode();
                is = conn.getInputStream();
                return convertStreamToString(is);
            } catch (Exception e) {
                success = false;
                msg = e.getMessage();
                Log.e("RRRR", e.getMessage(), e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String r) {
            int start = r.indexOf("{", r.indexOf("{") + 1);
            int end = r.lastIndexOf("}");
            String jsonResponse = r.substring(start, end);
            JSONObject table = null;
            try {
                table = new JSONObject(jsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Fragment newFragment = (success ? new MainFragment(table) : new TextFragment(msg));
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            // Замена контейнер в разметке на фрагмент
            transaction.replace(R.id.container, newFragment);
            // выполнение транзакции
            transaction.commit();
        }

    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
