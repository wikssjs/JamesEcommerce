package com.ebookfrenzy.projetibj.mainFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    private int quantity_save;

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
        CartProducts produit = produits.get(position);
        quantity_save = produit.getQuantity();


        rowView.findViewById(R.id.cart_delete_item_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produits.remove(position);
                notifyDataSetChanged();
            }
        });

        Button diminuer = rowView.findViewById(R.id.diminuer_button);
        diminuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = produit.getQuantity()-1;
                quantity_save = quantity;
                diminuer.setBackgroundTintList(ctx.getColorStateList(R.color.gray));
                diminuer.setEnabled(false);
                if(quantity>=1){
                    produit.setQuantity(quantity);
                    notifyDataSetChanged();
                }

            }
        });

        Button augmenter = rowView.findViewById(R.id.augmenter_button);
        augmenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity = produit.getQuantity()+1;
                quantity_save = quantity;

                    Log.i("quant",quantity+"");

                if(quantity<=10){
                    produit.setQuantity(quantity);
                    notifyDataSetChanged();
                }


            }
        });

        if(quantity_save==10){
            augmenter.setEnabled(false);
            augmenter.setBackgroundTintList(ctx.getColorStateList(R.color.gray));
        }

        else if(quantity_save==1){
            diminuer.setEnabled(false);
            diminuer.setBackgroundTintList(ctx.getColorStateList(R.color.gray));
        }

        double somme = 0;
        for (int i = 0; i < produits.size(); i++) {
            somme += produits.get(i).getProduit().getPrix() * produits.get(i).getQuantity();
        }
        CartFragment.setTotal(somme);

        name.setText(produits.get(position).getProduit().getNom());
        Picasso.get().load(produits.get(position).getProduit().getImage()).into(image);
        price.setText(produits.get(position).getProduit().getPrix()+ "$");
        quantity.setText(produits.get(position).getQuantity()+"");
        return rowView;
    }
}
