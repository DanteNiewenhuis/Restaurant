package com.example.dante.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = (String)intent.getSerializableExtra("category");

        // get the menu from the api
        MenuRequest request = new MenuRequest(this, category);
        request.getMenuItems(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> items) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item, items);

        // set the adapter and the ItemListener of the listview
        ListView item_list = findViewById(R.id.item_list);
        item_list.setAdapter(adapter);
        item_list.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
            intent.putExtra("item", (MenuItem) parent.getItemAtPosition(position));

            startActivity(intent);
        }
    }
}
