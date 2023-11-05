package com.example.depo.ui.detail_of_material_pages.tea_material_detail_page;

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
import com.example.depo.databinding.FragmentTeaMaterialDetailBinding;
import com.example.depo.model.Material;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.cream_materials_page.CreamMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.tea_materials_page.TeaMaterialFragment;
import com.example.depo.util.FragmentHelper;
import com.example.depo.util.MaterialUpdateDialog;

import java.util.Calendar;

public class TeaMaterialDetailFragment extends Fragment {

    private FragmentTeaMaterialDetailBinding binding;
    private Material teaMaterial;

    private MaterialUpdateDialog materialUpdateDialog;

    public TeaMaterialDetailFragment(Material teaMaterial) {
        this.teaMaterial = teaMaterial;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeaMaterialDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.tea_material_secondary);

        materialUpdateDialog = new MaterialUpdateDialog();
        materialUpdateDialog.setColor(R.color.tea_material_secondary,R.color.tea_material_primary,R.color.tea_material_primary);
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

    public void goToTeaMaterialsFragmentPage(){
        TeaMaterialFragment fragment = new TeaMaterialFragment();
        FragmentHelper helper = new FragmentHelper(getActivity());
        helper.changeFragment(R.id.body_container,fragment,"TeaMaterialFragment");
    }

}
