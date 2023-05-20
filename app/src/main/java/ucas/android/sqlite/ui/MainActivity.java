package ucas.android.sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ucas.android.sqlite.ProductListener;
import ucas.android.sqlite.ProductsAdapterReccylerview;
import ucas.android.sqlite.R;
import ucas.android.sqlite.Utils;
import ucas.android.sqlite.databinding.ActivityMainBinding;
import ucas.android.sqlite.db.DBManager;
import ucas.android.sqlite.model.Cart;
import ucas.android.sqlite.model.Category;
import ucas.android.sqlite.model.Product;
import ucas.android.sqlite.model.Student;

public class MainActivity extends AppCompatActivity implements ProductListener {

    private DBManager db;
    ProductsAdapterReccylerview productsAdapterReccylerview;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DBManager(this);
        db.open();
        for (Category category : Utils.getCategories()) {
            db.insert(category);
        }
        for (Product product : Utils.getProductList()) {
            db.insert(product);
        }
        ArrayList<Product> productArrayList = db.fetchProducts();
        initAdapter(productArrayList);
        Log.d(MainActivity.class.getSimpleName(), productArrayList.toString());
    }

    private void initAdapter(ArrayList<Product> productArrayList) {
        productsAdapterReccylerview = new ProductsAdapterReccylerview(productArrayList, this);
        binding.productsRv.setAdapter(productsAdapterReccylerview);
        binding.productsRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onAddToCart(int productId, int count) {

        Cart cart = new Cart(productId, count);
        db.insert(cart);
    }
}