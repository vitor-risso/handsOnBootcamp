package com.example.banksystem

import com.example.banksystem.domain.Account
import com.example.banksystem.domain.User
import com.example.banksystem.features.MainActivity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityUnitTest {

    private lateinit var account: Account
    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        account = Account(User("123-x", "Vitor"), "123-x")
        mainActivity = MainActivity()
    }



    @Test
    fun isAccountExists() {
        val cpf = "123-x"

        val response = mainActivity.isAccountExists(cpf)

        assertNotNull(response)
        assertEquals(cpf, response?.user?.cpf)
    }


    @Test
    fun isAccountDontExists() {
        val cpf = "122-x"
        val response = mainActivity.isAccountExists(cpf)
        assertNull(response)
    }

    @Test
    fun `when transfering, value should transfer from an account to other`(){
        val transferAccount = Account(User("111", "Ana"), "111")

        val value = 56f

        mainActivity.execute( account, transferAccount, value)

        assertEquals(44f, account.balance)
        assertEquals(156f, transferAccount.balance)

    }

    @Test
    fun `when transfering value is bigger than balance should do nothing`(){
        val transferAccount = Account(User("111", "Ana"), "111")

        val value = 458f

        mainActivity.execute( account, transferAccount, value)


        assertEquals(100f, account.balance)
        assertEquals(100f, transferAccount.balance)

    }



}