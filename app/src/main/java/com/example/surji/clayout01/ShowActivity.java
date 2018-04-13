package com.example.surji.clayout01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    GridView gridView;
    ArrayAdapter<String> adapter;
    List<String> names = new ArrayList<>();
    List<String> mob = new ArrayList<>();
    List<String> img = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        names = getIntent().getStringArrayListExtra("names");
        mob = getIntent().getStringArrayListExtra("mob");
        img = getIntent().getStringArrayListExtra("img");
        gridView = findViewById(R.id.gridView);
        adapter = new ArrayAdapter<String>(ShowActivity.this, R.layout.item_view, R.id.name, names) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ImageView image = view.findViewById(R.id.imageView);

                byte [] encodeByte = Base64.decode(img.get(position), Base64.DEFAULT);
                Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                image.setImageBitmap(bitmap);

                TextView mobile = view.findViewById(R.id.mobile);
                mobile.setText(mob.get(position));
                return view;
            }
        };
        gridView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_item:
                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
