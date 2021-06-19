package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    DBHelperCourse DbCourseApp;
    ArrayList<Course> CourseArrayList;
    RecyclerView recyclerView;
    Button AddCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Course List");
        setContentView(R.layout.activity_course);
        recyclerView = findViewById(R.id.rv_course_display);
        AddCourse = findViewById(R.id.add_course);

        DbCourseApp = new DBHelperCourse(this);
        CourseArrayList = DbCourseApp.getAllData();

        RVCourse rvCourse = new RVCourse(CourseActivity.this, CourseArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvCourse);

        AddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(CourseActivity.this,CourseForm.class);
                startActivity(i);
            }
        });
    }
}