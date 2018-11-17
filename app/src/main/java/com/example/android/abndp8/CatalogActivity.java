package com.example.android.abndp8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.abndp8.data.KitInventoryContract.KitInventoryEntry;
import com.example.android.abndp8.data.KitInventoryDBHelper;

public class CatalogActivity extends AppCompatActivity {

    private KitInventoryDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        dbHelper = new KitInventoryDBHelper(this);
        displayDatabaseInfo();
    }

    private Cursor queryData() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                KitInventoryEntry._ID,
                KitInventoryEntry.COLUMN_PRODUCT_NAME,
                KitInventoryEntry.COLUMN_PRODUCT_PRICE,
                KitInventoryEntry.COLUMN_PRODUCT_QUANTITY,
                KitInventoryEntry.COLUMN_SUPPLIER_NAME,
                KitInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
        };


        Cursor cursor;
        cursor = db.query(KitInventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

        private void displayDatabaseInfo() {

        Cursor cursor = queryData();

        TextView displayView = findViewById(R.id.text_view_books);
        displayView.setText(getString(R.string.the_kit_table_contains));
        displayView.append(" " + cursor.getCount() + " ");
        displayView.append(getString(R.string.kit) + "\n\n");

        int idColumnIndex = cursor.getColumnIndex(KitInventoryEntry._ID);
        int productNameColumnIndex = cursor.getColumnIndex(KitInventoryEntry.COLUMN_PRODUCT_NAME);
        int productPriceColumnIndex = cursor.getColumnIndex(KitInventoryEntry.COLUMN_PRODUCT_PRICE);
        int productQuantityColumnIndex = cursor.getColumnIndex(KitInventoryEntry.COLUMN_PRODUCT_QUANTITY);
        int supplierNameColumnIndex = cursor.getColumnIndex(KitInventoryEntry.COLUMN_SUPPLIER_NAME);
        int supplierContactColumnIndex = cursor.getColumnIndex(KitInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

        try {
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentProductPrice = cursor.getInt(productPriceColumnIndex);
                int currentProductQuantity = cursor.getInt(productQuantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierContact = cursor.getString(supplierContactColumnIndex);

                displayView.append("\n" + KitInventoryEntry._ID + "  : " + currentID + "\n" +
                        KitInventoryEntry.COLUMN_PRODUCT_NAME + "  : " + currentProductName + "\n" +
                        KitInventoryEntry.COLUMN_PRODUCT_PRICE + "  : " + currentProductPrice + "\n" +
                        KitInventoryEntry.COLUMN_PRODUCT_QUANTITY + "  : " + currentProductQuantity + "\n" +
                        KitInventoryEntry.COLUMN_SUPPLIER_NAME + "  : " + currentSupplierName + "\n" +
                        KitInventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "  : " + currentSupplierContact + "\n");
            }
        } finally {

            cursor.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
