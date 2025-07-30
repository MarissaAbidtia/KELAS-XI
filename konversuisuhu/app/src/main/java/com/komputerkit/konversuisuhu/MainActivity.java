package com.example.konversisuhu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.text.TextWatcher;
import android.text.Editable;

public class MainActivity extends AppCompatActivity {

    private EditText etInputSuhu;
    private Spinner spinnerFrom, spinnerTo;
    private TextView tvHasil, tvSatuan;
    private Button btnKonversi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        etInputSuhu = findViewById(R.id.etInputSuhu);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        tvHasil = findViewById(R.id.tvHasil);
        tvSatuan = findViewById(R.id.tvSatuan);
        btnKonversi = findViewById(R.id.btnKonversi);

        // Setup spinner dengan pilihan suhu
        String[] satuanSuhu = {"Celsius", "Fahrenheit", "Kelvin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, satuanSuhu);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Set default selection
        spinnerFrom.setSelection(0); // Celsius
        spinnerTo.setSelection(1);   // Fahrenheit

        // Event listener untuk tombol konversi
        btnKonversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konversiSuhu();
            }
        });

        // Auto konversi saat mengetik
        etInputSuhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    konversiSuhu();
                } else {
                    tvHasil.setText("0");
                    tvSatuan.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void konversiSuhu() {
        String inputText = etInputSuhu.getText().toString();

        if (inputText.isEmpty()) {
            tvHasil.setText("0");
            tvSatuan.setText("");
            return;
        }

        try {
            double inputSuhu = Double.parseDouble(inputText);
            String fromUnit = spinnerFrom.getSelectedItem().toString();
            String toUnit = spinnerTo.getSelectedItem().toString();

            double hasil = hitungKonversi(inputSuhu, fromUnit, toUnit);

            // Format hasil dengan 2 desimal
            String hasilFormatted = String.format("%.2f", hasil);
            tvHasil.setText(hasilFormatted);
            tvSatuan.setText(getSatuanSimbol(toUnit));

        } catch (NumberFormatException e) {
            tvHasil.setText("Error");
            tvSatuan.setText("");
        }
    }

    private double hitungKonversi(double nilai, String dari, String ke) {
        // Konversi ke Celsius terlebih dahulu
        double celsius;
        switch (dari) {
            case "Celsius":
                celsius = nilai;
                break;
            case "Fahrenheit":
                celsius = (nilai - 32) * 5.0 / 9.0;
                break;
            case "Kelvin":
                celsius = nilai - 273.15;
                break;
            default:
                celsius = nilai;
        }

        // Konversi dari Celsius ke satuan tujuan
        double hasil;
        switch (ke) {
            case "Celsius":
                hasil = celsius;
                break;
            case "Fahrenheit":
                hasil = (celsius * 9.0 / 5.0) + 32;
                break;
            case "Kelvin":
                hasil = celsius + 273.15;
                break;
            default:
                hasil = celsius;
        }

        return hasil;
    }

    private String getSatuanSimbol(String satuan) {
        switch (satuan) {
            case "Celsius":
                return "°C";
            case "Fahrenheit":
                return "°F";
            case "Kelvin":
                return "K";
            default:
                return "";
        }
    }
}