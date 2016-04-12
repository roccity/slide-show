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

// The data handler will be implemented by subclassing from the Android SQLiteOpenHelper class
//adding the constructor, onCreate() and onUpgrade() methods.
// Since the handler will be required to add, query and delete data on behalf of the activity component,
// corresponding methods will also need to be added to the class.

// declare constants for the database name, table name, table columns and database version and  add the constructor method

public class MyDBHandler extends SQLiteOpenHelper { private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_QUANTITY = "quantity";
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) { super(context, DATABASE_NAME, factory, DATABASE_VERSION);
                        }
    // "the onCreate() method is implemented so that the products table is created when the database is first initialized.
    // This involves constructing a SQL CREATE statement containing instructions to create a new table
    // with the appropriate columns and then passing that through to the execSQL() method of the SQLiteDatabase object
    // passed as an argument to onCreate()"
    // Smyth, Neil (2015-12-06). Android Studio Development Essentials: Android 6 Edition (p. 469).
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE =
                "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_PRODUCTNAME + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

// "The method to insert database records will be named addProduct()
// and will take as an argument an instance of our Product data model class.
// A ContentValues object will be created in the body of the method and primed with key-value pairs
// for the data columns extracted from the Product object.
// Next, a reference to the database will be obtained via a call to getWritableDatabase()
// followed by a call to the insert() method of the returned database object.
// Finally, once the insertion has been performed, the database needs to be closed:"
// Smyth, Neil (2015-12-06). Android Studio Development Essentials: Android 6 Edition (p. 470)

    public void addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getProductName());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //"The method to query the database will be named findProduct()
    // and will take as an argument a String object containing the name of the product to be located.
    // Using this string, a SQL SELECT statement will be constructed to find all matching records in the table."
    // Smyth, Neil (2015-12-06). Android Studio Development Essentials: Android 6 Edition (p. 470).

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

    // "The deletion method will be named deleteProduct()
    // nd will accept as an argument the entry to be deleted in the form of a Product object.
    // The method will use a SQL SELECT statement to search for the entry based on the product name and,
    // if located, delete it from the table. The success or otherwise of the deletion will be reflected in a Boolean return value:"
    //Smyth, Neil (2015-12-06). Android Studio Development Essentials: Android 6 Edition (p. 471).

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





