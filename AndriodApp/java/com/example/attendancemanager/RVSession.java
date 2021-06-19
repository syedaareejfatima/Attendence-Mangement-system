package com.example.attendancemanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVSession extends RecyclerView.Adapter<RVSession.RVholder>{

    DBHelperSession DbSessionApp;
    Context context;
    ArrayList<Session> data = new ArrayList<>();

    public RVSession(Context con, ArrayList<Session> data) {
        this.context=con;
        this.data = data;
    }

    @NonNull
    @Override
    public RVSession.RVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_layout,parent,false);
        RVSession.RVholder rvHolder = new RVSession.RVholder(view);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVSession.RVholder holder, int position) {
        holder.Title.setText(data.get(position).getDate());
        holder.ViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SessionDetails.class);
                i.putExtra("Data",data.get(position));
                context.startActivity(i);
            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbSessionApp = new DBHelperSession(v.getContext());
                boolean check = DbSessionApp.deleteData(data.get(position).getId());
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
