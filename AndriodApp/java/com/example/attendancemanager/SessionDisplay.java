package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SessionDisplay extends AppCompatActivity {

    DBHelperSession DbSessionApp;
    ArrayList<Session> SessionArrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesson_display);
        recyclerView = findViewById(R.id.rv_session_display);

        DbSessionApp = new DBHelperSession(this);
        SessionArrayList = DbSessionApp.getAllData();

        RVSession rvSession = new RVSession(SessionDisplay.this, SessionArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvSession);
    }
}