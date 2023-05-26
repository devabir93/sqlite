package ucas.android.sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ucas.android.sqlite.ProductListener;
import ucas.android.sqlite.ProductsAdapterReccylerview;
import ucas.android.sqlite.databinding.ActivityCartBinding;
import ucas.android.sqlite.db.DBManager;
import ucas.android.sqlite.model.Product;

//private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

       // db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});

public class CartActivity extends AppCompatActivity {

    private DBManager db;
    ProductsAdapterReccylerview productsAdapterReccylerview;
    ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DBManager(this);
        db.open();
        ArrayList<Product> productArrayList = db.fetchProducts();
        initAdapter(productArrayList);
        Log.d(MainActivity.class.getSimpleName(), productArrayList.toString());
    }

    private void initAdapter(ArrayList<Product> productArrayList) {
        productsAdapterReccylerview = new ProductsAdapterReccylerview(productArrayList, new ProductListener() {
            @Override
            public void onAddToCart(int productId, int count) {

            }

            @Override
            public void deleteFromCart(int productId, int pos) {

            }
        });
        binding.productsRv.setAdapter(productsAdapterReccylerview);
        binding.productsRv.setLayoutManager(new LinearLayoutManager(this));
    }

}