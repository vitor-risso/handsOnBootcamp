package com.example.how2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.how2.databinding.FragmentMyAccountBinding

class MyAccountFragment : Fragment(R.layout.fragment_my_account) {
    lateinit var binding: FragmentMyAccountBinding
    lateinit var mPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyAccountBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        with(binding) {

            balance.text = mPreferences.getInt("BALANCE", 0).toString()

            addName.setOnClickListener {
                if (nameInput.text.isNotEmpty()) {
                    name.text = nameInput.text.toString()
                }
            }

            deposit.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("Name_Key", name.text.toString())
                val fragment = DepositFragment()
                fragment.arguments = bundle
                requireFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            name.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    deposit.handleButtonIsEnabled()
                    cash.handleButtonIsEnabled()
                }

            }

            reset.setOnClickListener {
                resetButton(name, deposit, cash)
            }
        }
    }

    private fun resetButton(name: TextView, btn1: Button, btn2: Button) {
        name.text = ""
        btn1.handleButtonIsEnabled()
        btn2.handleButtonIsEnabled()
    }

    open fun Button.handleButtonIsEnabled() {
        this.isEnabled = !this.isEnabled
    }
}

