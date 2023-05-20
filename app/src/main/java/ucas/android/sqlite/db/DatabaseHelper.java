package ucas.android.sqlite.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by abeer on 17,May,2023
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_PRODUCTS = "PRODUCTS";
    public static final String TABLE_CATEGORIES = "CATEGORIES";
    public static final String TABLE_STUDENTS = "STUDENTS";

    public static final String TABLE_CART = "CART";
    public static final String TABLE_DEPARTMENT = "DEPARTMENT";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String PRODUCT_ID = "product_id";
    public static final String QUANTITY = "quanitiy";
    public static final String DEP_NAME = "dep_name";
    public static final String MAJOR = "major";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY_ID = "category_id";
    public static final String PRICE = "price";

    // Database Information
    static final String DB_NAME = "STUDENTS.DB";

    // database version
    static final int DB_VERSION = 4;

    // Creating table query
    private static final String CREATE_TABLE_STUDENTS = "create table if not exists" + TABLE_STUDENTS + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + MAJOR + " TEXT);";

    private static final String CREATE_TABLE_PRODUCTS ="create table if not exists "+ TABLE_PRODUCTS +"( "+_ID+" INTEGER PRIMARY key AUTOINCREMENT,"
            + NAME+" Text Not null,"+ DESCRIPTION+" text,"+ PRICE+" float ,"+CATEGORY_ID +" INTEGER ,foreign key ("+CATEGORY_ID+") references  TABLE_CATEGORIES ("+_ID+"));";
    private static final String CREATE_TABLE_CATEGORIES = "create table if not exists " + TABLE_CATEGORIES + "(" + _ID
            + " INTEGER PRIMARY KEY ON CONFLICT REPLACE, " + NAME + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_CART = "create table if not exists " + TABLE_CART + "(" + _ID
            + " INTEGER PRIMARY KEY ON CONFLICT REPLACE, " + PRODUCT_ID + " INTEGER ," + QUANTITY+" INTEGER,"+
            " foreign key ("+PRODUCT_ID+") references "+TABLE_PRODUCTS
            +" ("+_ID+"));";

    private static final String CREATE_TABLE_DEPARTMENTS = "create table if not exists " + TABLE_DEPARTMENT + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DEP_NAME + " TEXT NOT NULL );";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE_STUDENTS);
//        db.execSQL(CREATE_TABLE_DEPARTMENTS);
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }
}