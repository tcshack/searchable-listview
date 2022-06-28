package com.tcscorp.searchablelistviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import com.tcscorp.searchablelistviewapp.databinding.ActivityFruitDetailsBinding;

public class FruitDetailsActivity extends AppCompatActivity {
    public static final String FRUIT_ARG = "fruit_arg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFruitDetailsBinding binding = ActivityFruitDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            Fruit fruit = (Fruit) intent.getSerializableExtra("fruit_arg");
            String title = "About " + fruit.getName();
            getSupportActionBar().setTitle(title);
            binding.name.setText(fruit.getName());
            binding.description.setText(fruit.getDescription());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
