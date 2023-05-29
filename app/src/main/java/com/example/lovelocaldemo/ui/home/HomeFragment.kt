package com.example.lovelocaldemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lovelocaldemo.R
import com.example.lovelocaldemo.databinding.FragmentHomeBinding
import com.example.lovelocaldemo.ui.cart.CartListFragment
import com.example.lovelocaldemo.ui.dashboard.DashBoardFragment
import com.example.lovelocaldemo.ui.feed.FeedFragment
import com.example.lovelocaldemo.ui.home.adapter.ViewPagerAdapter
import com.example.lovelocaldemo.ui.wallet.WalletFragment
import com.example.lovelocaldemo.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var root: View? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (root == null) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            root = binding.root
        }
        return root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvUserName.text = AppUtils.getLocalTimeMessage() + " Amit"
        setFragmentAdapter()
    }

    private fun setFragmentAdapter() {
        val fragmentList: ArrayList<Fragment> = ArrayList()
        fragmentList.add(DashBoardFragment())
//        fragmentList.add(FeedFragment())
//        fragmentList.add(CartListFragment())
//        fragmentList.add(WalletFragment())
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, fragmentList)
    }

    override fun onResume() {
        super.onResume()
        setListener()
    }


    private fun setListener() {
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_home -> binding.viewPager.setCurrentItem(0, true)
//                R.id.bottom_cart -> binding.viewPager.setCurrentItem(2, true)
//                R.id.bottom_wallet -> binding.viewPager.setCurrentItem(3, true)
//                R.id.bottom_feed -> binding.viewPager.setCurrentItem(1, true)
            }
            true
        }

    }

}