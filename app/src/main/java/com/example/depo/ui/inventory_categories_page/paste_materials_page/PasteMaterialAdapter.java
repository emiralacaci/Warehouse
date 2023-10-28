package com.example.depo.ui.inventory_categories_page.paste_materials_page;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depo.databinding.AdapterPasteMaterialBinding;

public class PasteMaterialAdapter extends RecyclerView.Adapter<PasteMaterialAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterPasteMaterialBinding binding = AdapterPasteMaterialBinding.inflate(inflater,parent,false);
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
        private final AdapterPasteMaterialBinding binding;
        public ViewHolder(AdapterPasteMaterialBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
