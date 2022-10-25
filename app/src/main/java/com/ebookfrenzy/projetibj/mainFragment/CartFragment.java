package com.ebookfrenzy.projetibj.mainFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ebookfrenzy.projetibj.Produit;
import com.ebookfrenzy.projetibj.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    public static ArrayList<Produit> produits = new ArrayList<>();
    private CartFragmentAdapter adapter ;
    View rowView;
    TextView subTotal,tax,total ;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



         rowView=inflater.inflate(R.layout.fragment_cart, container, false);
        ListView lv = rowView.findViewById(R.id.cart_listView);
        adapter = new CartFragmentAdapter(produits,getContext());
        lv.setAdapter(adapter);

        subTotal = rowView.findViewById(R.id.cart_subtotal_id);
        tax = rowView.findViewById(R.id.cart_taxes_id);
        total = rowView.findViewById(R.id.cart_total_id);
        spinner = rowView.findViewById(R.id.cart_spinner);



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
                somme += produits.get(i).getPrix();
            }

            double taxes = (somme * 0.05);
            double totals = somme + taxes;

            DecimalFormat df = new DecimalFormat("0.00");
            subTotal.setText("$ " + somme);
            tax.setText(df.format(taxes));
            total.setText(totals + "");
        }


        super.onStart();
    }
}