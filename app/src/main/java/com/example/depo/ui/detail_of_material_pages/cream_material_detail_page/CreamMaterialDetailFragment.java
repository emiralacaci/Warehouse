package com.example.depo.ui.detail_of_material_pages.cream_material_detail_page;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentCreamMaterialDetailBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.InventoryCategoryPage;
import com.example.depo.ui.inventory_categories_page.cream_materials_page.CreamMaterialsFragment;
import com.example.depo.util.CreamDialog;
import com.example.depo.util.FragmentHelper;

public class CreamMaterialDetailFragment extends Fragment{

    private FragmentCreamMaterialDetailBinding binding;
    private CreamMaterial creamMaterial;


    public CreamMaterialDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreamMaterialDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        //change status bar color using hexadecimal code
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.cream_material_dark_green);
        //ContextCompat.getColor(requireContext(),R.color.cream_material_dark_green)
        //((MainActivity) getActivity()).updateStatusBarColor(getString(R.string.cream_material_dark_green));




        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreamMaterials();
            }
        });



        binding.updateMaterialNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialNameButton();
            }
        });
    }

    public void updateMaterialNameButton(){
        CreamDialog creamDialog = new CreamDialog();
        creamDialog.setListener(listener);
        creamDialog.show(getActivity().getSupportFragmentManager(), "cream dialog");
    }




    private CreamDialog.CreamDialogListener listener = new CreamDialog.CreamDialogListener() {
        @Override
        public void applyTexts(String updatedData) {
                binding.materialName.setText(updatedData);
        }
    };

    public void goToCreamMaterials(){
        CreamMaterialsFragment fragment = new CreamMaterialsFragment();
        FragmentHelper helper = new FragmentHelper(getActivity());
        helper.changeFragment(R.id.body_container,fragment,"CreamMaterialsFragment");
    }
}
