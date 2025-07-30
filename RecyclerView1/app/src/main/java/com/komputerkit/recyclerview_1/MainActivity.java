// MainActivity.java
package com.example.recyclerviewcardview;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText editNama, editTTL, editAlamat, editTelepon, editOrangTua;
    Button btnTambah;
    RecyclerView recyclerView;
    ArrayList<String> dataList = new ArrayList<>();
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNama = findViewById(R.id.editNama);
        editTTL = findViewById(R.id.editTTL);
        editAlamat = findViewById(R.id.editAlamat);
        editTelepon = findViewById(R.id.editTelepon);
        editOrangTua = findViewById(R.id.editOrangTua);
        btnTambah = findViewById(R.id.btnTambah);
        recyclerView = findViewById(R.id.recyclerView);

        // Tanggal lahir
        editTTL.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(MainActivity.this, (datePicker, year, month, day) -> {
                editTTL.setText(day + "/" + (month + 1) + "/" + year);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        adapter = new DataAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Tambah data ke RecyclerView
        btnTambah.setOnClickListener(view -> {
            String nama = editNama.getText().toString();
            String ttl = editTTL.getText().toString();

            if (!nama.isEmpty() && !ttl.isEmpty()) {
                dataList.add(nama + " - " + ttl);
                adapter.notifyDataSetChanged();

                editNama.setText("");
                editTTL.setText("");
            } else {
                Toast.makeText(this, "Isi nama dan TTL terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Adapter RecyclerView
    class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
        ArrayList<String> localData;

        DataAdapter(ArrayList<String> data) {
            this.localData = data;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView text;

            ViewHolder(TextView itemView) {
                super(itemView);
                text = itemView;
                itemView.setOnClickListener(view -> {
                    int position = getAdapterPosition();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Konfirmasi Hapus")
                            .setMessage("Yakin ingin menghapus item ini?")
                            .setPositiveButton("Ya", (dialog, which) -> {
                                localData.remove(position);
                                notifyItemRemoved(position);
                            })
                            .setNegativeButton("Tidak", null)
                            .show();
                });
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView v = new TextView(parent.getContext());
            v.setPadding(30, 30, 30, 30);
            v.setTextSize(18);
            v.setTextColor(Color.BLACK);
            v.setBackgroundColor(Color.YELLOW);
            v.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(localData.get(position));
        }

        @Override
        public int getItemCount() {
            return localData.size();
        }
    }
}
