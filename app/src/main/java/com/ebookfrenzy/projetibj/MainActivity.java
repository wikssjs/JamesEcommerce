package com.ebookfrenzy.projetibj;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    ViewPageAdapter viewPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        produits();
        setContentView(R.layout.activity_main);
        /***
         * Pour éviter le bug de la barre de status
         */
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);


        /**
         * On ajoute les onglets
         */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /**
         * Pour faire le lien entre le tablayout et le viewpager
         */
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();

            }

        });


    }

    public void produits() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://asos2.p.rapidapi.com/products/v2/list?store=US&offset=0&categoryId=4209&limit=48&country=US&sort=freshness&currency=USD&sizeSchema=US&lang=en-US")
                .get()
                .addHeader("X-RapidAPI-Key", "1a1dd8d20amsh9f57cb9363b78d2p1d9416jsnef806e1b3281")
                .addHeader("X-RapidAPI-Host", "asos2.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resp = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(resp);
                                JSONArray jsonArray = jsonObject.getJSONArray("products");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Produit p = new Produit();
                                    p.setNom(jsonArray.getJSONObject(i).getString("name"));
                                    String price = jsonArray.getJSONObject(i).getJSONObject("price").getJSONObject("current").getString("value");
                                    p.setPrix(Double.parseDouble(price));
                                    p.setImage("https://" + jsonArray.getJSONObject(i).getString("imageUrl"));
                                    Produit.liste.add(p);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
    }

}