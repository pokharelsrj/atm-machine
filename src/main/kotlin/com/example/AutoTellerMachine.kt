package com.example

class AutoTellerMachine(private val printer: Printer, private val bankingService: BankingService) {
    fun withdraw(amount: Int) {
        try {
            bankingService.withdraw(amount)
            printer.print("Successfully Withdrawn $amount")
        } catch (exception: Exception) {
            printer.print("Error Withdrawing")
        }
    }
}
