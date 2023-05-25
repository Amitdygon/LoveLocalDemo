package com.example.lovelocaldemo.ui.home

import androidx.lifecycle.ViewModel
import com.example.lovelocaldemo.data.respository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

}