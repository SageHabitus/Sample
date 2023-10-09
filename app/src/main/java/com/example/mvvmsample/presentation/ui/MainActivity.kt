package com.example.mvvmsample.presentation.ui

import com.example.mvvmsample.databinding.ActivityMainBinding
import com.example.mvvmsample.presentation.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate)