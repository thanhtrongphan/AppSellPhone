package function;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_IMAGE = "image";
    private static final String KEY_PRODUCT_ID = "id";
    private static final String PRODUCT_CATEGORY_ID = "category_id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_IMAGE = "image";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_DETAIL = "detail";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY,"
                + CATEGORY_NAME + " TEXT,"
                + CATEGORY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY,"
                + PRODUCT_CATEGORY_ID + " INTEGER,"
                + PRODUCT_NAME + " TEXT,"
                + PRODUCT_IMAGE + " TEXT,"
                + PRODUCT_PRICE + " TEXT,"
                + PRODUCT_DETAIL + " TEXT,"
                + "FOREIGN KEY(" + PRODUCT_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORIES + "(" + KEY_CATEGORY_ID + ")" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
        List<String> categories = List.of("Iphone", "Samsung", "Nokia", "Xiomi", "Oppo");
        for (int i = 0; i < categories.size(); i++) {
            String INSERT_CATEGORY = "INSERT INTO " + TABLE_CATEGORIES + " (" + KEY_CATEGORY_ID + ", " + CATEGORY_NAME + ", " + CATEGORY_IMAGE + ") VALUES (" + (i+1) + ", '" + categories.get(i) + "', 'image')";
            db.execSQL(INSERT_CATEGORY);
        }
        String[][] products = {
                {"1", "5", "Nokia 8210 4G", "1", "1590000", "Nokia 8210 4G"},
                {"2", "4", "Xiaomi Redmi Note 13 Pro+ 5G", "2", "10490000", "Xiaomi Redmi Note 13 Pro+ 5G"},
                {"3", "4", "Xiaomi 14 5G", "3", "19990000", "Xiaomi 14 5G"},
                {"4", "3", "OPPO A57 128GB", "4", "4390000", "OPPO A57 128GB"},
                {"5", "3", "OPPO Reno11 5G", "5", "10690000", "OPPO Reno11 5G"},
                {"6", "2", "Samsung Galaxy A25 5G", "6", "6490000", "Samsung Galaxy A25 5G"},
                {"7", "2", "Samsung Galaxy S24 5G", "7", "20690000", "Samsung Galaxy S24 5G"},
                {"8", "1", "Iphone 15 Pro Max", "8", "30990000", "Iphone 15 Pro Max"},
                {"9", "5", "Nokia 105 4G Pro", "9", "680000", "Nokia 105 4G Pro"},
                {"10", "1", "Iphone 13", "10", "14900000", "Iphone 13"}
        };

        for (String[] product : products) {
            String INSERT_PRODUCT = "INSERT INTO " + TABLE_PRODUCTS + " (" + KEY_PRODUCT_ID + ", " + PRODUCT_CATEGORY_ID + ", " + PRODUCT_NAME + ", " + PRODUCT_IMAGE + ", " + PRODUCT_PRICE + ", " + PRODUCT_DETAIL + ") VALUES (" + product[0] + ", " + product[1] + ", '" + product[2] + "', '" + product[3] + "', " + product[4] + ", '" + product[5] + "')";
            db.execSQL(INSERT_PRODUCT);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + CATEGORY_NAME + " FROM " + TABLE_CATEGORIES, null);
        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }
    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
}
