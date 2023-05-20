package ucas.android.sqlite.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ucas.android.sqlite.model.Cart;
import ucas.android.sqlite.model.Category;
import ucas.android.sqlite.model.Product;
import ucas.android.sqlite.model.Student;

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
        contentValue.put(DatabaseHelper.NAME, student.getName());
        contentValue.put(DatabaseHelper.MAJOR, student.getMajor());
        database.insert(DatabaseHelper.TABLE_STUDENTS, null, contentValue);
    }

    public void insert(Cart cart) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.PRODUCT_ID, cart.getProductId());
        contentValue.put(DatabaseHelper.QUANTITY, cart.getCount());
        database.insert(DatabaseHelper.TABLE_CART, null, contentValue);
    }
    public void insert(Category category) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ID, category.getId());
        contentValue.put(DatabaseHelper.NAME, category.getName());
        database.insert(DatabaseHelper.TABLE_CATEGORIES, null, contentValue);
    }

    public void insert(Product product) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, product.getName());
        contentValue.put(DatabaseHelper.PRICE, product.getPrice());
        contentValue.put(DatabaseHelper.DESCRIPTION, product.getDescription());
        contentValue.put(DatabaseHelper.CATEGORY_ID, product.getCategoryId());
        database.insert(DatabaseHelper.TABLE_PRODUCTS, null, contentValue);
    }
    public ArrayList<Student> fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.MAJOR};
        Cursor cursor = database.query(DatabaseHelper.TABLE_STUDENTS, columns, null, null, null, null, null);
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
    public ArrayList<Product> fetchProducts() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.DESCRIPTION,DatabaseHelper.PRICE,DatabaseHelper.CATEGORY_ID};
        Cursor cursor = database.query(DatabaseHelper.TABLE_PRODUCTS, columns, null, null, null, null, null);
        ArrayList<Product> productArrayList = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            do{
                int id =cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                float price = cursor.getFloat(3);
                int category_id = cursor.getInt(4);
                Cursor cursorCategory = database.
                        rawQuery("select "+DatabaseHelper.NAME+" from "+DatabaseHelper.TABLE_CATEGORIES +" where "+DatabaseHelper._ID+" = "+category_id,null);
                cursorCategory.moveToFirst();
                String categoryName =cursorCategory.getString(0);
                Product product = new Product(id,name,description,price,categoryName);
                productArrayList.add(product);
            }while (cursor.moveToNext());
        }
        return productArrayList;
    }

    public int update(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, student.getName());
       // contentValues.put(DatabaseHelper.MAJOR, student.major);
        return database.update(DatabaseHelper.TABLE_STUDENTS, contentValues, DatabaseHelper._ID + " = " + student.getId(), null);
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_STUDENTS, DatabaseHelper._ID + "=" + _id, null);
    }

}
