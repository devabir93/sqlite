package ucas.android.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBManager(this);
        db.open();
        db.insert(new Student("Abir","IT"));
        ArrayList<Student> studentArrayList= db.fetch();
        Log.d(MainActivity.class.getSimpleName(),studentArrayList.toString());
    }
}