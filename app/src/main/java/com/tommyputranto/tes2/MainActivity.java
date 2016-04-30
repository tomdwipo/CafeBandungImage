package com.tommyputranto.tes2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tes = (TextView)findViewById(R.id.tes);
        String hasil ="";
        if (getIntent().hasExtra("tes")){
            hasil = getIntent().getStringExtra("tes");
            tes.setText(hasil);
        }
    }
}
