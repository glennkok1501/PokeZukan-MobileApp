package com.gmail.gk_dev_software.pokezukan.Main.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gmail.gk_dev_software.pokezukan.Main.Fragments.AbilityFragment;
import com.gmail.gk_dev_software.pokezukan.Main.Fragments.DexFragment;
import com.gmail.gk_dev_software.pokezukan.Main.Fragments.MoveFragment;

public class MainAdapter extends FragmentStateAdapter {
    public MainAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new AbilityFragment();
            case 2:
                return new MoveFragment();
//            case 3:
//                return new ItemFragment();
        }

        return new DexFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
