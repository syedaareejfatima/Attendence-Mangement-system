package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CourseForm extends AppCompatActivity {
    EditText CourseCode, Description;
    TextView ID;
    Button Submit;
    DBHelperCourse DbCourseApp;
    Course data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_form);
        DbCourseApp = new DBHelperCourse(this);

        CourseCode = findViewById(R.id.course_code_input);
        Description = findViewById(R.id.course_description_input);
        ID = findViewById(R.id.course_id_holder);
        Submit = (Button)findViewById(R.id.submit_course);
        long Id = DbCourseApp.getcount() + 1;
        ID.setText("ID: " + Long.toString(Id));

        Intent intent = getIntent();
        if(intent.hasExtra("Data"))
        {
            Submit.setText("Update");
            data  = (Course) intent.getSerializableExtra("Data");
            CourseCode.setText(data.getCourseCode());
            Description.setText(data.getDescription());
            ID.setText(data.getId());
            intent.replaceExtras(new Bundle());
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check= DbCourseApp.updateData( ID.getText().toString(), CourseCode.getText().toString(), Description.getText().toString());
                    if(check)
                        Toast.makeText(CourseForm.this,"Data Updated",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(CourseForm.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    Intent i= new Intent(CourseForm.this,MainActivity.class);
                    startActivity(i);
                }
            });
        }
        else {
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check = DbCourseApp.insertData(CourseCode.getText().toString(), Description.getText().toString());
                    if (check)
                        Toast.makeText(CourseForm.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(CourseForm.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(CourseForm.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }
    }
}