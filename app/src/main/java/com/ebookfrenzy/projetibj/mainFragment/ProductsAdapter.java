package com.ebookfrenzy.projetibj.mainFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebookfrenzy.projetibj.Produit;
import com.ebookfrenzy.projetibj.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Produit> list;

    public ProductsAdapter(ArrayList<Produit> list, Context ctx) {
        this.list = list;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View v = inflater.inflate(R.layout.grid_list_view, null);
        TextView nom = v.findViewById(R.id.nomId);
        ImageView image = v.findViewById(R.id.imageId);
        TextView prix = v.findViewById(R.id.prixId);


        nom.setText(list.get(position).getNom());
        Picasso.get().load(list.get(position).getImage()).into(image);
        prix.setText(list.get(position).getPrix()+ "$");

        return v;
    }


}
