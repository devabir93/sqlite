package ucas.android.sqlite.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity implements ProductListener, MenuProvider {

    private DBManager db;
    ProductsAdapterReccylerview productsAdapterReccylerview;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addMenuProvider(this);
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
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db!=null)
            db.close();
    }
    private void initAdapter(ArrayList<Product> productArrayList) {
        productsAdapterReccylerview = new ProductsAdapterReccylerview(MainActivity.class.getSimpleName(),productArrayList, this);
        binding.productsRv.setAdapter(productsAdapterReccylerview);
        binding.productsRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onAddToCart(int productId, String size, int count) {

        Cart cart = new Cart(productId,size, count);
        db.insert(cart);
    }

    @Override
    public void deleteFromCart(int productId, int pos) {

    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_cart: {
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                return true;
            }
        }
        return false;
    }
}