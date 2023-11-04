package com.example.depo.ui.inventory_categories_page.paste_materials_page;

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
import com.example.depo.databinding.FragmentPasteMaterialsBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.model.PasteMaterial;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.add_new_material.AddNewCreamMaterialFragment;
import com.example.depo.ui.add_new_material.AddNewPasteMaterialFragment;
import com.example.depo.ui.inventory_categories_page.InventoryCategoryPage;
import com.example.depo.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class PasteMaterialsFragment extends Fragment {

    private FragmentPasteMaterialsBinding binding;
    private FragmentHelper helper;
    private List<PasteMaterial> pasteMaterialsList;
    private PasteMaterialAdapter pasteMaterialAdapter;
    private PasteMaterialsViewModel pasteMaterialsViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPasteMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        ((MainActivity) getActivity()).updateStatusBarColor(R.color.paste_material_secondary);

        helper = new FragmentHelper(getActivity());
        pasteMaterialsViewModel= new ViewModelProvider(this).get(PasteMaterialsViewModel.class);
        pasteMaterialsList = new ArrayList<>();
        pasteMaterialAdapter = new PasteMaterialAdapter(pasteMaterialsList,getActivity());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.pasteMaterialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.pasteMaterialRecyclerView.setAdapter(pasteMaterialAdapter);

        showLoadingScreen();
        getMaterialsData();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInventoryCategoryPage();
            }
        });

        binding.addNewPasteMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddNewCreamMaterialFragment();
            }
        });

    }

    private void getMaterialsData(){
        pasteMaterialsViewModel.getData();
        pasteMaterialsViewModel.getPasteMaterials().observe(getViewLifecycleOwner(), new Observer<List<PasteMaterial>>() {
            @Override
            public void onChanged(List<PasteMaterial> pasteMaterials) {
                hideLoadingScreen();
                for(PasteMaterial p : pasteMaterials){
                    pasteMaterialsList.add(p);
                    pasteMaterialAdapter.notifyDataSetChanged();
                    System.out.println(pasteMaterialsList.size());

                }
            }
        });

    }

    private void showLoadingScreen() {
        binding.loadingBar.setVisibility(View.VISIBLE);
        binding.loadingTextView.setVisibility(View.VISIBLE);
        binding.pasteMaterialRecyclerView.setVisibility(View.GONE);

        binding.loadingBar.setAlpha(1.0f);
        binding.loadingTextView.setAlpha(1.0f);
        binding.pasteMaterialRecyclerView.setAlpha(0.0f);
    }

    private void hideLoadingScreen() {
        binding.pasteMaterialRecyclerView.setVisibility(View.VISIBLE);

        binding.pasteMaterialRecyclerView.animate().alpha(1.0f).setDuration(500).withEndAction(new Runnable() {
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
        AddNewPasteMaterialFragment fragment = new AddNewPasteMaterialFragment(pasteMaterialsViewModel);
        helper.changeFragment(R.id.body_container,fragment,"AddNewCreamMaterial");
    }



}
