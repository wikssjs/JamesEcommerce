package com.ebookfrenzy.projetibj.mainFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ebookfrenzy.projetibj.Produit;
import com.ebookfrenzy.projetibj.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<Produit> listProduits;
    private final Context context;

    public RecyclerAdapter(List<Produit> listProduits, Context context) {
        this.listProduits = listProduits;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,price;
        public ImageView image ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nomId);
            price = itemView.findViewById(R.id.prixId);
            image = itemView.findViewById(R.id.imageId);
        }
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recycler_list_view,parent,false);
        return new RecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Produit produits = listProduits.get(position);
        holder.name.setText(produits.getNom());
        Picasso.get().load(produits.getImage()).into(holder.image);
        holder.price.setText(String.valueOf(produits.getPrix()));
    }

    @Override
    public int getItemCount() {
        return listProduits.size();
    }

    public void filterList(ArrayList<Produit> filteredList){
        listProduits = filteredList;
        notifyDataSetChanged();

    }
}
