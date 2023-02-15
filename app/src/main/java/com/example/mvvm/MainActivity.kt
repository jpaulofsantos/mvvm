package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

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

        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btnLogin) {
            val email = binding.etEmail.text.toString()
            val senha = binding.etSenha.text.toString()
            val isChecked = binding.checkBox1.isChecked
            //val btnStrChange = "NEW BTN"

            mainViewModel.doLogin(email, senha)
            mainViewModel.analyseCheckBox(isChecked)
            mainViewModel.changeBtn(email)
        }
    }

    private fun setObserver() {
        //observe atento ao welcome()/login(), em qualquer que seja a alteração
        //neste caso, a variavel está setada no init dentro da viewNodel, então será setada
        mainViewModel.welcome().observe(this, Observer {
            binding.tv1.text = it
        })

        //neste caso, o método login() verifica se o login foi executado com sucesso
        mainViewModel.login().observe(this, Observer {
            if(it) {
                Toast.makeText(applicationContext, "Login ok!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Login falhou!", Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.checkBox().observe(this, Observer {
            if (it) {
                binding.checkBox1.text = "Selecionado"
            } else {
                binding.checkBox1.text = "Desmarcado"
            }
        })

        mainViewModel.newTextWelcome.observe(this, Observer {
            binding.tv2.text = it
        })

        mainViewModel.textBtn.observe(this, Observer {
            binding.btnLoginNew.text = it
        })

        mainViewModel.btn().observe(this, Observer {
            binding.btnLoginNew.text = it
        })
    }
}