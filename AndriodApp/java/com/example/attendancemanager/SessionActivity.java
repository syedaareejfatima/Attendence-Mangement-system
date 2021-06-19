package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SessionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,DatePickerDialog.OnDateSetListener {

    private String Date, CourseCode, CourseId;
    DBHelperCourse DbCourseApp;
    List<String> CourseCodes;
    Button DateInput, Create;
    TextView CourseDisplay;
    ArrayList<Course> CourseArrayList;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        DateInput = (Button) findViewById(R.id.date_input);
        CourseDisplay = findViewById(R.id.course_display);
        Create = findViewById(R.id.create);

        CourseCodes = new ArrayList<String>();
        DbCourseApp = new DBHelperCourse(this);
        CourseArrayList = DbCourseApp.getAllData();
        for (Course course : CourseArrayList) {
            CourseCodes.add(course.getCourseCode());
        }

        spinner = (Spinner) findViewById(R.id.course_input);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CourseCodes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseCode = String.valueOf(spinner.getSelectedItem());
                //Toast.makeText(v.getContext(), "Selected: " + CourseCode, Toast.LENGTH_LONG).show();
                for (Course course: CourseArrayList) {
                    if(CourseCode == course.getCourseCode())
                    CourseId = course.getId();
                }
                Intent i= new Intent(SessionActivity.this,SessionForm.class);
                Bundle extras = new Bundle();
                extras.putString("CourseId",CourseId);
                extras.putString("Date",Date);
                i.putExtras(extras);
                startActivity(i);
            }
        });

        DateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Date = month + "/" + dayOfMonth + "/" + year;
        DateInput.setText(Date);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
