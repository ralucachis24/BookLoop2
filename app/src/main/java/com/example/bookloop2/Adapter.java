package com.example.bookloop2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Book> books;

    Adapter(Context context, List<Book> books){
        this.inflater = LayoutInflater.from(context);
        this.books = books;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.costum_list_view, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {

        String title = books.get(i).getTitle();
        String author = books.get(i).getAuthor();
        String date = books.get(i).getDate();
        viewHolder.bTitle.setText(title);
        viewHolder.bDate.setText(author);
        viewHolder.bDate.setText(date);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //retrieve data from books list and save it

        TextView bTitle, bAuthor, bDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bTitle = itemView.findViewById(R.id.bTitle);
            bAuthor = itemView.findViewById(R.id.bAuthor);
            bDate = itemView.findViewById(R.id.bDate);

        }
    }
}
