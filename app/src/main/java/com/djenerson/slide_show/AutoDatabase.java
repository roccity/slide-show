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

public class AutoDatabase extends AppCompatActivity {
    TextView idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

