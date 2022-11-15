package com.ebookfrenzy.projetibj.mainFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ebookfrenzy.projetibj.CartProducts;
import com.ebookfrenzy.projetibj.R;
import com.ebookfrenzy.projetibj.ShippingActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    public static ArrayList<CartProducts> produits = new ArrayList<>();
    private CartFragmentAdapter adapter ;
    View rowView;
    private static TextView  total ;
    private TextView empty_mes;
    private double totals;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TOTAL_KEY = "total";
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences = inflater.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);



        rowView=inflater.inflate(R.layout.fragment_cart, container, false);
        ListView lv = rowView.findViewById(R.id.cart_listView);
        adapter = new CartFragmentAdapter(produits,getContext());
        lv.setAdapter(adapter);
        empty_mes = rowView.findViewById(R.id.empty_message);



        total = rowView.findViewById(R.id.cart_total_id);

        rowView.findViewById(R.id.btn_checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShippingActivity.class));
            }
        });



        // Inflate the layout for this fragment
        return rowView;
    }

    @Override
    public void onStart() {
        if(produits.size()>0) {
            ListView lv = rowView.findViewById(R.id.cart_listView);
            adapter = new CartFragmentAdapter(produits, getContext());
            lv.setAdapter(adapter);


            double somme = 0;
            for (int i = 0; i < produits.size(); i++) {
                Log.i("james",i+" i");
                somme += produits.get(i).getProduit().getPrix() * produits.get(i).getQuantity();
            }

             totals= somme;
            editor = sharedPreferences.edit();
            editor.putString(TOTAL_KEY, String.valueOf(somme));
            editor.apply();


            DecimalFormat df = new DecimalFormat("0.00");
        }


        if(produits.size()==0){
            empty_mes.setText("There are no items in your cart.");
        }
        else{
            empty_mes.setText("");
        }




        super.onStart();
    }

    public static void setTotal(double totals){
        total.setText(totals+" $ ");
    }
}