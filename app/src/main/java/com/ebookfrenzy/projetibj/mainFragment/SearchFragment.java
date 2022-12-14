package com.ebookfrenzy.projetibj.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ebookfrenzy.projetibj.ItemBuyingActivity;
import com.ebookfrenzy.projetibj.MainActivity;
import com.ebookfrenzy.projetibj.Produit;
import com.ebookfrenzy.projetibj.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements IRecyvlerView {
    private RecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        View rowView = inflater.inflate(R.layout.fragment_search, container, false);
        View v = inflater.inflate(R.layout.grid_list_view,container,false);

        recyclerAdapter = new RecyclerAdapter(this, MainActivity.produits,getContext());
        RecyclerView recyclerView = rowView.findViewById(R.id.search_RecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        filter("sljadakdjakd");


        EditText edtSearch = rowView.findViewById(R.id.edtSearch_Id);
        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                Toast.makeText(getContext(), "search", Toast.LENGTH_LONG).show();
                if (s.length() == 0) {
                    filter("slkdakdladkaldkaldkadlkdla");
                }
            }
        });

        // Inflate the layout for this fragment
        return rowView;
    }

    private  void filter(String text){
        ArrayList<Produit> filterList = new ArrayList<>();

        for(Produit produit :MainActivity.produits){
            if(produit.getNom().toLowerCase().contains(text.toLowerCase())){
                filterList.add(produit);
            }
        }
        recyclerAdapter.filterList(filterList);
    }
    public void showProduct(Produit p) {

    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new  Intent(getContext(), ItemBuyingActivity.class);
        intent.putExtra("produit",MainActivity.produits.get(position));
        startActivity(intent);
    }
}