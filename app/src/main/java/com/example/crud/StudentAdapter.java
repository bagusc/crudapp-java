package com.example.crud;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import java.io.Serializable;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> listStudents = new ArrayList<>();
    private Activity activity;
    private DbHelper dbHelper;

    public StudentAdapter(Activity activity) {
        this.activity = activity;
        dbHelper = new DbHelper(activity);
    }

    public ArrayList<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(ArrayList<Student> listStudents) {
        if (listStudents.size() > 0) {
            this.listStudents.clear();
        }
        this.listStudents.addAll(listStudents);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.tvNim.setText(listStudents.get(position).getNim());
        holder.tvName.setText(listStudents.get(position).getName());
        holder.btnEdit.setOnClickListener((View v) -> {
            Intent intent = new Intent(activity, UpdateActivity.class);
            intent.putExtra("user", (Serializable) listStudents.get(position));
            activity.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setTitle("Konfirmasi hapus");
            builder.setMessage("Apakah yakin akan dihapus?");

            builder.setPositiveButton("YA", (dialog, which) -> {
                dbHelper.deleteUser(listStudents.get(position).getId());
                Toast.makeText(activity, "Hapus berhasil!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity, ListStudentsActivity.class);
                activity.startActivity(myIntent);
                activity.finish();
            });

            builder.setNegativeButton("TIDAK", (dialog, which) -> dialog.dismiss());

            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        final TextView tvNim, tvName;
        final Button btnEdit, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tv_item_nim);
            tvName = itemView.findViewById(R.id.tv_item_name);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
