package com.example.depo.ui.detail_of_material_pages.cream_material_detail_page;

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
import com.example.depo.databinding.FragmentCreamMaterialDetailBinding;
import com.example.depo.model.Material;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.cream_materials_page.CreamMaterialsFragment;
import com.example.depo.util.MaterialUpdateDialog;
import com.example.depo.util.FragmentHelper;

import java.util.Calendar;

public class CreamMaterialDetailFragment extends Fragment{

    private FragmentCreamMaterialDetailBinding binding;
    private Material creamMaterial;
    private MaterialUpdateDialog materialUpdateDialog;


    public CreamMaterialDetailFragment(Material creamMaterial) {
        this.creamMaterial=creamMaterial;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreamMaterialDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        //change status bar color using hexadecimal code
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.cream_material_secondary);
        //ContextCompat.getColor(requireContext(),R.color.cream_material_dark_green)
        //((MainActivity) getActivity()).updateStatusBarColor(getString(R.string.cream_material_dark_green));

        materialUpdateDialog = new MaterialUpdateDialog();
        materialUpdateDialog.setColor(R.color.cream_material_secondary,R.color.cream_material_primary,R.color.cream_material_primary);



        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(creamMaterial != null){
            binding.materialName.setText(creamMaterial.getMaterialName());
            binding.explanation.setText(creamMaterial.getExplanation());
            binding.numberOfPieces.setText(creamMaterial.getNumberOfPieces());
            binding.specificCode.setText(creamMaterial.getSpecificCode());
            binding.expirationDate.setText(creamMaterial.getExpirationDate());
        }

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreamMaterialsFragmentPage();
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



    public void goToCreamMaterialsFragmentPage(){
        CreamMaterialsFragment fragment = new CreamMaterialsFragment();
        FragmentHelper helper = new FragmentHelper(getActivity());
        helper.changeFragment(R.id.body_container,fragment,"CreamMaterialsFragment");
    }



}
