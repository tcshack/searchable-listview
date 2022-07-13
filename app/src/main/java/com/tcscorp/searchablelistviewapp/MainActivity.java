package com.tcscorp.searchablelistviewapp;

import static com.tcscorp.searchablelistviewapp.FruitDetailsActivity.FRUIT_ARG;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.tcscorp.searchablelistviewapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FruitAdapter.OnFruitItemClick {

    private FruitAdapter fruitAdapter;
    private InterstitialAd mInterstitialAd;
    private Fruit mFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Fruit> fruits = new ArrayList<>();
        String[] names = {"Apple", "Banana", "Kiwi", "Watermelon", "Orange"};
        String[] descriptions = {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Arcu dictum varius duis at consectetur lorem donec. Aliquam sem et tortor consequat id. Non consectetur a erat nam at lectus. Rhoncus aenean vel elit scelerisque mauris pellentesque pulvinar. Egestas pretium aenean pharetra magna ac placerat vestibulum. Amet consectetur adipiscing elit pellentesque habitant. Tortor vitae purus faucibus ornare suspendisse. Cursus sit amet dictum sit amet justo donec enim diam. Et pharetra pharetra massa massa ultricies. Rhoncus est pellentesque elit ullamcorper dignissim cras tincidunt. Ornare aenean euismod elementum nisi quis eleifend quam. Dapibus ultrices in iaculis nunc sed. Platea dictumst vestibulum rhoncus est. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Sollicitudin nibh sit amet commodo nulla facilisi nullam vehicula ipsum. Et malesuada fames ac turpis egestas sed tempus urna et. Sit amet justo donec enim diam vulputate ut. Interdum velit laoreet id donec. Vitae auctor eu augue ut lectus arcu bibendum. Amet nulla facilisi morbi tempus iaculis urna id volutpat lacus. Convallis tellus id interdum velit. Nullam vehicula ipsum a arcu cursus. Sed euismod nisi porta lorem mollis aliquam ut porttitor. Mattis rhoncus urna neque viverra justo nec ultrices dui sapien. Quisque egestas diam in arcu cursus euismod. Id semper risus in hendrerit gravida. Nisl nunc mi ipsum faucibus vitae. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Sollicitudin nibh sit amet commodo nulla facilisi nullam vehicula ipsum. Et malesuada fames ac turpis egestas sed tempus urna et. Sit amet justo donec enim diam vulputate ut. Interdum velit laoreet id donec. Vitae auctor eu augue ut lectus arcu bibendum. Amet nulla facilisi morbi tempus iaculis urna id volutpat lacus. Convallis tellus id interdum velit. Nullam vehicula ipsum a arcu cursus. Sed euismod nisi porta lorem mollis aliquam ut porttitor. Mattis rhoncus urna neque viverra justo nec ultrices dui sapien. Quisque egestas diam in arcu cursus euismod. Id semper risus in hendrerit gravida. Nisl nunc mi ipsum faucibus vitae. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Sollicitudin nibh sit amet commodo nulla facilisi nullam vehicula ipsum. Et malesuada fames ac turpis egestas sed tempus urna et. Sit amet justo donec enim diam vulputate ut. Interdum velit laoreet id donec. Vitae auctor eu augue ut lectus arcu bibendum. Amet nulla facilisi morbi tempus iaculis urna id volutpat lacus. Convallis tellus id interdum velit. Nullam vehicula ipsum a arcu cursus. Sed euismod nisi porta lorem mollis aliquam ut porttitor. Mattis rhoncus urna neque viverra justo nec ultrices dui sapien. Quisque egestas diam in arcu cursus euismod. Id semper risus in hendrerit gravida. Nisl nunc mi ipsum faucibus vitae.",
                "A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa. In some countries, bananas used for cooking may be called \"plantains\", distinguishing them from dessert bananas. ",
                "Kiwifruit or Chinese gooseberry is the edible berry of several species of woody vines in the genus Actinidia. The most common cultivar group of kiwifruit is oval, about the size of a large hen's egg: 5–8 centimetres in length and 4.5–5.5 cm in diameter.",
                "Watermelon is a flowering plant species of the Cucurbitaceae family and the name of its edible fruit. A scrambling and trailing vine-like plant, it is a highly cultivated fruit worldwide, with more than 1,000 varieties.",
                "An orange is a fruit of various citrus species in the family Rutaceae; it primarily refers to Citrus × sinensis, which is also called sweet orange, to distinguish it from the related Citrus × aurantium, referred to as bitter orange."
        };
        for (int i = 0; i < names.length; i++) {
            fruits.add(new Fruit(names[i], descriptions[i]));
        }

        fruitAdapter = new FruitAdapter(fruits, this);
        fruitAdapter.setOnFruitItemClickedListener(this);
        binding.listView.setAdapter(fruitAdapter);

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getString(R.string.interstitial_unit_id_test), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        attachInterstitialCallback();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });

    }

    private void attachInterstitialCallback() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                launchDetailsActivity();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                mInterstitialAd = null;
            }

            @Override
            public void onAdImpression() {
            }

            @Override
            public void onAdShowedFullScreenContent() {
            }
        });
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
        } else if (id == R.id.share) {
            Util.launchSharingIntent(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFruitClicked(Fruit fruit) {
        mFruit = fruit;
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {
            launchDetailsActivity();
        }
    }

    private void launchDetailsActivity() {
        Intent intent = new Intent(this, FruitDetailsActivity.class);
        intent.putExtra(FRUIT_ARG, mFruit);
        startActivity(intent);
    }
}