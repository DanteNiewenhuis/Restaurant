package com.example.dante.restaurant;

import android.content.Context;

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
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<String> categories = new ArrayList<>();
        try {
            JSONArray mJsonArray = response.getJSONArray("categories");

            assert mJsonArray != null;
            for (int i = 0; i < mJsonArray.length(); i++) {
                categories.add(mJsonArray.getString(i));
            }
            activity.gotCategories(categories);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getCategories(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        // send a request to the api to get the categories
        String url = "https://resto.mprog.nl/categories";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

    }
}
