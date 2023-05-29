package com.example.lovelocaldemo.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lovelocaldemo.ui.cart.CartListFragment;
import com.example.lovelocaldemo.ui.dashboard.DashBoardFragment;
import com.example.lovelocaldemo.ui.feed.FeedFragment;
import com.example.lovelocaldemo.ui.wallet.WalletFragment;

public class CategoryAdapter extends FragmentStateAdapter {
    private static final int NUM_CATEGORIES = 4;

    // Required public constructor
    public CategoryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Depending on which page the user is in,
        // create a fragment of the corresponding category
        switch (position) {
            case 0:
                return new DashBoardFragment();
            case 1:
                return new FeedFragment();
            case 2:
                return new CartListFragment();
            default:
                return new WalletFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_CATEGORIES;
    }
}