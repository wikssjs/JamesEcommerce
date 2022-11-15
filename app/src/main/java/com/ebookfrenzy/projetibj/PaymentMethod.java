package com.ebookfrenzy.projetibj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);


        Bundle b = getIntent().getExtras();
        TextView address = findViewById(R.id.pm_adress);
        String address1 = b.getString("address1");
        String address2 = b.getString("address2");
        String city = b.getString("city");
        String country =  b.getString("country");
        String zip =  b.getString("zip");


        address.setText(address1+" "+address2+"\n"+city+", "+zip+" "+country);






        RadioButton rdb_card = findViewById(R.id.rdb_card);
        RadioButton rdb_paypal = findViewById(R.id.rdb_paypal);
        findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rdb_card.setChecked(true);
                rdb_paypal.setChecked(false);
                Toast.makeText(PaymentMethod.this,rdb_card.isChecked()+"",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.paypal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdb_paypal.setChecked(true);
                rdb_card.setChecked(false);
                Toast.makeText(PaymentMethod.this,rdb_card.isChecked()+"",Toast.LENGTH_LONG).show();

            }
        });

        findViewById(R.id.btn_pm_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdb_card.isChecked()){
                    startActivity(new Intent(PaymentMethod.this, CardPaymentActivity.class));
                }
                else{
                    startActivity(new Intent(PaymentMethod.this,PaypalPaymentActivity.class));
                }
            }
        });


    }

}