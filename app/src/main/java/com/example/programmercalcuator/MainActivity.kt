package com.example.programmercalculator


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.programmercalcuator.Operation
import com.example.programmercalcuator.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private var lastNumber: Int = 0
    var currentOperation: Operation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hex_radio_button.setOnClickListener {
            enableTwoNumbers()
            enableButtonsCharacters()
            enableNumsFromSeventoTwo()
        }
        dec_radio_button.setOnClickListener {
            disableAllButtonsThatIsNotUsedInDecimal()
        }
        oct_radio_button.setOnClickListener {
            disableAllButtonsThatIsNotUsedInOctal()
        }
        bin_radio_button.setOnClickListener {
            disableAllButtonsThatIsNotUsedInBinary()
            if (text_input.text.isNotEmpty()){
                convertBinary(text_input.text.toString())
            }else{
                Toast.makeText(this,"enter Number ",Toast.LENGTH_SHORT).show()
            }
        }

        /*       binary.setOnClickListener {
               val binary =  binary.text.toString()
               convertBinary(binary)
               }

               dec.setOnClickListener {
                   val decimal = dec.text.toString().toInt()
                   convertDecimal(decimal)
               }

               hex.setOnClickListener {
                   val hexadecimal = hex.text.toString()
                   convertHex(hexadecimal)
               }

               oct.setOnClickListener {
                   val octal = oct.text.toString()
                   convertOctal(octal)
               }*/

        button_plus.setOnClickListener {
            prepareOperation(Operation.Plus)
        }
        button_minus.setOnClickListener {
            prepareOperation(Operation.Minus)
        }
        button_div.setOnClickListener {
            prepareOperation(Operation.Div)
        }
        button_multi.setOnClickListener {
            prepareOperation(Operation.Multi)
        }
        button_result.setOnClickListener {
        //    val result = doCurrentOperation()
          //  text_input.text = result.toString()
        }

        button_delete.setOnClickListener {
            clearLastDigit()
        }
        button_delete_all.setOnClickListener {
            clearAllText()
        }
    }

    private fun convertBinary(binary: String) {
        val decimal = Integer.parseInt(binary, 2)
        val hexadecimal = Integer.toHexString(decimal).uppercase(Locale.getDefault())
        val octal = Integer.toOctalString(decimal)
        hex_text.text = hexadecimal
        octal_text.text = octal.toString()
        dec_text.text = decimal.toString()
        binary_text.text = binary
    }

    private fun convertHex(hexadecimal: String) {
        val decimal = Integer.parseInt(hexadecimal, 16)
        val binary = Integer.toBinaryString(decimal)
        val octal = Integer.toOctalString(decimal)
        hex_text.text = hexadecimal
        dec_text.text = decimal.toString()
        binary_text.text = binary
        octal_text.text = octal
    }

    private fun convertDecimal(decimal: Int) {
        val binary = Integer.toBinaryString(decimal)
        val hexadecimal = Integer.toHexString(decimal).uppercase(Locale.getDefault())
        val octal = Integer.toOctalString(decimal)
        hex_text.text = hexadecimal
        dec_text.text = decimal.toString()
        binary_text.text = binary
        octal_text.text = octal
    }

    private fun convertOctal(octal: String) {
        val decimal = Integer.parseInt(octal, 8)
        val binary = Integer.toBinaryString(decimal)
        val hexadecimal = Integer.toHexString(decimal).uppercase(Locale.getDefault())
        hex_text.text = hexadecimal
        dec_text.text = decimal.toString()
        binary_text.text = binary
        octal_text.text = octal
    }


    private fun doCurrentOperation(): Int {
        val secondNumber = text_input.text.toString().toInt()
        return when (currentOperation) {
            Operation.Plus -> lastNumber + secondNumber
            Operation.Minus -> lastNumber - secondNumber
            Operation.Div -> lastNumber / secondNumber
            Operation.Multi -> lastNumber * secondNumber
            null -> TODO()
        }
    }

    private fun prepareOperation(operation: Operation) {
        lastNumber = text_input.text.toString().toInt()
        clearAllText()
        currentOperation = operation
    }

    private fun clearAllText() {
        text_input.setText("")
    }

    private fun clearLastDigit() {
        var result = text_input.text.toString()
        if (result.isNotEmpty()) {
            result = result.substring(0, result.length - 1)
            text_input.text = result
        }
    }

    fun onClickNumber(v: View) {
        val newDigit = (v as Button).text.toString()
        val oldDigit = text_input.text.toString()
        val newTextNumber = oldDigit + newDigit
        text_input.text = newTextNumber
    }

    fun disableAllButtonsThatIsNotUsedInDecimal() {
        button_A.isEnabled = false
        button_B.isEnabled = false
        button_C.isEnabled = false
        button_D.isEnabled = false
        button_E.isEnabled = false
        button_F.isEnabled = false
        enableNumsFromSeventoTwo()
        enableTwoNumbers()
    }

    fun enableButtonsCharacters() {
        button_A.isEnabled = true
        button_B.isEnabled = true
        button_C.isEnabled = true
        button_D.isEnabled = true
        button_E.isEnabled = true
        button_F.isEnabled = true
    }

    fun enableTwoNumbers() {
        button_num_nine.isEnabled = true
        button_num_eight.isEnabled = true
    }

    fun disableAllButtonsThatIsNotUsedInOctal() {
        disableAllButtonsThatIsNotUsedInDecimal()
        button_num_nine.isEnabled = false
        button_num_eight.isEnabled = false
        enableNumsFromSeventoTwo()
    }

    fun enableNumsFromSeventoTwo() {
        button_num_seven.isEnabled = true
        button_num_six.isEnabled = true
        button_num_five.isEnabled = true
        button_num_four.isEnabled = true
        button_num_three.isEnabled = true
        button_num_two.isEnabled = true
    }

    fun disableAllButtonsThatIsNotUsedInBinary() {
        disableAllButtonsThatIsNotUsedInDecimal()
        disableAllButtonsThatIsNotUsedInOctal()
        button_num_seven.isEnabled = false
        button_num_six.isEnabled = false
        button_num_five.isEnabled = false
        button_num_four.isEnabled = false
        button_num_three.isEnabled = false
        button_num_two.isEnabled = false
    }
}