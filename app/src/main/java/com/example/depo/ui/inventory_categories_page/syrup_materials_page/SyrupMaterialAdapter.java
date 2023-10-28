package com.example.depo.ui.inventory_categories_page.syrup_materials_page;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depo.databinding.AdapterSyrupMaterialBinding;

public class SyrupMaterialAdapter extends RecyclerView.Adapter<SyrupMaterialAdapter.ViewHolder>{


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterSyrupMaterialBinding binding = AdapterSyrupMaterialBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final AdapterSyrupMaterialBinding binding;
        public ViewHolder(AdapterSyrupMaterialBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
