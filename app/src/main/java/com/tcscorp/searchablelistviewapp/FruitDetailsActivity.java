package com.tcscorp.searchablelistviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import com.tcscorp.searchablelistviewapp.databinding.ActivityFruitDetailsBinding;

public class FruitDetailsActivity extends AppCompatActivity {
    public static final String FRUIT_ARG = "fruit_arg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fruit fruit;
        ActivityFruitDetailsBinding binding = ActivityFruitDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            fruit = (Fruit) intent.getSerializableExtra("fruit_arg");
            String title = "About " + fruit.getName();
            getSupportActionBar().setTitle(title);
            binding.name.setText(fruit.getName());
            binding.description.setText(fruit.getDescription());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.search_view);
        searchMenuItem.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.share) {
            Util.launchSharingIntent(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
