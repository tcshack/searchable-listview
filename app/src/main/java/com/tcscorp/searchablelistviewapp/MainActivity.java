package com.tcscorp.searchablelistviewapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.tcscorp.searchablelistviewapp.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FruitAdapter fruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Fruit> fruits = new ArrayList<>();
        String[] names = {"Apple", "Banana", "Kiwi", "Watermelon", "Orange"};
        String[] descriptions = {"This is an apple", "This is a banana", "This is a kiwi", "This is a watermelon", "This is an orange"};
        for (int i = 0; i < names.length; i++) {
            fruits.add(new Fruit(names[i], descriptions[i]));
        }

        fruitAdapter = new FruitAdapter(this, fruits, this);
        binding.listView.setAdapter(fruitAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fruitAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_view) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}