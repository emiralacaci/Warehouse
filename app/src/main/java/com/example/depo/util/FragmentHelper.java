package com.example.depo.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.depo.R;

public class FragmentHelper {
    FragmentActivity activity;

    public FragmentHelper(FragmentActivity activity) {
        this.activity=activity;

    }

    public void changeFragment(int container_view, Fragment fragment, String tag){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);

        fragmentManager.popBackStack(tag,FragmentManager.POP_BACK_STACK_INCLUSIVE);

// Replace whatever is in the fragment_container_view view with this fragment
        transaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out
        ).replace(container_view, fragment, tag);

// Commit the transaction

        transaction.commit();


    }

    public void delete(){
        activity=null;
    }
}
