package com.example.attendancemanager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVSessionStudent extends RecyclerView.Adapter<RVSessionStudent.RVholder> {
    Context context;
    ArrayList<Student> data = new ArrayList<Student>();
    ArrayList<String> SIDS = new ArrayList<String>();

    public RVSessionStudent(Context con, ArrayList<Student> data) {
        this.context=con;
        this.data = data;
    }

    @NonNull
    @Override
    public RVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_ss_layout,parent,false);
        RVholder rvHolder = new RVholder(view);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVholder holder, int position) {
        holder.Student.setText(data.get(position).getRollNumber());
        holder.IsPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.IsPresent.isChecked()){
                    SIDS.add(data.get(position).getRollNumber());
                }
                else{
                    SIDS.remove(data.get(position).getRollNumber());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RVholder extends RecyclerView.ViewHolder
    {
        TextView Student;
        CheckBox IsPresent;

        public RVholder(@NonNull View itemView) {
            super(itemView);
            Student=((TextView) itemView.findViewById(R.id.student_display));
            IsPresent=((CheckBox) itemView.findViewById(R.id.is_present));
        }
    }
}

