package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SessionDetails extends AppCompatActivity {

    TextView Date, Time, Course;
    Session data;
    Course obj;
    DBHelperCourse DbCourseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_details);
        Date = (TextView) findViewById(R.id.date_display);
        Time = (TextView) findViewById(R.id.time_display);
        Course = (TextView) findViewById(R.id.course_code);

        Intent intent = getIntent();
        data  = (Session) intent.getSerializableExtra("Data");
        DbCourseApp = new DBHelperCourse(this);
        obj = DbCourseApp.getById(data.getCourseId());

        Date.setText(data.getDate());
        Time.setText(data.getTime());
        Course.setText(obj.getCourseCode());
    }
}