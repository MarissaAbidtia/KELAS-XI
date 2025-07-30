package com.example.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class IntentActivity extends AppCompatActivity {

    EditText etNamaBarang;
    Button btnBarang, btnPenjualan, btnPembelian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        etNamaBarang = findViewById(R.id.etNamaBarang);
        btnBarang = findViewById(R.id.btnBarang);
        btnPenjualan = findViewById(R.id.btnPenjualan);
        btnPembelian = findViewById(R.id.btnPembelian);

        btnBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this, BarangActivity.class);
                intent.putExtra("nama", etNamaBarang.getText().toString());
                startActivity(intent);
            }
        });

        btnPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this, PenjualanActivity.class);
                intent.putExtra("nama", etNamaBarang.getText().toString());
                startActivity(intent);
            }
        });

        btnPembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this, PembelianActivity.class);
                intent.putExtra("nama", etNamaBarang.getText().toString());
                startActivity(intent);
            }
        });
    }
}
