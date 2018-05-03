package com.example.dante.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);

        // set the adapter and the itemCleickListener
        ListView categories_list = findViewById(R.id.categorie_list);
        categories_list.setAdapter(adapter);
        categories_list.setOnItemClickListener(new ListItemClickListener());

    }

    @Override
    public void gotCategoriesError(String message) {
        // send a message if an error has occurred
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", (String) parent.getItemAtPosition(position));

            startActivity(intent);
        }
    }
}
