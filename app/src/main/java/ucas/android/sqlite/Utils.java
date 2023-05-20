package ucas.android.sqlite;

import java.util.ArrayList;

import ucas.android.sqlite.model.Category;
import ucas.android.sqlite.model.Product;

/**
 * Created by abeer on 20,May,2023
 */
public class Utils {

    public static ArrayList <Product> getProductList(){
        ArrayList <Product> productArrayList = new ArrayList<>();
        productArrayList.add(new Product("Dress","Nice dress",20,1));
        productArrayList.add(new Product("blouse","Nice blouse",23,1));
        productArrayList.add(new Product("iphone 14","New iphone",3000,2));
        productArrayList.add(new Product("sketcher shoes","Nice shoes",250,3));
        return productArrayList;
    }

    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        categoryArrayList.add(new Category(1,"Fancy Clothes"));
        categoryArrayList.add(new Category(2,"Electronics"));
        categoryArrayList.add(new Category(3,"Shoes"));
        return categoryArrayList;

    }
}
