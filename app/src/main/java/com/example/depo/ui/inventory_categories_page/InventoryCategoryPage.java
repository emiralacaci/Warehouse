package com.example.depo.ui.inventory_categories_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.ui.MainActivity;
import com.example.depo.R;
import com.example.depo.databinding.FragmentInventoryCategoryBinding;
import com.example.depo.ui.inventory_categories_page.cream_materials_page.CreamMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.paste_materials_page.PasteMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.syrup_materials_page.SyrupMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.tea_materials_page.TeaMaterialFragment;
import com.example.depo.util.FragmentHelper;

public class InventoryCategoryPage extends Fragment {
    private FragmentInventoryCategoryBinding binding;
    private Fragment fragment;
    private MainActivity activity;
    private ViewGroup container;
    FragmentHelper helper;
    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.container=container;
        binding = FragmentInventoryCategoryBinding.inflate(inflater,container,false);
         view = binding.getRoot();



        return  view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment = null;
         helper=new FragmentHelper(getActivity());


        binding.goToTeaMaterialsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new TeaMaterialFragment();
                helper.changeFragment(R.id.body_container,fragment,"TeaMaterialFragment");
            }
        });
        binding.goToPasteMaterialsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new PasteMaterialsFragment();
                helper.changeFragment(R.id.body_container,fragment,"PasteMaterialsFragment");
            }
        });
        binding.goToCreamMaterialsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CreamMaterialsFragment();
                helper.changeFragment(R.id.body_container,fragment,"CreamMaterialsFragment");
            }
        });
        binding.goToSyrupMaterialsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new SyrupMaterialsFragment();
                helper.changeFragment(R.id.body_container,fragment,"SyrupMaterialsFragment");
            }
        });
    }



}
