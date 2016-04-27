package com.djenerson.slide_show;
//MyDBHandler code courtesy Smyth, Neil (2015-12-06). Android Studio Development Essentials:
// Android 6 Edition (p.466) applied to Slide-Show by Daniel Jenerson
import android.content.ContentValues;// This class is used to store a set of values that the ContentResolver can process.
import android.content.Context;// It allows access to application-specific resources and classes,
// as well as up-calls for application-level operations such as launching activities, broadcasting and receiving intents, etc.
import android.database.Cursor;// This interface provides random read-write access to the result set returned by a database query.
import android.database.sqlite.SQLiteDatabase;// SQLiteDatabase has methods to create, delete, execute SQL commands,
// and perform other common database management tasks.
import android.database.sqlite.SQLiteOpenHelper;// A helper class to manage database creation and version management

public class MyDBHandler extends SQLiteOpenHelper { private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_QUANTITY = "quantity";
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version)
    { super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE =
                "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_PRODUCTNAME + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    public void addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getProductName());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public Product findProduct(String productname)
    {String query ="Select * FROM "+ TABLE_PRODUCTS +" WHERE "+ COLUMN_PRODUCTNAME + " =\"" + productname +"\"";

            SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null); Product product = new Product();
        if (cursor.moveToFirst()) { cursor.moveToFirst();  product.setID(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();  } else { product = null;
        } db.close();
        return product; }

    public boolean deleteProduct(String productname) { boolean result = false;
        String query = "Select * FROM "+TABLE_PRODUCTS +" WHERE "+ COLUMN_PRODUCTNAME +" =\""+ productname +"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Product product = new Product();
        if (cursor.moveToFirst()) { product.setID(Integer.parseInt(cursor.getString(0)));
            product.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",new String[] { String.valueOf(product.getID()) });
            cursor.close();  result = true;  }  db.close();
        return result; }


    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);

 }

}





