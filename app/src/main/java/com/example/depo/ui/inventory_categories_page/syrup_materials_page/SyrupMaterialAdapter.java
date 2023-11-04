package com.example.depo.ui.inventory_categories_page.syrup_materials_page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depo.R;
import com.example.depo.databinding.AdapterSyrupMaterialBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.model.SyrupMaterial;
import com.example.depo.ui.detail_of_material_pages.paste_material_detail_page.PasteMaterialDetailFragment;
import com.example.depo.ui.detail_of_material_pages.syrup_material_detail_page.SyrupMaterialDetailFragment;
import com.example.depo.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class SyrupMaterialAdapter extends RecyclerView.Adapter<SyrupMaterialAdapter.ViewHolder>{

    private List<SyrupMaterial> syrupMaterialList = new ArrayList<>();
    private final String piecesOfNumber = "Adet: ";
    private final String expirationDate = "Son Kullanma Tarihi: ";
    private FragmentActivity activity;

    public SyrupMaterialAdapter(List<SyrupMaterial> syrupMaterialList, FragmentActivity activity) {
        this.syrupMaterialList = syrupMaterialList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterSyrupMaterialBinding binding = AdapterSyrupMaterialBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index=position;
        if(getItemCount()-1 == position){
            holder.binding.bottomEnd.setVisibility(View.VISIBLE);
        }

        if(syrupMaterialList.get(position) != null){
            holder.binding.materialName.setText(syrupMaterialList.get(position).getMaterialName());
            holder.binding.numberOfPieces.setText(piecesOfNumber+syrupMaterialList.get(position).getNumberOfPieces());
            holder.binding.expirationDate.setText(expirationDate+syrupMaterialList.get(position).getExpirationDate());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SyrupMaterialDetailFragment fragment = new SyrupMaterialDetailFragment(syrupMaterialList.get(position));
                FragmentHelper helper = new FragmentHelper(activity);
                helper.changeFragment(R.id.body_container,fragment, "SyrupMaterialDetailFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return syrupMaterialList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final AdapterSyrupMaterialBinding binding;
        public ViewHolder(AdapterSyrupMaterialBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
