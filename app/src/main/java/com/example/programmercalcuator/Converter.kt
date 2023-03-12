package com.example.programmercalcuator


class Converter {
    fun decimalToBinary(decimal: Long) = decimal.toString(2)
    fun binaryToDecimal(binary: String) = binary.toLong(2)
    fun decimalToOctal(decimal: Long) = decimal.toString(8)
    fun octalToDecimal(octal: String) = octal.toLong(8)
    fun decimalToHexadecimal(decimal: Long) = decimal.toString(16)
    fun hexadecimalToDecimal(hexadecimal: String) = hexadecimal.toLong(16)
    fun binaryToOctal(binary: String) = decimalToOctal(binaryToDecimal(binary))
    fun octalToBinary(octal: String) = decimalToBinary(octalToDecimal(octal))
    fun octalToHexadecimal(octal: String) = decimalToHexadecimal(octalToDecimal(octal))
    fun hexadecimalToOctal(hexadecimal: String) = decimalToOctal(hexadecimalToDecimal(hexadecimal))
    fun hexadecimalToBinary(hexadecimal: String) = decimalToBinary(hexadecimalToDecimal(hexadecimal))
    fun binaryToHexadecimal(binary: String) = decimalToHexadecimal(binaryToDecimal(binary))
}