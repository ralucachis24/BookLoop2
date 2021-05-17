package com.example.bookloop2;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Calendar;


public class AddBook extends AppCompatActivity {

    Toolbar toolbar;
    EditText enterTitle, enterAuthor, review;
    RatingBar ratingBar;
    Calendar c;
    String today;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Book");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        enterTitle = findViewById(R.id.enterTitle);
        enterAuthor = findViewById(R.id.enterAuthor);
        review = findViewById(R.id.Review);
        ratingBar = findViewById(R.id.ratingBar);
        int numberOfStars = ratingBar.getNumStars();

        enterTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length() !=0 ){
                        getSupportActionBar().setTitle(s);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //get current date and time
        c = Calendar.getInstance();
        today = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);

        Log.d("calendar", "Date and Time: " + today);

    }

    private String pad(int i){
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        if(item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            onBackPressed(); //go back
        }
        if(item.getItemId() == R.id.save) {
            Book book = new Book(enterTitle.getText().toString(), enterAuthor.getText().toString(),
                    ratingBar.getNumStars(), review.getText().toString(), today);
            BookDB db = new BookDB(this);
            db.addBook(book);
            Toast.makeText(this, "Book added to your shelf", Toast.LENGTH_SHORT).show();

            goToMain();//go back to previous screen
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}