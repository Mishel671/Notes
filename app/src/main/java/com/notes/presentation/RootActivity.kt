package com.notes.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.notes.databinding.ActivityRootBinding
import com.notes.presentation._base.FragmentNavigator
import com.notes.presentation.details.NoteDetailsFragment
import com.notes.presentation.list.NoteListFragment

class RootActivity : AppCompatActivity(), FragmentNavigator {

    private var viewBinding: ActivityRootBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityRootBinding.inflate(layoutInflater)
        this.viewBinding = viewBinding
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    viewBinding.container.id,
                    NoteListFragment.newInstance()
                )
                .commit()
        }
    }


    override fun navigateTo(
        fragment: Fragment,
        addBackStackFragment: String?
    ) {
        val viewBinding = this.viewBinding ?: return
        supportFragmentManager
            .beginTransaction()
            .replace(
                viewBinding.container.id,
                fragment
            )
            .addToBackStack(addBackStackFragment)
            .commit()
    }

    override fun onBackPressed() {
        Log.d("BackLog", "${supportFragmentManager.backStackEntryCount}")
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}