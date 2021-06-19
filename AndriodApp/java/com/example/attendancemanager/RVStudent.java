package com.example.attendancemanager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVStudent extends RecyclerView.Adapter<RVStudent.RVholder> {
    DBHelperStudent DbStudentApp;
    Context context;
    ArrayList<Student> data = new ArrayList<>();
    public RVStudent(Context con, ArrayList<Student> data) {
        this.context=con;
        this.data = data;
    }

    @NonNull
    @Override
    public RVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_layout,parent,false);
        RVholder rvHolder = new RVholder(view);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVholder holder, int position) {
        holder.Title.setText(data.get(position).getRollNumber());
        holder.ViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), StudentForm.class);
                i.putExtra("Data",data.get(position));
                context.startActivity(i);
            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbStudentApp = new DBHelperStudent(v.getContext());
                boolean check = DbStudentApp.deleteData(data.get(position).getRollNumber());
                if(check)
                    Toast.makeText(context,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context,"Data not Deleted",Toast.LENGTH_LONG).show();
                Intent i = new Intent(v.getContext(), MainActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RVholder extends RecyclerView.ViewHolder
    {
        TextView Title;
        Button ViewDetails, Delete;

        public RVholder(@NonNull View itemView) {
            super(itemView);
            Title=((TextView) itemView.findViewById(R.id.rv_index_display));
            ViewDetails=((Button) itemView.findViewById(R.id.rv_view_details));
            Delete=((Button) itemView.findViewById(R.id.rv_delete));
        }
    }
}

