package com.ebookfrenzy.projetibj;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemBuyingActivity extends AppCompatActivity {


    private ArrayList list ;
    private static final String nomFichier = "fruit.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buying);


            Spinner spinner = (Spinner) findViewById(R.id.buying_spinner);
    // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.planets_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

        TextView P_name = findViewById(R.id.buying_product_name);
        TextView P_price = findViewById(R.id.buying_price);
        ImageView P_image = findViewById(R.id.buying_image);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String price = bundle.getString("price");
        String image = bundle.getString("image");

        P_name.setText(name);
        P_price.setText(price);
        Picasso.get().load(image).into(P_image);


    }



}