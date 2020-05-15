package com.karbonara.karbonara.ui.main.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.karbonara.karbonara.R;
import com.karbonara.karbonara.ui.main.dialogs.ErrorFragment;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoadingFragment extends Fragment {

    public LoadingFragment() {
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
            } catch (Throwable e) {
                success = false;
                msg = e.getLocalizedMessage();
                Log.e("RRRR", e.getMessage(), e);
                DialogFragment newFragment = new ErrorFragment(msg);
                newFragment.show(getParentFragmentManager(), "Error happened :c");
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
            JSONObject table = null;
            try {
                int start = r.indexOf("{", r.indexOf("{") + 1);
                int end = r.lastIndexOf("}");
                String jsonResponse = r.substring(start, end);
                table = new JSONObject(jsonResponse);
            } catch (Throwable e) {
                success = false;
                msg = e.getLocalizedMessage();
                e.printStackTrace();
            }
            if (success) {
                Fragment newFragment = new MainFragment(table, getActivity());
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                // Замена контейнер в разметке на фрагмент
                transaction.replace(R.id.container, newFragment);
                // выполнение транзакции
                transaction.commit();
            } else {
                 DialogFragment newFragment = new ErrorFragment(msg);
                 newFragment.show(getParentFragmentManager(), "Error happened :c");
            }
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
