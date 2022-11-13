package com.ebookfrenzy.projetibj;

import java.util.Objects;

public class CartProducts {

    private Produit produit;
    private int quantity = 0;

    public CartProducts(Produit p, int qt) {
        produit = p;
        quantity = qt;
    }


    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartProducts)) return false;
        return Objects.equals(produit.getNom(), ((CartProducts) o).getProduit().getNom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(produit, quantity);
    }
}