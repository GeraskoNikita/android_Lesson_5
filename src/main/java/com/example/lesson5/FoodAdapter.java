package com.example.lesson5;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.app.databinding.ItemCategoryBinding;
import com.example.lesson5.databinding.ItemFoodBinding;
//import com.example.lesson5.databinding.ItemFoodBinding;

import java.util.ArrayList;

//import java.util.Locale;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {
    private ArrayList<CategoryFood> categoryFoodArrayList = new ArrayList<>();

    public FoodAdapter(ArrayList<CategoryFood> foodArrayList) {
        this.categoryFoodArrayList = foodArrayList;
    }

    @NonNull @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodBinding b = ItemFoodBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new FoodHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        holder.bind(categoryFoodArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryFoodArrayList.size();
    }

    public ArrayList<CategoryFood> getCategoryFoodArrayList() {
        return categoryFoodArrayList;
    }

    public void setCategoryFoodArrayList(ArrayList<CategoryFood> categoryFoodArrayList) {
        this.categoryFoodArrayList = categoryFoodArrayList;
    }

    public static class FoodHolder extends RecyclerView.ViewHolder {
        final ItemFoodBinding binding;


        public FoodHolder(@NonNull ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
        }
        private void bind (CategoryFood category){
            binding.textCategory.setText(category.getTitle());
            binding.icon.setImageResource(category.getIconRes());

        }
    }
}
