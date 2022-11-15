package com.ebookfrenzy.projetibj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CardPaymentActivity extends AppCompatActivity {
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TOTAL_KEY = "total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_card);

        TextView total = findViewById(R.id.shipping_total);
        Button pay = findViewById(R.id.btn_pay);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        String strTotal = sharedPreferences.getString(TOTAL_KEY,null)+" $";
        total.setText(strTotal);
        pay.setText("PAY  "+strTotal);

    }
}