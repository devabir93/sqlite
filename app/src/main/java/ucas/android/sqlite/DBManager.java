package ucas.android.sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by abeer on 17,May,2023
 */
public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Student student) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, student.name);
        contentValue.put(DatabaseHelper.MAJOR, student.major);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public ArrayList<Student> fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.MAJOR};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<Student> studentArrayList = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            do{
                int id =cursor.getInt(0);
                String name = cursor.getString(1);
                String major = cursor.getString(2);
                Student student = new Student(id,name,major);
                studentArrayList.add(student);
            }while (cursor.moveToNext());
        }
        return studentArrayList;
    }

    public int update(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, student.name);
        contentValues.put(DatabaseHelper.MAJOR, student.major);
        return database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + student.id, null);
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
