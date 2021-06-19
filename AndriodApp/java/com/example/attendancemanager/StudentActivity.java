package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    DBHelperStudent DbStudentApp;
    ArrayList<Student> StudentArrayList;
    RecyclerView recyclerView;
    Button AddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Student List");
        setContentView(R.layout.activity_student);
        recyclerView = findViewById(R.id.rv_display);
        AddStudent = findViewById(R.id.add_student);

        DbStudentApp = new DBHelperStudent(this);
        StudentArrayList = DbStudentApp.getAllData();

        RVStudent rvstudent = new RVStudent(StudentActivity.this, StudentArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvstudent);

        AddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(StudentActivity.this,StudentForm.class);
                startActivity(i);
            }
        });
    }
}