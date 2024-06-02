package function;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Product;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;
    // table categories
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_IMAGE = "image";
    // TABLE PRODUCT
    private static final String KEY_PRODUCT_ID = "id";
    private static final String PRODUCT_CATEGORY_ID = "category_id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_IMAGE = "image";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_DETAIL = "detail";
    // table account
    private static final String TABLE_ACCOUNT = "accounts";
    private static final String KEY_ACCOUNT_ID = "id";
    private static final String ACCOUNT_EMAIL = "email";
    private static final String ACCOUNT_USERNAME = "username";
    private static final String ACCOUNT_PASSWORD = "password";
    // table order
    private static final String TABLE_ORDER = "orders";
    private static final String KEY_ORDER_ID = "id";
    private static final String ORDER_ACCOUNT_ID = "account_id";
    private static final String ORDER_ISPAYMENT = "isPayment";
    private static final String ORDER_TOTAL = "total";
    private static final String ORDER_PHONE = "phone";
    private static final String ORDER_ADDRESS = "address";
    // table order item
    private static final String TABLE_ORDER_ITEM = "order_items";
    private static final String KEY_ORDERITEM_ID = "id";
    private static final String ORDERITEM_ORDER_ID = "order_id";
    private static final String ORDERITEM_PRODUCT_ID = "product_id";
    private static final String ORDERITEM_COUNT = "count";
    private static final String ORDERITEM_PRICE = "price";
    // TABLE CART ITEM
    private static final String TABLE_CART_ITEM = "cart_items";
    private static final String KEY_CART_ITEM_ID = "id";
    private static final String CART_ITEM_PRODUCT_ID= "product_id";
    private static final String CART_ITEM_COUNT = "count";
    private static final String CART_SESSION_ID = "session_id";
    // TABLE SESSION_SHOPPING
    private static final String TABLE_SESSION_SHOPPING = "session_shopping";
    private static final String KEY_SESSION_ID = "id";
    private static final String SESSION_USER_ID = "user_id";
    private static final String SESSION_TOTAL = "TOTAL";
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
                {"1", "5", "Nokia 8210 4G",
                        "https://i.imgur.com/ESv4M5w.jpg"
                        , "1590000", "Nokia 8210 4G"},
                {"2", "4", "Xiaomi Redmi Note 13 Pro+ 5G", "https://i.imgur.com/r6tXPHP.jpg", "10490000", "Xiaomi Redmi Note 13 Pro+ 5G"},
                {"3", "4", "Xiaomi 14 5G", "https://i.imgur.com/cF7yxUT.jpg", "19990000", "Xiaomi 14 5G"},
                {"4", "3", "OPPO A57 128GB", "https://i.imgur.com/kFnN6TF.jpg", "4390000", "OPPO A57 128GB"},
                {"5", "3", "OPPO Reno11 5G", "https://i.imgur.com/gTWTzCR.jpg", "10690000", "OPPO Reno11 5G"},
                {"6", "2", "Samsung Galaxy A25 5G", "https://i.imgur.com/BBKvOv2.jpg", "6490000", "Samsung Galaxy A25 5G"},
                {"7", "2", "Samsung Galaxy S24 5G", "https://i.imgur.com/KjWwrtU.jpg", "20690000", "Samsung Galaxy S24 5G"},
                {"8", "1", "Iphone 15 Pro Max", "https://i.imgur.com/6xFPwEj.jpg", "30990000", "Iphone 15 Pro Max"},
                {"9", "5", "Nokia 105 4G Pro", "https://i.imgur.com/Rwo0eQu.png", "680000", "Nokia 105 4G Pro"},
                {"10", "1", "Iphone 13", "https://i.imgur.com/XX3aDph.jpg", "14900000", "Iphone 13"}
        };

        for (String[] product : products) {
            String INSERT_PRODUCT = "INSERT INTO " + TABLE_PRODUCTS + " (" + KEY_PRODUCT_ID + ", " + PRODUCT_CATEGORY_ID + ", " + PRODUCT_NAME + ", " + PRODUCT_IMAGE + ", " + PRODUCT_PRICE + ", " + PRODUCT_DETAIL + ") VALUES (" + product[0] + ", " + product[1] + ", '" + product[2] + "', '" + product[3] + "', " + product[4] + ", '" + product[5] + "')";
            db.execSQL(INSERT_PRODUCT);
        }

        String CREATE_TABLE_ACCOUNT = "CREATE TABLE " + TABLE_ACCOUNT + "("
                + KEY_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ACCOUNT_EMAIL + " TEXT,"
                + ACCOUNT_USERNAME + " TEXT,"
                + ACCOUNT_PASSWORD + " TEXT)";
        db.execSQL(CREATE_TABLE_ACCOUNT);
        // create 1 user test password 123
        String testEmail = "test@example.com";
        String testUsername = "test";
        String testPassword = "123";

        String INSERT_ACCOUNT = "INSERT INTO " + TABLE_ACCOUNT + " (" + ACCOUNT_EMAIL + ", " + ACCOUNT_USERNAME + ", " + ACCOUNT_PASSWORD + ") VALUES ('" + testEmail + "', '" + testUsername + "', '" + testPassword + "')";
        db.execSQL(INSERT_ACCOUNT);

        String CREATE_TABLE_ORDER = "CREATE TABLE " + TABLE_ORDER + "("
                + KEY_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ORDER_ACCOUNT_ID + " INTEGER,"
                + ORDER_ISPAYMENT + " INTEGER,"
                + ORDER_TOTAL + " TEXT,"
                + ORDER_PHONE + " TEXT,"
                + ORDER_ADDRESS + " TEXT,"
                + "FOREIGN KEY(" + ORDER_ACCOUNT_ID + ") REFERENCES " + TABLE_ACCOUNT + "(" + KEY_ACCOUNT_ID + ")" + ")";
        db.execSQL(CREATE_TABLE_ORDER);

        String CREATE_TABLE_ORDERITEM = "CREATE TABLE " + TABLE_ORDER_ITEM + "("
                + KEY_ORDERITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ORDERITEM_ORDER_ID + " INTEGER,"
                + ORDERITEM_PRODUCT_ID + " INTEGER,"
                + ORDERITEM_COUNT + " INTEGER,"
                + ORDERITEM_PRICE + " TEXT,"
                + "FOREIGN KEY(" + ORDERITEM_ORDER_ID + ") REFERENCES " + TABLE_ORDER + "(" + KEY_ORDER_ID + "),"
                + "FOREIGN KEY(" + ORDERITEM_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + KEY_PRODUCT_ID + ")" + ")";
        db.execSQL(CREATE_TABLE_ORDERITEM);

        String CREATE_TABLE_SESSION_SHOPPING = "CREATE TABLE " + TABLE_SESSION_SHOPPING + "("
                + KEY_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SESSION_USER_ID + " INTEGER,"
                + SESSION_TOTAL + " TEXT,"
                + "FOREIGN KEY(" + SESSION_USER_ID + ") REFERENCES " + TABLE_ACCOUNT + "(" + KEY_ACCOUNT_ID + ")" + ")";
        db.execSQL(CREATE_TABLE_SESSION_SHOPPING);

        String CREATE_TABLE_CART_ITEM = "CREATE TABLE " + TABLE_CART_ITEM + "("
                + KEY_CART_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CART_ITEM_PRODUCT_ID + " INTEGER,"
                + CART_ITEM_COUNT + " INTEGER,"
                + CART_SESSION_ID + " INTEGER,"
                + "FOREIGN KEY(" + CART_ITEM_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + KEY_PRODUCT_ID + "),"
                + "FOREIGN KEY(" + CART_SESSION_ID + ") REFERENCES " + TABLE_SESSION_SHOPPING + "(" + KEY_SESSION_ID + ")" + ")";
        db.execSQL(CREATE_TABLE_CART_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public List<Category> getCategories() {
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT *"  + " FROM " + TABLE_CATEGORIES, null);
        if (cursor.moveToFirst()) {
            do {
                categoryList.add(new Category(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;
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
    public List<Product> getProductbyIdCategory(int idCategory) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + PRODUCT_CATEGORY_ID + " = " + idCategory, null);
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
    public List<Product> searchProduct(String name) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + PRODUCT_NAME + " LIKE '%" + name + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
    public boolean checkAccountIsExist(String username, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNT + " WHERE " + ACCOUNT_USERNAME + " = '" + username + "' OR " + ACCOUNT_EMAIL + " = '" + email + "'", null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
    public boolean createAccount(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT_ACCOUNT = "INSERT INTO " + TABLE_ACCOUNT + " (" + ACCOUNT_EMAIL + ", " + ACCOUNT_USERNAME + ", " + ACCOUNT_PASSWORD + ") VALUES ('" + email + "', '" + username + "', '" + password + "')";
        db.execSQL(INSERT_ACCOUNT);
        return true;
    }
    public boolean checkAccount(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNT + " WHERE " + ACCOUNT_USERNAME + " = '" + username + "' AND " + ACCOUNT_PASSWORD + " = '" + password + "'", null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
    public int getAccountId(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_ACCOUNT_ID + " FROM " + TABLE_ACCOUNT + " WHERE " + ACCOUNT_USERNAME + " = '" + username + "'", null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return -1;
    }
    public int getSessionID(String idUser){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_SESSION_ID + " FROM " + TABLE_SESSION_SHOPPING + " WHERE " + SESSION_USER_ID + " = '" + idUser + "'", null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        else{
            String INSERT_SESSION = "INSERT INTO " + TABLE_SESSION_SHOPPING + " (" + SESSION_USER_ID + ", " + SESSION_TOTAL + ") VALUES ('" + idUser + "', '0')";
            db.execSQL(INSERT_SESSION);
            return getSessionID(idUser);
        }
    }
    public String getName(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ACCOUNT_USERNAME + " FROM " + TABLE_ACCOUNT + " WHERE " + KEY_ACCOUNT_ID + " = '" + userID + "'", null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return "";
    }
}
