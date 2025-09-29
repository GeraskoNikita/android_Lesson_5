package com.example.lesson5;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.app.databinding.ItemCategoryBinding;
import com.example.lesson5.databinding.ItemCategoriFoodBinding;
//import com.example.lesson5.databinding.ItemFoodBinding;

import java.util.ArrayList;

//import java.util.Locale;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder> {
    private ArrayList<CategoryFood> foodArrayList = new ArrayList<>();

    public CategoriesAdapter(ArrayList<CategoryFood> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }

    @NonNull @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoriFoodBinding b = ItemCategoriFoodBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoriesHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesHolder holder, int position) {
        holder.bind(foodArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public static class CategoriesHolder extends RecyclerView.ViewHolder {
        final ItemCategoriFoodBinding binding;


    public CategoriesHolder(@NonNull ItemCategoriFoodBinding binding) {
        super(binding.getRoot());
        this.binding =binding;
    }
        private void bind (CategoryFood category){
            binding.textCategory.setText(category.getTitle());
            binding.icon.setImageResource(category.getIconRes());

        }
    }
}
