package com.example.banksystem

import com.example.banksystem.domain.Account
import com.example.banksystem.domain.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AccountUnitTest {

    private lateinit var account: Account

    @Before
    fun start() {
        account = Account(User("123-x", "Vitor"), "123-x")
    }

    @Test
    fun `when withdrawing should decrease balance by value`() {
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

}