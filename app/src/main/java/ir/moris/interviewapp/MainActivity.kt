package ir.moris.interviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import ir.moris.interviewapp.databinding.ActivityMainBinding
import ir.moris.interviewapp.db.PersonDatabase
import ir.moris.interviewapp.repository.Repository
import ir.moris.interviewapp.ui.DbViewModel
import ir.moris.interviewapp.ui.DbViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: DbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val repository = Repository(PersonDatabase.invoke(this))
        val viewModelProviderFactory = DbViewModelFactory(repository)
        viewModel = ViewModelProvider(this , viewModelProviderFactory).get(DbViewModel::class.java)

        binding.bottomNav.setupWithNavController(navController)

    }
}