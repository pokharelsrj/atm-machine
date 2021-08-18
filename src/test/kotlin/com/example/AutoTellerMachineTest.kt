package com.example

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AutoTellerMachineTest : StringSpec({
    "should get the withdrawn amount from banking service" {
        val fakePrinter = FakePrinter()
        val fakeBankingService = FakeBankingService(true)

        AutoTellerMachine(fakePrinter, fakeBankingService).withdraw(100)

        fakeBankingService.withdrawnAmount shouldBe 100
    }

    "should print a receipt if money is withdrawn successfully" {
        val fakePrinter = FakePrinter()
        val fakeBankingService = FakeBankingService(true)

        AutoTellerMachine(fakePrinter, fakeBankingService).withdraw(100)

        fakePrinter.printCount shouldBe 1
        fakePrinter.printText shouldBe "Successfully Withdrawn 100"
    }

    "should throw exception if banking service throws an exception" {
        val fakePrinter = FakePrinter()
        val fakeBankingService = FakeBankingService(false)

        AutoTellerMachine(fakePrinter, fakeBankingService).withdraw(100)

        fakePrinter.printText shouldBe "Error Withdrawing"
    }

    "should print a receipt if money is withdrawn successfully using mock" {
        val fakePrinter = mockk<Printer>()
        val fakeBankingService = mockk<BankingService>()
        every { fakeBankingService.withdraw(100) } returns Unit
        every { fakePrinter.print("Successfully Withdrawn 100") } returns Unit

        AutoTellerMachine(fakePrinter,fakeBankingService).withdraw(100)

        verify { fakePrinter.print("Successfully Withdrawn 100") }
    }

    "should throw exception if banking service throws an exception using mock" {
        val fakePrinter = mockk<Printer>()
        val fakeBankingService = mockk<BankingService>()
        every { fakeBankingService.withdraw(100) } returns Unit

        val exception= shouldThrowAny {
            AutoTellerMachine(fakePrinter,fakeBankingService).withdraw(100)
        }

        verify { fakePrinter.print("Error Withdrawing") }
    }
})
