package com.ebookfrenzy.projetibj.mainFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebookfrenzy.projetibj.CartProducts;
import com.ebookfrenzy.projetibj.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartFragmentAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<CartProducts> produits;
    Context ctx;

    public CartFragmentAdapter(ArrayList<CartProducts> produits, Context ctx){
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
        TextView quantity = rowView.findViewById(R.id.quantity_id);


        rowView.findViewById(R.id.cart_delete_item_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produits.remove(position);
                notifyDataSetChanged();
            }
        });

        rowView.findViewById(R.id.diminuer_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartProducts produit = produits.get(position);
                int quantity = produit.getQuantity();
                if(quantity>1){
                    produit.setQuantity(quantity-1);
                    notifyDataSetChanged();
                }

            }
        });

        rowView.findViewById(R.id.augmenter_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartProducts produit = produits.get(position);
                int quantity = produit.getQuantity();
                if(quantity<10){
                    produit.setQuantity(quantity+1);
                    notifyDataSetChanged();

                }
            }
        });
        double somme = 0;
        for (int i = 0; i < produits.size(); i++) {
            Log.i("james",i+" i");
            somme += produits.get(i).getProduit().getPrix() * produits.get(i).getQuantity();
            Log.i("quant",somme+"");
        }
        CartFragment.setTotal(somme);

        name.setText(produits.get(position).getProduit().getNom());
        Picasso.get().load(produits.get(position).getProduit().getImage()).into(image);
        price.setText(produits.get(position).getProduit().getPrix()+ "$");
        quantity.setText(produits.get(position).getQuantity()+"");
        return rowView;
    }
}
