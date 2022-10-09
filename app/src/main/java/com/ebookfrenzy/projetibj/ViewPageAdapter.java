package com.ebookfrenzy.projetibj;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ebookfrenzy.projetibj.mainFragment.CartFragment;
import com.ebookfrenzy.projetibj.mainFragment.HomeFragment;
import com.ebookfrenzy.projetibj.mainFragment.SearchFragment;

public class ViewPageAdapter extends FragmentStateAdapter {


    public ViewPageAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1 :
                return new SearchFragment();
            case 2:
                return new CartFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    }

