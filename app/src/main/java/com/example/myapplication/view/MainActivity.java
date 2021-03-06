package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Singletons;
import com.example.myapplication.api.MakeupAPI;
import com.example.myapplication.R;
import com.example.myapplication.controller.MainController;
import com.example.myapplication.model.Makeup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = Singletons.getSharedPreferences(getApplicationContext());
        Gson gson = Singletons.getGson();
        controller = new MainController(this, gson, sp);
        controller.onStart();
    }

    public void showList(List<Makeup> makeupList) {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(makeupList);
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(this, "API error", Toast.LENGTH_SHORT).show();
    }
}
