package com.example.android.abndp8.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.abndp8.data.KitInventoryContract.KitInventoryEntry;

public class KitInventoryDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kit_inventory.db";

    public KitInventoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + KitInventoryEntry.TABLE_NAME + " ("
                + KitInventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KitInventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + KitInventoryEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + KitInventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, "
                + KitInventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + KitInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
