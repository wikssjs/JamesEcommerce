package com.ebookfrenzy.projetibj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ebookfrenzy.projetibj.mainFragment.CartFragment;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ItemBuyingActivity extends AppCompatActivity {

private PopupWindow popupWindow;
private int count = 0;

    private ConstraintLayout constraintLayout;
    LayoutInflater layoutInflater;
    ViewGroup container ;

//    private static final String nomFichier = "fruit.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buying);

        constraintLayout =(ConstraintLayout) findViewById(R.id.constraint);
        TextView P_name = findViewById(R.id.buying_product_name);
        TextView P_price = findViewById(R.id.buying_price);
        TextView amount_in_cart = findViewById(R.id.cart_count_item);
        ImageView P_image = findViewById(R.id.buying_image);
        getCount(amount_in_cart);
        Bundle bundle = getIntent().getExtras();

        Produit produit  = (Produit) bundle.get("produit");
        String name = produit.getNom();
        String price = String.valueOf(produit.getPrix());
        String image = produit.getImage();


        P_name.setText(name);
        P_price.setText(price);
        Picasso.get().load(image).into(P_image);

        Button addTocart = findViewById(R.id.button_addToCart);



        addTocart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                CartProducts cartProducts = new CartProducts(produit,1);

                if(productExist(produit)) {
                   increaseQuantity(cartProducts);
                }
                else{
                    CartFragment.produits.add(cartProducts);
                Toast.makeText(getApplicationContext(),cartProducts.getProduit().getNom()+" Added to Cart",Toast.LENGTH_LONG).show();
                }
                    getCount(amount_in_cart);

            }
        });

        findViewById(R.id.cart_floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemBuyingActivity.this,MainActivity.class);
                i.putExtra("position",2);
                startActivity(i);
            }
        });
    }

    private void increaseQuantity(CartProducts products) {
        int quantity;

        for(CartProducts p:CartFragment.produits){
            if(p.equals(products)){
                quantity =p.getQuantity();
                if(quantity<10) {
                    p.setQuantity(quantity + 1);
                }
                else{
                    Toast.makeText(getApplicationContext(),"trop",Toast.LENGTH_LONG).show();

                    layoutInflater = (LayoutInflater) getApplicationContext().getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window,null);

                    popupWindow = new PopupWindow(container,1000,1000,true);
                    popupWindow.showAtLocation(constraintLayout, Gravity.CENTER,0,0);
                    container.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            popupWindow.dismiss();
                            return true;
                        }
                    });

                 container.findViewById(R.id.popup_window_goToCart).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Toast.makeText(getApplicationContext(),"cart",Toast.LENGTH_LONG).show();
                         Intent i = new Intent(ItemBuyingActivity.this,MainActivity.class);
                         i.putExtra("position",2);
                         startActivity(i);
                     }
                 });
                }
            }
        }
    }

    private boolean productExist(Produit pr) {
        for (int i = 0; i < CartFragment.produits.size(); i++) {
            Produit produit = CartFragment.produits.get(i).getProduit();

            if (Objects.equals(pr.getNom(), produit.getNom())) {
                return true;
            }
        }
        return false;
    }

    private void getCount(TextView t){

        for (int i=0;i<CartFragment.produits.size();i++){
            count+=CartFragment.produits.get(i).getQuantity();
            Log.i("caca",CartFragment.produits.get(i).getQuantity()+"");
        }
        int quant = count;
        t.setText(count+"");
        count=0;
    }



}