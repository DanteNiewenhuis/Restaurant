package com.example.dante.restaurant;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("ErrorResponse", "init");

        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("Response", "init");

        ArrayList<String> categories = new ArrayList<>();
        try {
            JSONArray mJsonArray = response.getJSONArray("categories");
            Log.d("Response", "got Jsomn array");

            if (mJsonArray == null) {
                Log.d("Response", "array is null");
            }
            assert mJsonArray != null;
            for (int i = 0; i < mJsonArray.length(); i++) {
                Log.d("Response", "add");
                categories.add(mJsonArray.getString(i));
            }

            if (activity == null) {
                Log.d("Response", "activity is null");
            }
            activity.gotCategories(categories);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getCategories(Callback activity) {
        Log.d("getCategories", "init");
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://resto.mprog.nl/categories";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

    }
}
