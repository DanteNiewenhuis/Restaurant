package com.example.dante.restaurant;

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

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    private Context context;
    private Callback activity;
    private String category;

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> items);
        void gotMenuError(String message);
    }

    public MenuRequest(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    public void getMenuItems(Callback activity) {
        Log.d("getMenuItems", "init");
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        String url =  "https://resto.mprog.nl/menu";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        String name;
        String description;
        String imageUrl;
        String category;
        double price;

        ArrayList<MenuItem> items = new ArrayList<>();
        try {
            JSONArray mJsonArray = response.getJSONArray("items");

            assert mJsonArray != null;
            for (int i = 0; i < mJsonArray.length(); i++) {
                JSONObject item = mJsonArray.getJSONObject(i);

                if (this.category.equals(item.getString("category"))) {
                    // get all the info from the JSonArray
                    name = item.getString("name");
                    description = item.getString("description");
                    imageUrl = item.getString("image_url");
                    category = item.getString("category");
                    price = item.getDouble("price");

                    // create a new MenuItem and add it to items
                    items.add(new MenuItem(name, description, imageUrl, category, price));
                }
            }

            activity.gotMenu(items);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
