package com.ebookfrenzy.projetibj;

import android.widget.Spinner;

public class CartProducts{

    private Spinner spinner;
    private Produit produit;

    public CartProducts(Produit p,Spinner spn){
        produit = p;
        spinner = spn;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }


    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
