package com.example.banksystem

import com.example.banksystem.domain.Account
import com.example.banksystem.domain.User
import com.example.banksystem.features.MainActivity
<<<<<<< HEAD
import org.junit.Test

import org.junit.Assert.*
=======
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
>>>>>>> 8a1dce093084b07aaa3e251107bc0d8931e63cb5
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityUnitTest {
<<<<<<< HEAD

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

=======
    private lateinit var main: MainActivity

    @Before
    fun start() {
        main = MainActivity()
    }

    @Test
    fun `when validating cpf should return valid account`() {
        val cpf = "123"

        val account = main.validateAccount(cpf)

        assertNotNull(account)
        assertEquals(cpf, account?.user?.cpf)
    }

    @Test
    fun `when validating invalid cpf should return null value`() {
        val cpf = "111"

        val account = main.validateAccount(cpf)

        assertNull(account)
    }

    @Test
    fun `when transferring value should transfer from an account to other`() {
        val originAccount = Account(User("123", "Thiago"), 1000f, "123-X")
        val destinationAccount = Account(User("444", "Sueli"), 10f, "123-X")
        val transferValue = 500f

        main.execute(originAccount, destinationAccount, transferValue)

        assertEquals(500f, originAccount.balance)
        assertEquals(510f, destinationAccount.balance)
    }

    @Test
    fun `when transferring value bigger balance should do nothing`() {
        val originAccount = Account(User("123", "Thiago"), 1000f, "123-X")
        val destinationAccount = Account(User("444", "Sueli"), 10f, "123-X")
        val transferValue = 1200f

        main.execute(originAccount, destinationAccount, transferValue)

        assertEquals(1000f, originAccount.balance)
        assertEquals(10f, destinationAccount.balance)
>>>>>>> 8a1dce093084b07aaa3e251107bc0d8931e63cb5
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