package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentForm extends AppCompatActivity {
    EditText RollNumber,Name,SectionId,CIDS;
    Button Submit;
    DBHelperStudent DbStudentApp;
    Student data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_form);
        DbStudentApp = new DBHelperStudent(this);

        RollNumber = findViewById(R.id.rollnumber_input);
        Name = findViewById(R.id.name_input);
        SectionId = findViewById(R.id.sectionid_input);
        CIDS = findViewById(R.id.course_ids_input);
        Submit = (Button)findViewById(R.id.submit_student);

        Intent intent = getIntent();
        if(intent.hasExtra("Data"))
        {
            Submit.setText("Update");
            data  = (Student) intent.getSerializableExtra("Data");
            RollNumber.setText(data.getRollNumber());
            Name.setText(data.getName());
            SectionId.setText(data.getSectionId());
            CIDS.setText(data.getCIDS());
            intent.replaceExtras(new Bundle());
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check= DbStudentApp.updateData( RollNumber.getText().toString(), Name.getText().toString(), SectionId.getText().toString(), CIDS.getText().toString() );
                    Log.i("DDDBBB","check"+check);
                    if(check)
                        Toast.makeText(StudentForm.this,"Data Updated",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(StudentForm.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    Intent i= new Intent(StudentForm.this,MainActivity.class);
                    startActivity(i);
                }
            });
        }
        else {
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check = DbStudentApp.insertData(RollNumber.getText().toString(), Name.getText().toString(), SectionId.getText().toString(), CIDS.getText().toString());
                    Log.i("DDDBBB", "check" + check);
                    if (check)
                        Toast.makeText(StudentForm.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(StudentForm.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(StudentForm.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }
    }
}