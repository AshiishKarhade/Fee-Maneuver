package com.example.fees2;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public StudentViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDisplayName(String name){

        TextView userNameView = mView.findViewById(R.id.student_single_name);
        userNameView.setText(name);

    }

    public void setUserCaste(String status){

        TextView userStatusView = mView.findViewById(R.id.student_single_caste);
        userStatusView.setText(status);
    }

}
