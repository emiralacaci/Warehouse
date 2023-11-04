package com.example.depo.ui.inventory_categories_page.tea_materials_page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depo.R;
import com.example.depo.databinding.AdapterTeaMaterialBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.model.TeaMaterial;
import com.example.depo.ui.detail_of_material_pages.syrup_material_detail_page.SyrupMaterialDetailFragment;
import com.example.depo.ui.detail_of_material_pages.tea_material_detail_page.TeaMaterialDetailFragment;
import com.example.depo.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class TeaMaterialAdapter extends RecyclerView.Adapter<TeaMaterialAdapter.ViewHolder>{

    private List<TeaMaterial> teaMaterialList = new ArrayList<>();
    private final String piecesOfNumber = "Adet: ";
    private final String expirationDate = "Son Kullanma Tarihi: ";
    private FragmentActivity activity;

    public TeaMaterialAdapter(List<TeaMaterial> teaMaterialList, FragmentActivity activity) {
        this.teaMaterialList = teaMaterialList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterTeaMaterialBinding binding = AdapterTeaMaterialBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int index=position;
        if(getItemCount()-1 == position){
            holder.binding.bottomEnd.setVisibility(View.VISIBLE);
        }

        if(teaMaterialList.get(position) != null){
            holder.binding.materialName.setText(teaMaterialList.get(position).getMaterialName());
            holder.binding.numberOfPieces.setText(piecesOfNumber+teaMaterialList.get(position).getNumberOfPieces());
            holder.binding.expirationDate.setText(expirationDate+teaMaterialList.get(position).getExpirationDate());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeaMaterialDetailFragment fragment = new TeaMaterialDetailFragment(teaMaterialList.get(position));
                FragmentHelper helper = new FragmentHelper(activity);
                helper.changeFragment(R.id.body_container,fragment, "TeaMaterialDetailFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return teaMaterialList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final AdapterTeaMaterialBinding binding;
        public ViewHolder(AdapterTeaMaterialBinding binding){
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
