package com.ebookfrenzy.projetibj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;
import com.paypal.android.sdk.payments.PayPalConfiguration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShippingActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "AeXBed8PWE4UvAF5-tpaNQkblz5NVdEQM6ui71budB9fVXfmeJ_m2MQ5UYbmxLlrVzE5aRyMp6xssF5J";
    EditText adress1,adress2,city,zipCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        PayPalConfiguration config = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(CLIENT_ID);
        adress1 = findViewById(R.id.edt_adress1);
        adress2 = findViewById(R.id.edt_address2);
        city = findViewById(R.id.eddt_city);
        zipCode = findViewById(R.id.edt_ZIP);
        CountryCodePicker ccp = findViewById(R.id.ccp);


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputsValid()){
                    Intent i = new Intent(ShippingActivity.this,PaymentMethod.class);
                    i.putExtra("address1",adress1.getText().toString());
                    i.putExtra("address2",adress2.getText().toString());
                    i.putExtra("city",city.getText().toString());
                    i.putExtra("country",ccp.getDefaultCountryName());
                    i.putExtra("zip",zipCode.getText().toString());
                    startActivity(i);
                }
            }
        });
    }

    private boolean inputsValid() {

        if(adress1.getText().length()==0){
            adress1.setError("Adress required");
            adress1.requestFocus();
        }
        if(city.getText().length()==0){
            city.setError("City required");
            city.requestFocus();
        }

        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
        Pattern pattern = Pattern.compile(regex);

        String  strZip = zipCode.getText().toString();
        Matcher matcher = pattern.matcher(strZip);
        boolean strMatch = matcher.matches();

        if(!strMatch){
            zipCode.setError("Postal required(K1K 1K1)");
            zipCode.requestFocus();
        }
        return adress1.getText().length()!=0&&city.getText().length()!=0
                    /*&&strMatch*/;
    }


}