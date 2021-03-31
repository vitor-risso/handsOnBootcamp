package com.example.how2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.how2.databinding.FragmentDepositBinding

class DepositFragment : Fragment(R.layout.fragment_deposit) {

    lateinit var binding: FragmentDepositBinding
    lateinit var userName: String
    lateinit var mPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userName = arguments?.getString("Name_Key") ?: ""

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDepositBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        binding.name.text = userName

        with(binding) {

            depositInput.doOnTextChanged{ _,_,_,_ ->
                if(depositInput.text.toString().toInt() > 0){
                    depositBtn.handleButtonIsEnabled(true)
                }
            }

            depositBtn.setOnClickListener {
                if (depositInput.text.toString().toInt() > 0) {

                    var balance = mPreferences.getInt("BALANCE", 0)

                    balance += depositInput.text.toString().toInt()

                    with(mPreferences.edit()) {
                        putInt("BALANCE", balance)
                        apply()
                    }
                    Toast.makeText(activity, "Transferência efetuada no valor de $balance ", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    fun Button.handleButtonIsEnabled(state: Boolean) {
       this.isEnabled = state
    }
}