package com.ebookfrenzy.projetibj.mainFragment;

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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    public static ArrayList<CartProducts> produits = new ArrayList<>();
    private CartFragmentAdapter adapter ;
    View rowView;
    private static TextView  total ;
   private static double  totals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



         rowView=inflater.inflate(R.layout.fragment_cart, container, false);
        ListView lv = rowView.findViewById(R.id.cart_listView);
        adapter = new CartFragmentAdapter(produits,getContext());
        lv.setAdapter(adapter);

        total = rowView.findViewById(R.id.cart_total_id);





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

            DecimalFormat df = new DecimalFormat("0.00");
        }


        super.onStart();
    }

    public static void setTotal(double totals){
        total.setText(totals+" $ ");
    }
}