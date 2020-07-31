package com.example.quizgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BooksMarksAdapter extends  RecyclerView.Adapter<BooksMarksAdapter.viewHolder> {
private List<Question_model>list;
private Context context;

    public BooksMarksAdapter(List<Question_model> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_customlist,parent,false);
    return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
         holder.setData(list.get(position).getQuestion(),list.get(position).getCorrectAnswer(),position);
       }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        private TextView question,answer;
        private ImageButton delete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            question=itemView.findViewById(R.id.question);
            answer=itemView.findViewById(R.id.answer);
            delete =itemView.findViewById(R.id.delete);
        }
           private void setData(String question, String answer, final int position){
            this.question.setText(question);
            this.answer.setText("Answer :- "+answer);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Deleted SuccessFully", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }


}
