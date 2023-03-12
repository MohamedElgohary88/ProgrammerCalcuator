package com.example.programmercalcuator

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultButton: Button
    private lateinit var inputText: TextView
    private lateinit var binaryTextView: TextView
    private lateinit var deleteLastDigit: Button
    private lateinit var buttonNumOne: Button
    private lateinit var buttonNumTwo: Button
    private lateinit var buttonNumThree: Button
    private lateinit var buttonNumFour: Button
    private lateinit var buttonNumFive: Button
    private lateinit var buttonNumSix: Button
    private lateinit var buttonNumSeven: Button
    private lateinit var buttonNumEight: Button
    private lateinit var buttonNumNine: Button
    private lateinit var buttonCharA: Button
    private lateinit var buttonCharB: Button
    private lateinit var buttonCharC: Button
    private lateinit var buttonCharD: Button
    private lateinit var buttonCharE: Button
    private lateinit var buttonCharF: Button
    private lateinit var deleteAllText: Button
    private lateinit var decimalTextView: TextView
    private lateinit var octalTextView: TextView
    private lateinit var hexadecimalTextView: TextView
    private lateinit var spinner: Spinner
    private val convert = Converter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()

        val choices = resources.getStringArray(R.array.my_spinner_choices)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, choices)
        spinner.adapter = adapter
        var myPosition = 0

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, choices
            )
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> disableAllButtonsThatIsNotUsedInBinary()
                        1 -> enableAllButtonsToUseInHexa()
                        2 -> disableAllButtonsThatIsNotUsedInDecimal()
                        3 -> disableAllButtonsThatIsNotUsedInOctal()
                    }
                    myPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        resultButton.setOnClickListener {
            try {
                if (inputText.text.isEmpty()) {
                    throw Exception("EditText is empty")
                }
                when (myPosition) {
                    0 -> {
                        val bin = inputText.text.toString()
                        val dec = convert.binaryToDecimal(bin)
                        val hex = convert.decimalToHexadecimal(dec)
                        val oct = convert.decimalToOctal(dec)
                        setText(bin, dec, hex, oct)
                    }
                    1 -> {
                        val hex = inputText.text.toString()
                        val dec = convert.hexadecimalToDecimal(hex)
                        val oct = convert.decimalToOctal(dec)
                        val bin = convert.octalToBinary(oct)
                        setText(bin, dec, hex, oct)
                    }
                    2 -> {
                        val dec = inputText.text.toString()
                        val hex = convert.decimalToHexadecimal(dec.toLong())
                        val oct = convert.decimalToOctal(dec.toLong())
                        val bin = convert.octalToBinary(oct)
                        setText(bin, dec.toLong(), hex, oct)
                    }
                    else -> {
                        val oct = inputText.text.toString()
                        val hex = convert.octalToHexadecimal(oct)
                        val dec = convert.hexadecimalToDecimal(hex)
                        val bin = convert.octalToBinary(oct)
                        setText(bin, dec, hex, oct)
                    }
                }

            } catch (e: Exception) {
                // handle the exception here
                createToast("Please enter some text")
            }
        }
        deleteLastDigit.setOnClickListener {
            deleteLastDigit()
        }
        deleteAllText.setOnClickListener {
            deleteAllText()
        }

    }

    private fun setText(bin: String, dec: Long, hex: String, oct: String) {
        decimalTextView.text = dec.toString()
        hexadecimalTextView.text = hex
        octalTextView.text = oct
        binaryTextView.text = bin
    }

    private fun createToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun deleteAllText() {
        inputText.text = ""
    }

    private fun deleteLastDigit() {
        var result = inputText.text.toString()
        if (result.isNotEmpty()) {
            result = result.substring(0, result.length - 1)
            inputText.text = result
        }
    }


    private fun initialization() {
        spinner = findViewById(R.id.my_spinner)
        inputText = findViewById(R.id.editText)
        resultButton = findViewById(R.id.button_result)
        deleteLastDigit = findViewById(R.id.button_delete)
        deleteAllText = findViewById(R.id.button_delete_all)
        binaryTextView = findViewById(R.id.binary_text_view)
        decimalTextView = findViewById(R.id.decimal_text_view)
        octalTextView = findViewById(R.id.octal_text_view)
        hexadecimalTextView = findViewById(R.id.hexadecimal_text_view)
        buttonCharA = findViewById(R.id.button_A)
        buttonCharB = findViewById(R.id.button_B)
        buttonCharC = findViewById(R.id.button_C)
        buttonCharD = findViewById(R.id.button_D)
        buttonCharE = findViewById(R.id.button_E)
        buttonCharF = findViewById(R.id.button_F)
        buttonNumOne = findViewById(R.id.button_num_one)
        buttonNumTwo = findViewById(R.id.button_num_two)
        buttonNumThree = findViewById(R.id.button_num_three)
        buttonNumFour = findViewById(R.id.button_num_four)
        buttonNumFive = findViewById(R.id.button_num_five)
        buttonNumSix = findViewById(R.id.button_num_six)
        buttonNumSeven = findViewById(R.id.button_num_seven)
        buttonNumEight = findViewById(R.id.button_num_eight)
        buttonNumNine = findViewById(R.id.button_num_nine)
    }

    fun onClickNumber(v: View) {
        val newDigit = (v as Button).text.toString()
        val oldDigit = inputText.text.toString()
        val newTextNumber = oldDigit + newDigit
        inputText.text = newTextNumber
    }

    fun disableAllButtonsThatIsNotUsedInDecimal() {
        disableCharacters()
        enableNumsFromSeventoTwo()
        enableTwoNumbers()
    }

    private fun disableCharacters(){
        buttonCharA.isEnabled = false
        buttonCharB.isEnabled = false
        buttonCharC.isEnabled = false
        buttonCharD.isEnabled = false
        buttonCharE.isEnabled = false
        buttonCharF.isEnabled = false
    }
    fun enableAllButtonsToUseInHexa() {
        enableButtonsCharacters()
        enableTwoNumbers()
        enableNumsFromSeventoTwo()
    }

    private fun enableButtonsCharacters() {
        buttonCharA.isEnabled = true
        buttonCharB.isEnabled = true
        buttonCharC.isEnabled = true
        buttonCharD.isEnabled = true
        buttonCharE.isEnabled = true
        buttonCharF.isEnabled = true
    }


    private fun enableTwoNumbers() {
        buttonNumNine.isEnabled = true
        buttonNumEight.isEnabled = true
    }

    fun disableAllButtonsThatIsNotUsedInOctal() {
        disableAllButtonsThatIsNotUsedInDecimal()
        buttonNumNine.isEnabled = false
        buttonNumEight.isEnabled = false
        enableNumsFromSeventoTwo()
    }

    private fun enableNumsFromSeventoTwo() {
        buttonNumSeven.isEnabled = true
        buttonNumSix.isEnabled = true
        buttonNumFive.isEnabled = true
        buttonNumFour.isEnabled = true
        buttonNumThree.isEnabled = true
        buttonNumTwo.isEnabled = true
    }

    fun disableAllButtonsThatIsNotUsedInBinary() {
        disableAllButtonsThatIsNotUsedInDecimal()
        disableAllButtonsThatIsNotUsedInOctal()
        buttonNumSeven.isEnabled = false
        buttonNumSix.isEnabled = false
        buttonNumFive.isEnabled = false
        buttonNumFour.isEnabled = false
        buttonNumThree.isEnabled = false
        buttonNumTwo.isEnabled = false

    }
}


