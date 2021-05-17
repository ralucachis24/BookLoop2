package com.example.bookloop2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "bookdb";
    private static final String DATABASE_TABLE = "booktbl";

    //columns
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_RATING = "rating";
    private static final String KEY_REVIEW = "review";
    private static final String KEY_DATE = "date";



    BookDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        String query =  "CREATE TABLE " + DATABASE_TABLE + "("+ KEY_ID + " INT PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_AUTHOR + " TEXT," +
                KEY_RATING + " INT," +
                KEY_REVIEW + " TEXT," +
                KEY_DATE + " TEXT"+ ")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion) return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db) ;
    }

    public long addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, book.getTitle());
        c.put(KEY_AUTHOR, book.getAuthor());
        c.put(KEY_RATING, book.getRating());
        c.put(KEY_REVIEW, book.getReview());

        long ID = db.insert(DATABASE_TABLE, null, c);
        //check if data is successfully inserted in the db, return -1 instead
        Log.d("Inserted", "ID -> " + ID);

        return ID;
    }


    public Book getBook(long id){
        //retrieve data from db with id
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor points to a specific row in the db
        Cursor cursor = db.query(DATABASE_TABLE,
                            new String[]{KEY_ID, KEY_TITLE, KEY_AUTHOR, KEY_RATING, KEY_REVIEW, KEY_DATE},
                            KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Book book = new Book(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                            cursor.getInt(3), cursor.getString(4), cursor.getString(5));

        return book;

    }



    public List<Book> getBooks(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM " + DATABASE_TABLE;

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setID(cursor.getLong(0));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setRating(cursor.getInt(3));
                book.setReview(cursor.getString(4));
                book.setDate(cursor.getString(5));

                books.add(book);

            }while(cursor.moveToNext());
        }
        return books;
    }


}
