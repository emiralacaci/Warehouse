package com.example.depo.ui.detail_of_material_pages.tea_material_detail_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentTeaMaterialDetailBinding;
import com.example.depo.model.Material;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.cream_materials_page.CreamMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.tea_materials_page.TeaMaterialFragment;
import com.example.depo.util.FragmentHelper;

public class TeaMaterialDetailFragment extends Fragment {

    private FragmentTeaMaterialDetailBinding binding;
    private Material teaMaterial;

    public TeaMaterialDetailFragment(Material teaMaterial) {
        this.teaMaterial = teaMaterial;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeaMaterialDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.tea_material_secondary);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(teaMaterial != null){
            binding.materialName.setText(teaMaterial.getMaterialName());
            binding.explanation.setText(teaMaterial.getExplanation());
            binding.numberOfPieces.setText(teaMaterial.getNumberOfPieces());
            binding.specificCode.setText(teaMaterial.getSpecificCode());
            binding.expirationDate.setText(teaMaterial.getExpirationDate());
        }

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTeaMaterialsFragmentPage();
            }
        });
    }

    public void goToTeaMaterialsFragmentPage(){
        TeaMaterialFragment fragment = new TeaMaterialFragment();
        FragmentHelper helper = new FragmentHelper(getActivity());
        helper.changeFragment(R.id.body_container,fragment,"TeaMaterialFragment");
    }

}
