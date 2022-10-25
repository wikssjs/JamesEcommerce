package com.ebookfrenzy.projetibj.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.ebookfrenzy.projetibj.ItemBuyingActivity;
import com.ebookfrenzy.projetibj.Produit;
import com.ebookfrenzy.projetibj.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View rowView2;
    public ArrayList<Produit> list;
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list= Produit.liste;
        ProductsAdapter adapter = new ProductsAdapter(list,getContext());
        View rowView = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = rowView.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

         rowView2= inflater.inflate(R.layout.activity_item_buying,container,false);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showProduct(position);
            }
        });
        return rowView;
    }

    private void showProduct(int position) {
        Intent intent = new Intent(getContext(), ItemBuyingActivity.class);

        intent.putExtra("produit",list.get(position));
        startActivity(intent);
    }


}



