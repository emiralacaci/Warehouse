package com.example.depo.ui.inventory_categories_page.cream_materials_page;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depo.R;
import com.example.depo.databinding.FragmentCreamMaterialsBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.add_new_material.AddNewCreamMaterialFragment;
import com.example.depo.ui.inventory_categories_page.InventoryCategoryPage;
import com.example.depo.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class CreamMaterialsFragment extends Fragment {
    private FragmentCreamMaterialsBinding binding;
    private CreamMaterialsViewModel creamMaterialsViewModel;
    private FragmentHelper helper;
    private List<CreamMaterial> creamMaterialsList;
    private CreamMaterialAdapter creamMaterialAdapter;
    private DividerItemDecoration decoration;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreamMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        ((MainActivity) getActivity()).updateStatusBarColor(R.color.cream_material_secondary);

        helper = new FragmentHelper(getActivity());
        creamMaterialsViewModel = new ViewModelProvider(this).get(CreamMaterialsViewModel.class);
        creamMaterialsList = new ArrayList<>();
        creamMaterialAdapter = new CreamMaterialAdapter(creamMaterialsList,creamMaterialsViewModel,getActivity());
        binding.creamMaterialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.creamMaterialRecyclerView.setAdapter(creamMaterialAdapter);

        decoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        Drawable d = ResourcesCompat.getDrawable(getResources(),R.drawable.recycler_view_add_space_after_last_index,null);
        decoration.setDrawable(d);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.addNewCreamMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddNewCreamMaterialFragment();
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInventoryCategoryPage();
            }
        });
        showLoadingScreen();
        getMaterialsData();


        binding.loadingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("tıklandı");
            }
        });


    }

    private void getMaterialsData(){
        creamMaterialsViewModel.getData();
        creamMaterialsViewModel.getCreamMaterials().observe(getViewLifecycleOwner(), new Observer<List<CreamMaterial>>() {
            @Override
            public void onChanged(List<CreamMaterial> creamMaterials) {
                hideLoadingScreen();
                for(CreamMaterial c : creamMaterials){
                    creamMaterialsList.add(c);
                    creamMaterialAdapter.notifyDataSetChanged();
                    System.out.println(creamMaterialsList.size());

                }
            }
        });

    }

    private void showLoadingScreen() {
        binding.loadingBar.setVisibility(View.VISIBLE);
        binding.loadingTextView.setVisibility(View.VISIBLE);
        binding.creamMaterialRecyclerView.setVisibility(View.GONE);

        binding.loadingBar.setAlpha(1.0f);
        binding.loadingTextView.setAlpha(1.0f);
        binding.creamMaterialRecyclerView.setAlpha(0.0f);
    }

    private void hideLoadingScreen() {
        binding.creamMaterialRecyclerView.setVisibility(View.VISIBLE);

        binding.creamMaterialRecyclerView.animate().alpha(1.0f).setDuration(500).withEndAction(new Runnable() {
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

    private void goToAddNewCreamMaterialFragment(){
        AddNewCreamMaterialFragment fragment = new AddNewCreamMaterialFragment(creamMaterialsViewModel);
        helper.changeFragment(R.id.body_container,fragment,"AddNewCreamMaterial");
    }

    private void goToInventoryCategoryPage(){
        InventoryCategoryPage fragment = new InventoryCategoryPage();
        helper.changeFragment(R.id.body_container,fragment,"InventoryCategoryPage");
    }


}
