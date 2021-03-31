package com.example.banksystem.domain

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

}