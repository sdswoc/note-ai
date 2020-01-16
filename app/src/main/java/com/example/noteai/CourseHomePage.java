package com.example.noteai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CourseHomePage extends AppCompatActivity {
private RecyclerView mNotesList;
private DatabaseReference mDatabase;
    private FirebaseAuth mAuthenticate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_home_page);
        mAuthenticate=FirebaseAuth.getInstance();
        String auser_id=mAuthenticate.getCurrentUser().getUid();
        mNotesList=findViewById(R.id.notes_list);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Personal Notes").child(auser_id);
        mNotesList.setHasFixedSize(true);
        mNotesList.setLayoutManager(new LinearLayoutManager(CourseHomePage.this));
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Notes,NoteViewHolder>
        FirebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Notes, NoteViewHolder>(

                Notes.class,R.layout.notes_row,NoteViewHolder.class,mDatabase
        ) {
            @Override
            protected void populateViewHolder(NoteViewHolder noteViewHolder, Notes notes, int i) {
                noteViewHolder.setTitle(notes.getTitle());
                noteViewHolder.setName(notes.getName());
                noteViewHolder.setDate(notes.getDate());
                noteViewHolder.setMain(notes.getMain());
                noteViewHolder.setSummary(notes.getSummary());
            }
        };
        mNotesList.setAdapter(FirebaseRecyclerAdapter);
    }
    public static class NoteViewHolder extends RecyclerView.ViewHolder{
    View mView;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title){
            TextView post_title=(TextView) mView.findViewById(R.id.title);
            post_title.setText(title);
        }
        public void setName(String name){
            TextView post_name=(TextView) mView.findViewById(R.id.name);
            post_name.setText(name);
        }
        public void setDate(String date){
            TextView post_date=(TextView) mView.findViewById(R.id.time);
            post_date.setText(date);
        }
        public void setMain(String main){
            TextView post_title=(TextView) mView.findViewById(R.id.maint);
            post_title.setText(main);
        }
        public void setSummary(String summary){
            TextView post_title=(TextView) mView.findViewById(R.id.summary);
            post_title.setText(summary);
        }
    }
}
