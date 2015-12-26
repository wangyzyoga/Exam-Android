package com.study.exam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-12-22.
 */
public class DBService {

    private SQLiteDatabase db;

    public DBService() {
        db = SQLiteDatabase.openDatabase("/data/data/com.study.exam/databases/question.db", null, SQLiteDatabase.OPEN_READWRITE);

    }

    //按顺序获取整个题库题目
    public List<Question> getQuestions() {
        List<Question> list = new ArrayList<Question>();
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Question question = new Question();
                question.ID = cursor.getInt(cursor.getColumnIndex("ID"));
                question.question = cursor.getString(cursor.getColumnIndex("question"));
                question.answerA = cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB = cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC = cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD = cursor.getString(cursor.getColumnIndex("answerD"));
                question.answer = cursor.getInt(cursor.getColumnIndex("answer"));
                question.explaination = cursor.getString(cursor.getColumnIndex("explaination"));
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;
    }

    //随机获取题库中N道题
    public List<Question> getRandomQuestions() {
        List<Question> list = new ArrayList<Question>();
        Cursor cursor = db.rawQuery("select * from question order by random() limit 5", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Question question = new Question();
                question.ID = cursor.getInt(cursor.getColumnIndex("ID"));
                question.question = cursor.getString(cursor.getColumnIndex("question"));
                question.answerA = cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB = cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC = cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD = cursor.getString(cursor.getColumnIndex("answerD"));
                question.answer = cursor.getInt(cursor.getColumnIndex("answer"));
                question.explaination = cursor.getString(cursor.getColumnIndex("explaination"));
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;
    }
}
