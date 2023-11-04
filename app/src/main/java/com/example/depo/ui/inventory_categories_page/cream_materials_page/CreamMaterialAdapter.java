package com.example.depo.ui.inventory_categories_page.cream_materials_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depo.R;
import com.example.depo.databinding.AdapterCreamMaterialBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.detail_of_material_pages.cream_material_detail_page.CreamMaterialDetailFragment;
import com.example.depo.util.FragmentHelper;
import com.google.firebase.firestore.FieldValue;

import java.util.ArrayList;
import java.util.List;

public class CreamMaterialAdapter extends RecyclerView.Adapter<CreamMaterialAdapter.ViewHolder> {
    private List<CreamMaterial> creamMaterialList = new ArrayList<>();
    private final String piecesOfNumber = "Adet: ";
    private final String expirationDate = "Son Kullanma Tarihi: ";
    private FragmentActivity activity;

    public CreamMaterialAdapter(List<CreamMaterial> creamMaterialList, FragmentActivity activity) {
        this.creamMaterialList = creamMaterialList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterCreamMaterialBinding binding = AdapterCreamMaterialBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int index=position;
        if(getItemCount()-1 == position){
            holder.binding.bottomEnd.setVisibility(View.VISIBLE);
        }

        if(creamMaterialList.get(position) != null){
            holder.binding.materialName.setText(creamMaterialList.get(position).getMaterialName());
            holder.binding.numberOfPieces.setText(piecesOfNumber+creamMaterialList.get(position).getNumberOfPieces());
            holder.binding.expirationDate.setText(expirationDate+creamMaterialList.get(position).getExpirationDate());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreamMaterialDetailFragment fragment = new CreamMaterialDetailFragment(creamMaterialList.get(position));
                FragmentHelper helper = new FragmentHelper(activity);
                helper.changeFragment(R.id.body_container,fragment, "CreamMaterialDetailFragment");
            }
        });

    }

    @Override
    public int getItemCount() {
        return creamMaterialList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final AdapterCreamMaterialBinding binding;
        public ViewHolder(AdapterCreamMaterialBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
