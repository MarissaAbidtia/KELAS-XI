package com.example.datepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTanggal = findViewById(R.id.etTanggal);

        etTanggal.setOnClickListener(view -> showDatePicker());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();

        int tahun = calendar.get(Calendar.YEAR);
        int bulan = calendar.get(Calendar.MONTH);
        int hari = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                (view, year, month, dayOfMonth) -> {
                    // Format tanggal ke dd-MM-yyyy
                    String selectedDate = dayOfMonth + "-" + (month + 1) + "-" + year;
                    etTanggal.setText(selectedDate);
                },
                tahun, bulan, hari
        );

        datePickerDialog.show();
    }
}
