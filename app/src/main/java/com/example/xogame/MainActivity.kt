package com.example.xogame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.xogame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val player1 = 0
    private val player2 = 1
    private var score1 = 0
    private var score2 = 0
    private var activePlayer = player1
    private lateinit var filledPositions: IntArray
    private lateinit var playerStatus: TextView
    private lateinit var player1Score: TextView
    private lateinit var player2Score: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        filledPositions = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)

        playerStatus = binding.playerStatus

        // playing Buttons
        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)

        // Player 1 & 2 scores
        player1Score = binding.playerOneScore
        player2Score = binding.playerTwoScore

        // Reset button
        binding.resetGame.setOnClickListener {
            playerStatus.text = "It's Draw"

            resetGameBoard()
        }
    }

    override fun onClick(v: View?) {
        val clickedBtn = findViewById<Button>(v!!.id)
        val tagButton = clickedBtn.tag.toString().toInt()

        if (filledPositions[tagButton] != -1)
            return

        filledPositions[tagButton] = activePlayer

        if(activePlayer == player1) {
            clickedBtn.text = "X"
            activePlayer = player2
        } else {
            clickedBtn.text = "O"
            activePlayer = player1
        }
        chickWinner()

    }

    private fun chickWinner() {
        val winningPositions = arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8), //Horizontal
                                       intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8), //vertical
                                       intArrayOf(0,4,8), intArrayOf(2,4,6)                     //Cross
        )

        for ( i in winningPositions.indices) {
            val winningPos1 = winningPositions[i][0]
            val winningPos2 = winningPositions[i][1]
            val winningPos3 = winningPositions[i][2]

            if(filledPositions[winningPos1] == filledPositions[winningPos2] && filledPositions[winningPos2] == filledPositions[winningPos3]) {
                if (filledPositions[winningPos1] != -1){
                    if (filledPositions[winningPos1] == player1) {
                        playerStatus.text = "Player 1 won the last game"
                        score1 += 1
                        player1Score.text = score1.toString()
                        resetGameBoard()
                    } else {
                        playerStatus.text = "Player 2 won the last game"
                        score2 += 1
                        player2Score.text = score2.toString()
                        resetGameBoard()
                    }
                }

            }

        }


    }

    fun resetGameBoard() {
        filledPositions = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activePlayer = player1

        binding.btn0.text = ""
        binding.btn1.text = ""
        binding.btn2.text = ""
        binding.btn3.text = ""
        binding.btn4.text = ""
        binding.btn5.text = ""
        binding.btn6.text = ""
        binding.btn7.text = ""
        binding.btn8.text = ""
    }

















}