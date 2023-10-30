package com.example.depo.ui.add_new_material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentAddNewSyrupMaterialBinding;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.syrup_materials_page.SyrupMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.syrup_materials_page.SyrupMaterialsViewModel;
import com.example.depo.util.FragmentHelper;

public class AddNewSyrupMaterialFragment extends Fragment {
    private FragmentAddNewSyrupMaterialBinding binding;
    private FragmentHelper helper;
    private SyrupMaterialsViewModel syrupMaterialsViewModel;

    public AddNewSyrupMaterialFragment(SyrupMaterialsViewModel syrupMaterialsViewModel) {
        this.syrupMaterialsViewModel = syrupMaterialsViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNewSyrupMaterialBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        helper = new FragmentHelper(getActivity());
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.syrup_material_tertiary);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreamMaterialsFragment();
            }
        });
    }

    private void goToCreamMaterialsFragment(){
        SyrupMaterialsFragment fragment = new SyrupMaterialsFragment();
        helper.changeFragment(R.id.body_container,fragment,"CreamMaterialsFragment");
    }
}
