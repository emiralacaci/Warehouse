package com.example.depo.ui.inventory_categories_page.syrup_materials_page;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.depo.R;
import com.example.depo.databinding.FragmentSyrupMaterialsBinding;
import com.example.depo.model.PasteMaterial;
import com.example.depo.model.SyrupMaterial;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.add_new_material.AddNewCreamMaterialFragment;
import com.example.depo.ui.add_new_material.AddNewSyrupMaterialFragment;
import com.example.depo.ui.inventory_categories_page.InventoryCategoryPage;
import com.example.depo.ui.inventory_categories_page.paste_materials_page.PasteMaterialAdapter;
import com.example.depo.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class SyrupMaterialsFragment extends Fragment {

    private FragmentSyrupMaterialsBinding binding;
    private FragmentHelper helper;
    private List<SyrupMaterial> syrupMaterialList;
    private SyrupMaterialAdapter syrupMaterialAdapter;
    private SyrupMaterialsViewModel syrupMaterialsViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSyrupMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        ((MainActivity) getActivity()).updateStatusBarColor(R.color.syrup_material_secondary);
        helper = new FragmentHelper(getActivity());

        syrupMaterialsViewModel = new ViewModelProvider(this).get(SyrupMaterialsViewModel.class);
        syrupMaterialList = new ArrayList<>();
        syrupMaterialAdapter = new SyrupMaterialAdapter(syrupMaterialList,getActivity());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.syrupMaterialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.syrupMaterialRecyclerView.setAdapter(syrupMaterialAdapter);

        showLoadingScreen();
        getMaterialsData();

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
    private void getMaterialsData(){
        syrupMaterialsViewModel.getData();
        syrupMaterialsViewModel.getSyrupMaterials().observe(getViewLifecycleOwner(), new Observer<List<SyrupMaterial>>() {
            @Override
            public void onChanged(List<SyrupMaterial> syrupMaterials) {
                hideLoadingScreen();
                for(SyrupMaterial s : syrupMaterials){
                    syrupMaterialList.add(s);
                    syrupMaterialAdapter.notifyDataSetChanged();
                    System.out.println(syrupMaterialList.size());

                }
            }
        });

    }

    private void showLoadingScreen() {
        binding.loadingBar.setVisibility(View.VISIBLE);
        binding.loadingTextView.setVisibility(View.VISIBLE);
        binding.syrupMaterialRecyclerView.setVisibility(View.GONE);

        binding.loadingBar.setAlpha(1.0f);
        binding.loadingTextView.setAlpha(1.0f);
        binding.syrupMaterialRecyclerView.setAlpha(0.0f);
    }

    private void hideLoadingScreen() {
        binding.syrupMaterialRecyclerView.setVisibility(View.VISIBLE);

        binding.syrupMaterialRecyclerView.animate().alpha(1.0f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {

            }
        });

        binding.loadingBar.animate().alpha(0.0f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                binding.loadingBar.setVisibility(View.GONE);
            }
        });

        binding.loadingTextView.animate().alpha(0.0f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                binding.loadingTextView.setVisibility(View.GONE);
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
