package com.example.banksystem.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.banksystem.R
import com.example.banksystem.domain.Account
import com.example.banksystem.domain.User
import com.example.banksystem.extensions.toFloat
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    // Text view to display both count and color
    private lateinit var mResultTextView: TextView
    private lateinit var mAccountBalanceTextView: TextView
    private lateinit var mWithdrawValueInputText: TextInputEditText
    private lateinit var mTransferValueInputText: TextInputEditText
    private lateinit var mTransferNameInputText: TextInputEditText
    private lateinit var mCpfInputText: TextInputEditText

    private val _accounts: List<Account> = listOf(
        Account(User("123-x", "Vitor"), "123-x"),
        Account(user = User("111-x", "Ana"), "456-x")
    )

    private lateinit var _account: Account
    private var _accountBalance: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize
        mWithdrawValueInputText = findViewById(R.id.withdrawValue_inputText)
        mTransferValueInputText = findViewById(R.id.transferValue_inputText)
        mTransferNameInputText = findViewById(R.id.transferTo_inputText)
        mAccountBalanceTextView = findViewById(R.id.accountBalance_textview)
        mCpfInputText = findViewById(R.id.cpf_inputText)

        mResultTextView = findViewById(R.id.result_textview)
        mResultTextView.text = "System is idle."
        _account = Account(User("123-x", "Vitor"), "123-x")
        mAccountBalanceTextView.text = "Current balance ${_account.balance}"
    }

    /**
     * Handles the onClick for the withdraw button.
     *
     * @param view
     */
    fun withdraw(view: View) {
       try {
           val cpfAccount = mCpfInputText.text.toString()
           val account = isAccountExists(cpfAccount)

           if (account == null) {
               return
           }

           mResultTextView.text = "Withdraw amount: ${mWithdrawValueInputText.text}"

           account.cash(mWithdrawValueInputText.text.toFloat())
           mAccountBalanceTextView.text = "Current balance ${account.balance}"
       } catch (err: Exception){
           mResultTextView.text = "Error while the process"
       }


    }

    /**
     * Handles the onClick for the transfer button.
     *
     * @param view
     */
    fun transfer(view: View) {
        val cpfAccount = mCpfInputText.text.toString()
        val cpfTransfer = mTransferNameInputText.text.toString()
        val transferValue = mTransferValueInputText.text.toFloat()

        val account = isAccountExists(cpfAccount)
        val accountTransfer = isAccountExists(cpfTransfer, "Account transfer not found")

        if (account == null) {
            return
        }

        if (accountTransfer == null) {
            return
        }

        execute(account, accountTransfer, transferValue)


        mResultTextView.text =
            "Transfered amount: ${mTransferValueInputText.text} to: ${mTransferNameInputText.text}"
        mAccountBalanceTextView.text = "Current balance ${account.balance}"
    }

    fun execute(originCpf: Account, destinationAccount: Account, value: Float) {
        val success = originCpf.cash(value)
        if(success){
            destinationAccount.deposit(value)
        }
    }

    fun isAccountExists(cpf: String, message: String? = "Account not found"): Account? {

        //        if (account == null) {
//            mResultTextView.text = message
//        }

        return _accounts.firstOrNull { it.user.cpf == cpf }
    }
}