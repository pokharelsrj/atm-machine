package com.example

class FakeBankingService(private val isBankingServiceFunctional: Boolean) : BankingService {

    var withdrawnAmount = 0

    override fun withdraw(amount: Int) {
        withdrawnAmount = amount
        if (!isBankingServiceFunctional) throw error("Cannot process the amount")
    }
}
