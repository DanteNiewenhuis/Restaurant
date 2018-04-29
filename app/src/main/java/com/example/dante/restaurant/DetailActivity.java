package com.example.dante.restaurant;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");

        String name = item.getName();
        String description = item.getDescription();
        String imageUrl = item.getImageUrl();
        String price = "€" + item.getPrice();

        TextView name_detail = findViewById(R.id.name_detail);
        TextView description_detail = findViewById(R.id.description_detail);
        TextView price_detail = findViewById(R.id.price_detail);
        ImageView image_detail = findViewById(R.id.image_detail);

        name_detail.setText(name);
        description_detail.setText(description);
        price_detail.setText(price);

        Picasso.get().load(imageUrl).into(image_detail);
    }
}
