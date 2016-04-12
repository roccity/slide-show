package com.djenerson.slide_show;
//Activity 2 database code courtesy Smyth, Neil (2015-12-06). Android Studio Development Essentials:
// Android 6 Edition (p.466) applied to Activity AutoDatabase by Daniel Jenerson

import android.support.v7.app.AppCompatActivity; // Base class for activities that use the support library action bar features
import android.os.Bundle;// extends BaseBundle
import android.content.Intent;// An intent is an abstract description of an operation to be performed.
// It can be used with startActivity to launch
import android.view.View;//This class represents the basic building block for user interface components.
// A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
// View is the base class for widgets, which are used to create interactive UI components
import android.widget.TextView;// Displays text to the user and optionally allows them to edit it.
import android.widget.EditText;// EditText is a thin veneer over TextView that configures itself to be editable

//" the application will consist of an activity and a database handler class.
// The database handler will be a subclass of SQLiteOpenHelper
// and will provide an abstract layer between the underlying SQLite database and the activity class,
// with the activity calling on the database handler to interact with the database
// (adding, removing and querying database entries).
// In order to implement this interaction in a structured way, a third class will need to be implemented
// to hold the database entry data as it is passed between the activity and the handler.
// This is actually a very simple class capable of holding product ID, product name and product quantity values,
// together with getter and setter methods for accessing these values.
// Instances of this class can then be created within the activity and database handler and passed back and forth as needed.
// Essentially, this class can be thought of as representing the database model."
//Smyth, Neil (2015-12-06). Android Studio Development Essentials: Android 6 Edition (p. 466).

public class AutoDatabase extends AppCompatActivity {
    // code to identify the views in the user interface and to implement the three “onClick” target methods:
    TextView idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //code to identify the views in the user interface and to implement the three “onClick” target methods:
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_database);
        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        quantityBox = (EditText) findViewById(R.id.productQuantity);

        }
    public void newProduct (View view) {  MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int quantity = Integer.parseInt(quantityBox.getText().toString());
        Product product =
                new Product(productBox.getText().toString(), quantity);
        dbHandler.addProduct(product);
        productBox.setText("");
        quantityBox.setText("");  }
    public void lookupProduct (View view) { MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Product product = dbHandler.findProduct(productBox.getText().toString());
        if (product != null) {idView.setText(String.valueOf(product.getID()));
            quantityBox.setText(String.valueOf(product.getQuantity())); } else {  idView.setText("No Match Found");
        }
    }
    public void removeProduct (View view) { MyDBHandler dbHandler = new MyDBHandler(this, null,null, 1);
        boolean result = dbHandler.deleteProduct(productBox.getText().toString());
        if (result){  idView.setText("Record Deleted");
            productBox.setText("");
            quantityBox.setText("");
        }
        else idView.setText("No Match Found");
    }



    }

