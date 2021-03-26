package com.example.banksystem

import com.example.banksystem.domain.Account
import com.example.banksystem.domain.User
<<<<<<< HEAD
import org.junit.Assert.*
=======
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
>>>>>>> 8a1dce093084b07aaa3e251107bc0d8931e63cb5
import org.junit.Before
import org.junit.Test

class AccountUnitTest {
<<<<<<< HEAD

=======
>>>>>>> 8a1dce093084b07aaa3e251107bc0d8931e63cb5
    private lateinit var account: Account

    @Before
    fun start() {
<<<<<<< HEAD
        account = Account(User("123-x", "Vitor"), "123-x")
=======
        account = Account(User("123", "Thiago"), 1000f, "546-X")
>>>>>>> 8a1dce093084b07aaa3e251107bc0d8931e63cb5
    }

    @Test
    fun `when withdrawing should decrease balance by value`() {
<<<<<<< HEAD
        val response = account.cash(50f)

        assertTrue(response)
        assertEquals(50f, account.balance)
    }

    @Test
    fun `when withdrawind value is bigger than balance should do nothing`() {
        val response = account.cash(789555f)

        assertFalse(response)
        assertEquals(100f, account.balance)
    }

    @Test
    fun `when withdrawing value is zero  should do nothing`() {
        val response = account.cash(0f)

        assertFalse(response)
        assertEquals(100f, account.balance)
    }

    @Test
    fun `when deposition should adding balance by value`() {
        val response = account.deposit(50f)

        assertTrue(response)
        assertEquals(150f, account.balance)
    }

    @Test
    fun `when depositing is negative should do nothing`() {
        val response = account.deposit(-50f)

        assertFalse(response)
        assertEquals(100f, account.balance)
    }

    @Test
    fun `when depositing is zero should do nothing`() {
        val response = account.deposit(0f)

        assertFalse(response)
        assertEquals(100f, account.balance)
    }

=======
        account.withdraw(150f)

        assertEquals(850f, account.balance)
    }

    @Test
    fun `when depositing should increase balance by value`() {
        val success = account.deposit(200f)

        assertTrue(success)
        assertEquals(1200f, account.balance)
    }

    @Test
    fun `when withdrawing value bigger than balance should do nothing`() {
        val success = account.withdraw(1500f)

        assertFalse(success)
        assertEquals(1000f, account.balance)
    }

    @Test
    fun `when withdrawing zero value should do nothing`() {
        account.withdraw(0f)

        assertEquals(1000f, account.balance)
    }

    @Test
    fun `when depositing zero value should do nothing`() {
        account.deposit(0f)

        assertEquals(1000f, account.balance)
    }
>>>>>>> 8a1dce093084b07aaa3e251107bc0d8931e63cb5
}