package com.example.depo.ui.inventory_categories_page.tea_materials_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.depo.R;
import com.example.depo.databinding.FragmentTeaMaterialsBinding;
import com.example.depo.model.PasteMaterial;
import com.example.depo.model.TeaMaterial;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.add_new_material.AddNewCreamMaterialFragment;
import com.example.depo.ui.add_new_material.AddNewTeaMaterialFragment;
import com.example.depo.ui.inventory_categories_page.InventoryCategoryPage;
import com.example.depo.ui.inventory_categories_page.paste_materials_page.PasteMaterialAdapter;
import com.example.depo.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class TeaMaterialFragment extends Fragment {
    private FragmentTeaMaterialsBinding binding;
    private FragmentHelper helper;
    private List<TeaMaterial> teaMaterialList;
    private TeaMaterialAdapter teaMaterialAdapter;
    private TeaMaterialsViewModel teaMaterialsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeaMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        ((MainActivity) getActivity()).updateStatusBarColor(R.color.tea_material_secondary);
        helper = new FragmentHelper(getActivity());

        teaMaterialsViewModel = new ViewModelProvider(this).get(TeaMaterialsViewModel.class);
        teaMaterialList = new ArrayList<>();
        teaMaterialAdapter = new TeaMaterialAdapter(teaMaterialList, getActivity());


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.teaMaterialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.teaMaterialRecyclerView.setAdapter(teaMaterialAdapter);

        showLoadingScreen();
        getMaterialsData();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInventoryCategoryPage();
            }
        });

        binding.addNewTeaMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddNewCreamMaterialFragment();
            }
        });

    }

    private void getMaterialsData(){
        teaMaterialsViewModel.getData();
        teaMaterialsViewModel.getTeaMaterials().observe(getViewLifecycleOwner(), new Observer<List<TeaMaterial>>() {
            @Override
            public void onChanged(List<TeaMaterial> teaMaterials) {
                hideLoadingScreen();
                for(TeaMaterial t : teaMaterials){
                    teaMaterialList.add(t);
                    teaMaterialAdapter.notifyDataSetChanged();
                    System.out.println(teaMaterialList.size());

                }
            }
        });

    }

    private void showLoadingScreen() {
        binding.loadingBar.setVisibility(View.VISIBLE);
        binding.loadingTextView.setVisibility(View.VISIBLE);
        binding.teaMaterialRecyclerView.setVisibility(View.GONE);

        binding.loadingBar.setAlpha(1.0f);
        binding.loadingTextView.setAlpha(1.0f);
        binding.teaMaterialRecyclerView.setAlpha(0.0f);
    }

    private void hideLoadingScreen() {
        binding.teaMaterialRecyclerView.setVisibility(View.VISIBLE);

        binding.teaMaterialRecyclerView.animate().alpha(1.0f).setDuration(500).withEndAction(new Runnable() {
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
        AddNewTeaMaterialFragment fragment = new AddNewTeaMaterialFragment(teaMaterialsViewModel);
        helper.changeFragment(R.id.body_container,fragment,"AddNewCreamMaterial");
    }
}
