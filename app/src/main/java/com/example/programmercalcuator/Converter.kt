package com.example.programmercalcuator


class Converter {
    fun decimalToBinary(decimal: Long) = decimal.toString(2)
    fun binaryToDecimal(binary: String) = binary.toLong(2)
    fun decimalToOctal(decimal: Long) = decimal.toString(8)
    fun octalToDecimal(octal: String) = octal.toLong(8)
    fun decimalToHexadecimal(decimal: Long) = decimal.toString(16)
    fun hexadecimalToDecimal(hexadecimal: String) = hexadecimal.toLong(16)
    fun octalToBinary(octal: String) = decimalToBinary(octalToDecimal(octal))
    fun octalToHexadecimal(octal: String) = decimalToHexadecimal(octalToDecimal(octal))
}