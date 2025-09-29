package com.example.lesson5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;


//import com.example.lesson5.databinding.ItemFoodBinding;
import com.example.lesson5.databinding.ActivityFoodMenuBinding;

import java.util.ArrayList;

public class FoodHomeActivity extends AppCompatActivity {
    private ActivityFoodMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityFoodMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.foodMenu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        ArrayList<CategoryFood> categoriesList = new ArrayList<>();
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesList);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.categoryOne.setAdapter(categoriesAdapter);
        binding.categoryOne.setLayoutManager(layoutManager);


        categoriesList.add(new CategoryFood(R.drawable.ic_hamburger, "Burger"));
        categoriesList.add(new CategoryFood(R.drawable.ic_pizza, "Pizza"));
        categoriesList.add(new CategoryFood(R.drawable.ic_chicken, "Chicken"));


        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food(R.drawable.ic_hamburger, "Burger"));
        foodList.add(new Food(R.drawable.ic_pizza, "Pizza"));
        foodList.add(new Food(R.drawable.ic_chicken, "Chicken"));





    }
}