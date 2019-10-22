package com.example.fees2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fees2.ProfileActivity;
import com.example.fees2.R;
import com.example.fees2.Student;
import com.example.fees2.StudentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sc_fragment extends Fragment {
    private static final String tag="sc fragment";


    private RecyclerView studentList3;
    private LinearLayoutManager layoutManager3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference("student");
    private FirebaseRecyclerAdapter adapter3;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate (R.layout.sc_fragment,container,false);

        layoutManager3 = new LinearLayoutManager(getActivity());
        studentList3 = view.findViewById(R.id.sc_listview);
        studentList3.setLayoutManager(layoutManager3);
        fetch();

        return view;
    }

    private void fetch() {

        Query query = dbRef.orderByChild("caste").equalTo("sc");

        FirebaseRecyclerOptions<Student> options3 = new FirebaseRecyclerOptions.Builder<Student>().setQuery(query, Student.class).build();

        adapter3 = new FirebaseRecyclerAdapter<Student, StudentViewHolder>(options3) {

            @NonNull
            @Override
            public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_single, parent, false);
                return new StudentViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int position, @NonNull Student student) {
                studentViewHolder.setDisplayName(student.getName());
                studentViewHolder.setUserCaste(student.getCaste());

                final String user_id = getRef(position).getKey();

                studentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent profileGenIntent = new Intent(getActivity(), ProfileActivity.class);
                        profileGenIntent.putExtra("user_id", user_id);
                        startActivity(profileGenIntent);

                    }
                });
            }
        };



        studentList3.setAdapter(adapter3);

    }

    @Override
    public void onStart() {
        super.onStart();

        adapter3.startListening();

    }


}

