package com.example.mvvmsample.presentation.ui

import com.example.mvvmsample.databinding.ActivityMainBinding
import com.example.mvvmsample.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)