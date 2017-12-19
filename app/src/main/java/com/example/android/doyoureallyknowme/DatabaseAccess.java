package com.example.android.doyoureallyknowme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<Question> getQuestions() {
        List<Question> list = new ArrayList<>();
        Cursor questionCursor = database.rawQuery("SELECT * FROM questions;", null);
        //questionCursor.moveToFirst();
        Question question;
        Cursor answerCursor;
        while (questionCursor.moveToNext()) {
            question=new Question();
            int id= questionCursor.getInt(questionCursor.getColumnIndexOrThrow("id"));
            question.setQuestion(questionCursor.getString(questionCursor.getColumnIndexOrThrow("question")));
            question.setType(questionCursor.getString(questionCursor.getColumnIndexOrThrow("type")));
            question.setSubtitle(questionCursor.getString(questionCursor.getColumnIndexOrThrow("subtitle")));
            answerCursor = database.rawQuery("SELECT * FROM answers WHERE questionId=" + id, null);
            List<String> answers= new ArrayList<>();
            while (answerCursor.moveToNext()){
                answers.add(answerCursor.getString(answerCursor.getColumnIndexOrThrow("answer")));
            }
            answerCursor.close();
            question.setAnswers(answers);
            list.add(question);
        }
        questionCursor.close();
        return list;
    }
}
