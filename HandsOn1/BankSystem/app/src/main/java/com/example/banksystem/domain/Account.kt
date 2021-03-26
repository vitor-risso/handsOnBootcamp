package com.example.banksystem.domain

<<<<<<< HEAD
class Account(var user: User, val code: String) {

    var balance: Float = 100f
        private set(value) {
            if (value > 0) {
                field = value
            }
        }

    fun deposit(value: Float): Boolean {
        if (value < 1) {
            return false
        }
        this.balance += value
        return true
    }

    fun cash(value: Float): Boolean {
        if (value > 0) {
            if (value <= this.balance) {
                this.balance -= value
                return true
            } else {
                return false
            }
        }
        return false
    }

=======
class Account(var user: User, balance: Float, var code: String) {
    var balance: Float = balance

    fun deposit(value: Float): Boolean {
        if (value <= 0) {
            return false
        }
        balance += value
        return true
    }

    fun withdraw(value: Float): Boolean {
        if (value > balance || value <= 0) {
            return false
        }

        balance -= value
        return true
    }
>>>>>>> 8a1dce093084b07aaa3e251107bc0d8931e63cb5
}