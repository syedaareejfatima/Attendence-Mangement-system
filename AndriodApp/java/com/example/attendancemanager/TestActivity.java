    package com.example.attendancemanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    DBHelperSession DbSessionApp;
    ArrayList<Session> SessionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        DbSessionApp = new DBHelperSession(this);
        SessionArrayList = DbSessionApp.getAllData();
        String data = "";
        for (Session obj: SessionArrayList) {
            data = data + obj.getId() + "//" +
                    obj.getDate() + "//" +
                    obj.getTime() + "//" +
                    obj.getCourseId() + "//" +
                    obj.getSIDS() + "//" ;
        }
        if (data == "") data = "NULL";
        TextView test = findViewById(R.id.test_output);
        test.setText(data);
    }
}