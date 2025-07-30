package com.example.cardviewdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> userList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        userList = new ArrayList<>();

        // Dummy data
        userList.add(new User("Budi Joni", "JL Jawa Surabaya Jawa Timur"));
        userList.add(new User("Ani", "JL Merdeka Bandung Jawa Barat"));
        userList.add(new User("Dewi", "JL Kaliurang Yogyakarta"));

        adapter = new UserAdapter(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
