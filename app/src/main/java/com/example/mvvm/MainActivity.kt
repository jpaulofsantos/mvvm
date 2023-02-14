package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //1 criando o binding
    private lateinit var binding: ActivityMainBinding
    //2 criando a viewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //3 setando o biding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //4 instanciando a viewModel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()
    }

    private fun setObserver() {
        //observe atento ao welcome(), em qualquer que seja a alteração
        //neste caso,
        mainViewModel.welcome().observe(this, Observer {
            binding.tv1.text = it
        })

    }
}