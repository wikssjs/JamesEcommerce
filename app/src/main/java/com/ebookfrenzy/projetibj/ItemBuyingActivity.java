package com.ebookfrenzy.projetibj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ebookfrenzy.projetibj.mainFragment.CartFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemBuyingActivity extends AppCompatActivity {


    private ArrayList list ;
    private static final String nomFichier = "fruit.json";
    CartProducts cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buying);

        TextView P_name = findViewById(R.id.buying_product_name);
        TextView P_price = findViewById(R.id.buying_price);
        ImageView P_image = findViewById(R.id.buying_image);

        Bundle bundle = getIntent().getExtras();

        Produit produit  = (Produit) bundle.get("produit");
        String name = produit.getNom();
        String price = String.valueOf(produit.getPrix());
        String image = produit.getImage();

        P_name.setText(name);
        P_price.setText(price);
        Picasso.get().load(image).into(P_image);

        Button addTocart = findViewById(R.id.button_addToCart);

        addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.produits.add(produit);
            }
        });
    }
}