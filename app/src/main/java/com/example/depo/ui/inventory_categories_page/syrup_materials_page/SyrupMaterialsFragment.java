package com.example.depo.ui.inventory_categories_page.syrup_materials_page;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.depo.R;
import com.example.depo.databinding.FragmentSyrupMaterialsBinding;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.add_new_material.AddNewCreamMaterialFragment;
import com.example.depo.ui.add_new_material.AddNewSyrupMaterialFragment;
import com.example.depo.ui.inventory_categories_page.InventoryCategoryPage;
import com.example.depo.util.FragmentHelper;

public class SyrupMaterialsFragment extends Fragment {

    private FragmentSyrupMaterialsBinding binding;
    private FragmentHelper helper;
    private SyrupMaterialsViewModel syrupMaterialsViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSyrupMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        ((MainActivity) getActivity()).updateStatusBarColor(R.color.syrup_material_secondary);
        helper = new FragmentHelper(getActivity());

        syrupMaterialsViewModel = new ViewModelProvider(this).get(SyrupMaterialsViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInventoryCategoryPage();
            }
        });

        binding.addNewSyrupMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddNewCreamMaterialFragment();
            }
        });

    }

    private void goToInventoryCategoryPage(){
        InventoryCategoryPage fragment = new InventoryCategoryPage();
        helper.changeFragment(R.id.body_container,fragment,"InventoryCategoryPage");
    }

    private void goToAddNewCreamMaterialFragment(){
        AddNewSyrupMaterialFragment fragment = new AddNewSyrupMaterialFragment(syrupMaterialsViewModel);
        helper.changeFragment(R.id.body_container,fragment,"AddNewCreamMaterial");
    }
}
