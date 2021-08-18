package com.example

class FakePrinter : Printer {

    var printCount: Int = 0
    var printText: String = ""

    override fun print(text: String) {
        printCount++
        printText = text
    }
}
