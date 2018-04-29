package com.example.dante.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem>{
    private ArrayList<MenuItem> items;

    public MenuItemAdapter(@NonNull Context context, int resource, ArrayList<MenuItem> items) {
        super(context, resource, items);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        MenuItem item = items.get(position);
        String name = item.getName();
        String imageUrl = item.getImageUrl();
        String price = "€" + item.getPrice();

        TextView item_name = convertView.findViewById(R.id.item_name);
        TextView item_price = convertView.findViewById(R.id.item_price);
        ImageView item_image = convertView.findViewById(R.id.item_image);

        item_name.setText(name);
        item_price.setText(price);

        Picasso.get().load(imageUrl).into(item_image);

        return convertView;
    }
}
