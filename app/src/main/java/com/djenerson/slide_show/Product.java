package com.djenerson.slide_show;
//Activity 2 database code courtesy Smyth, Neil (2015-12-06). Android Studio Development Essentials:
// Android 6 Edition (p.466) applied to Activity AutoDatabase by Daniel Jenerson

//The completed class contains private data members for the internal storage of data columns
// from database entries and a set of methods to get and set those values.

public class Product {private int _id;
    private String _productname;
    private int _quantity;
    public Product() {}  public Product(int id, String productname, int quantity) { this._id = id;
        this._productname = productname;
        this._quantity = quantity;} public Product(String productname, int quantity) { this._productname = productname;
        this._quantity = quantity;} public void setID(int id) { this._id = id;
    }  public int getID() {return this._id;} public void setProductName(String productname)
    { this._productname = productname;
    } public String getProductName() { return this._productname;
    } public void setQuantity(int quantity) { this._quantity = quantity;
    } public int getQuantity() { return this._quantity;
    }
}

