package com.example.depo.ui.detail_of_material_pages.syrup_material_detail_page;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentSyrupMaterialDetailBinding;
import com.example.depo.model.Material;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.cream_materials_page.CreamMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.syrup_materials_page.SyrupMaterialsFragment;
import com.example.depo.util.FragmentHelper;
import com.example.depo.util.MaterialUpdateDialog;

import java.util.Calendar;

public class SyrupMaterialDetailFragment extends Fragment {

    private FragmentSyrupMaterialDetailBinding binding;
    private Material syrupMaterial;

    private MaterialUpdateDialog materialUpdateDialog;

    public SyrupMaterialDetailFragment(Material syrupMaterial) {
        this.syrupMaterial = syrupMaterial;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSyrupMaterialDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.syrup_material_secondary);

        materialUpdateDialog = new MaterialUpdateDialog();
        materialUpdateDialog.setColor(R.color.syrup_material_secondary,R.color.syrup_material_primary,R.color.syrup_material_primary);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(syrupMaterial != null){
            binding.materialName.setText(syrupMaterial.getMaterialName());
            binding.explanation.setText(syrupMaterial.getExplanation());
            binding.numberOfPieces.setText(syrupMaterial.getNumberOfPieces());
            binding.specificCode.setText(syrupMaterial.getSpecificCode());
            binding.expirationDate.setText(syrupMaterial.getExpirationDate());
        }

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSyrupMaterialsFragmentPage();
            }
        });

        binding.saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        binding.updateMaterialNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialNameButton();
            }
        });

        binding.updateExplanationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialExplanationButton();
            }
        });
        binding.updateExpirationDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialExpirationDateButton();
            }
        });
        binding.updatePieceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialNumberOfPiecesButton();
            }
        });
        binding.updateSpecificCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialSpecificCodeButton();
            }
        });
    }

    public void updateData(){

    }


    public void updateMaterialNameButton(){
        materialUpdateDialog.setListener(listenerForMaterialName);
        materialUpdateDialog.show(getActivity().getSupportFragmentManager(), "cream dialog");
    }

    private MaterialUpdateDialog.CreamDialogListener listenerForMaterialName = new MaterialUpdateDialog.CreamDialogListener() {
        @Override
        public void applyTexts(String updatedData) {
            binding.materialName.setText(updatedData);
        }
    };

    public void updateMaterialExplanationButton(){
        materialUpdateDialog.setListener(listenerForExplanation);
        materialUpdateDialog.show(getActivity().getSupportFragmentManager(), "cream dialog");
    }

    private MaterialUpdateDialog.CreamDialogListener listenerForExplanation = new MaterialUpdateDialog.CreamDialogListener() {
        @Override
        public void applyTexts(String updatedData) {
            binding.explanation.setText(updatedData);
        }
    };

    public void updateMaterialNumberOfPiecesButton(){
        materialUpdateDialog.setListener(listenerForNumberOfPieces);
        materialUpdateDialog.show(getActivity().getSupportFragmentManager(), "cream dialog");
    }

    private MaterialUpdateDialog.CreamDialogListener listenerForNumberOfPieces = new MaterialUpdateDialog.CreamDialogListener() {
        @Override
        public void applyTexts(String updatedData) {
            binding.numberOfPieces.setText(updatedData);
        }
    };
    public void updateMaterialSpecificCodeButton(){
        materialUpdateDialog.setListener(listenerForSpecificCode);
        materialUpdateDialog.show(getActivity().getSupportFragmentManager(), "cream dialog");
    }

    private MaterialUpdateDialog.CreamDialogListener listenerForSpecificCode = new MaterialUpdateDialog.CreamDialogListener() {
        @Override
        public void applyTexts(String updatedData) {
            binding.specificCode.setText(updatedData);
        }
    };
    public void updateMaterialExpirationDateButton(){
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String month = String.valueOf(i1+1);
                String day = String.valueOf(i2);
                if(i1+1<10){
                    month = "0"+month;
                }
                if(i2<10){
                    day = "0"+day;
                }
                binding.expirationDate.setText(day+"."+month+"."+i);
            }
        },year,month,day);
        dialog.show();
    }

    public void goToSyrupMaterialsFragmentPage(){
        SyrupMaterialsFragment fragment = new SyrupMaterialsFragment();
        FragmentHelper helper = new FragmentHelper(getActivity());
        helper.changeFragment(R.id.body_container,fragment,"SyrupMaterialsFragment");
    }

}
