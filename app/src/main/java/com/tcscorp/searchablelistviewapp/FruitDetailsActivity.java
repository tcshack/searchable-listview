package com.tcscorp.searchablelistviewapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.tcscorp.searchablelistviewapp.databinding.ActivityFruitDetailsBinding;

public class FruitDetailsActivity extends AppCompatActivity {
    public static final String FRUIT_ARG = "fruit_arg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFruitDetailsBinding binding = ActivityFruitDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            Fruit fruit = (Fruit) intent.getSerializableExtra("fruit_arg");
            binding.name.setText(fruit.getName());
            binding.description.setText(fruit.getDescription());
        }
    }
}
