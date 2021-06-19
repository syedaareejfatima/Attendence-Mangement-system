package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SessionForm extends AppCompatActivity {

    RecyclerView recyclerView;
    Button SubmitSession;
    DBHelperStudent DbStudentApp;
    DBHelperSession DbSessionApp;
    ArrayList<Student> StudentArrayList = new ArrayList<Student>();
    ArrayList<Student> Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_form);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String CourseId_string = extras.getString("CourseId");
        String Date_string = extras.getString("Date");

        DbStudentApp = new DBHelperStudent(this);
        DbSessionApp = new DBHelperSession(this);
        Data = DbStudentApp.getAllData();

        for (Student student: Data) {
            String temp = student.getCIDS();
            String[] temp2 = temp.split("@");
            for (String Index: temp2) {
                if(Objects.equals(Index, CourseId_string)) {
                    StudentArrayList.add(student);
                }
            }
        }

        recyclerView = findViewById(R.id.rv_session_student);
        SubmitSession = findViewById(R.id.submit_session);

        RVSessionStudent rvSessionStudent = new RVSessionStudent(SessionForm.this, StudentArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvSessionStudent);

        SubmitSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean first = true;
                String SIDS = "";
                for (String id: rvSessionStudent.SIDS){
                    if(first){
                        SIDS = id;
                        first = false;
                    }
                    else
                        SIDS = SIDS + "@" + id;
                }
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                DbSessionApp.insertData(Date_string, dateFormat.format(date), CourseId_string, SIDS);
                Intent i= new Intent(SessionForm.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}