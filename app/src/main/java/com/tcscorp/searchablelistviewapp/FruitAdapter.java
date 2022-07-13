package com.tcscorp.searchablelistviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class FruitAdapter extends BaseAdapter implements Filterable {

    private final List<Fruit> fruits;
    private List<Fruit> filteredFruits;
    private final Context context;
    private OnFruitItemClick mCallback;

    public FruitAdapter(List<Fruit> fruits, Context context) {
        this.fruits = fruits;
        this.filteredFruits = fruits;
        this.context = context;
    }

    @Override
    public int getCount() {
        return filteredFruits.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fruitItemView = inflater.inflate(R.layout.item_fruit, null);
        TextView nameTxv = fruitItemView.findViewById(R.id.name);

        nameTxv.setText(filteredFruits.get(position).getName());

        fruitItemView.setOnClickListener(v -> mCallback.onFruitClicked(filteredFruits.get(position)));

        return fruitItemView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = fruits.size();
                    filterResults.values = fruits;
                } else {
                    String query = constraint.toString().toLowerCase(Locale.ROOT);
                    List<Fruit> resultData = new ArrayList<>();
                    for (Fruit fruit : fruits) {
                        if (fruit.getName().toLowerCase(Locale.ROOT).contains(query)) {
                            resultData.add(fruit);
                        }
                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredFruits = (List<Fruit>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setOnFruitItemClickedListener(OnFruitItemClick mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnFruitItemClick {
        void onFruitClicked(Fruit fruit);
    }
}
