package com.example.depo.ui.inventory_categories_page.paste_materials_page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depo.R;
import com.example.depo.databinding.AdapterPasteMaterialBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.model.PasteMaterial;
import com.example.depo.ui.detail_of_material_pages.cream_material_detail_page.CreamMaterialDetailFragment;
import com.example.depo.ui.detail_of_material_pages.paste_material_detail_page.PasteMaterialDetailFragment;
import com.example.depo.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class PasteMaterialAdapter extends RecyclerView.Adapter<PasteMaterialAdapter.ViewHolder> {

    private List<PasteMaterial> pasteMaterialList = new ArrayList<>();
    private final String piecesOfNumber = "Adet: ";
    private final String expirationDate = "Son Kullanma Tarihi: ";
    private FragmentActivity activity;

    public PasteMaterialAdapter(List<PasteMaterial> pasteMaterialList, FragmentActivity activity) {
        this.pasteMaterialList = pasteMaterialList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterPasteMaterialBinding binding = AdapterPasteMaterialBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index=position;
        if(getItemCount()-1 == position){
            holder.binding.bottomEnd.setVisibility(View.VISIBLE);
        }

        if(pasteMaterialList.get(position) != null){
            holder.binding.materialName.setText(pasteMaterialList.get(position).getMaterialName());
            holder.binding.numberOfPieces.setText(piecesOfNumber+pasteMaterialList.get(position).getNumberOfPieces());
            holder.binding.expirationDate.setText(expirationDate+pasteMaterialList.get(position).getExpirationDate());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasteMaterialDetailFragment fragment = new PasteMaterialDetailFragment();
                FragmentHelper helper = new FragmentHelper(activity);
                helper.changeFragment(R.id.body_container,fragment, "PasteMaterialDetailFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return pasteMaterialList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final AdapterPasteMaterialBinding binding;
        public ViewHolder(AdapterPasteMaterialBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
