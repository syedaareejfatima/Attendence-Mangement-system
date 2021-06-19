package com.example.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.attendancemanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Button ViewStudents, CreateSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ViewStudents = findViewById(R.id.view_sessions);
        CreateSession = findViewById(R.id.create_session);

        CreateSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,SessionActivity.class);
                startActivity(i);
            }
        });

        ViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,SessionDisplay.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_student) {
            Toast.makeText(MainActivity.this,"Students Clicked",Toast.LENGTH_LONG).show();
            Intent i= new Intent(MainActivity.this, StudentActivity.class);
            startActivity(i);
        }
        else if (id == R.id.menu_course) {
            Toast.makeText(MainActivity.this,"Courses Clicked",Toast.LENGTH_LONG).show();
            Intent i= new Intent(MainActivity.this, CourseActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}