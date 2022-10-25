package com.ebookfrenzy.projetibj.mainFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ebookfrenzy.projetibj.CartProducts;
import com.ebookfrenzy.projetibj.Produit;
import com.ebookfrenzy.projetibj.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartFragmentAdapter extends BaseAdapter {

    public static ArrayList<CartProducts>cartProducts = new ArrayList<>() ;
    public static CartProducts cartProduct;
    LayoutInflater inflater;
    ArrayList<Produit> produits;
    Context ctx;

    public CartFragmentAdapter(ArrayList<Produit> produits, Context ctx){
        this.produits = produits;
        this.ctx = ctx;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return produits.size();
    }

    @Override
    public Object getItem(int position) {
        return produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.cart_item_list,parent,false);
        ImageView image = rowView.findViewById(R.id.cart_image);
        TextView name = rowView.findViewById(R.id.cart_name);
        TextView price = rowView.findViewById(R.id.cart_price);
        Spinner spinner = rowView.findViewById(R.id.cart_spinner);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ctx,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        name.setText(produits.get(position).getNom());
        Picasso.get().load(produits.get(position).getImage()).into(image);
        price.setText(produits.get(position).getPrix()+ "$");
        return rowView;
    }
}
