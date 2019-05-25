package fr.epita.android.gameoflife

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.Button
import java.util.*
import kotlin.math.log


class GridFragment : Fragment(){
    private val game : Game = Game(20,20);
    private var adaptater : RowAdaptater? = null;
    private var gameTimer : CountDownTimer? = null;
    private var startButton : Button? = null
    private var resumePauseButton : Button? = null
    private var resetButton : Button? = null;
    private var isGameRunning : Boolean = false;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.game_view, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val MyRecyclerView = view!!.findViewById(R.id.Rows) as RecyclerView

        adaptater = RowAdaptater(context!!, game.grid, partialCellClickListener)

        startButton = view.findViewById(R.id.start)
        startButton!!.setOnClickListener(onCLick)
        resumePauseButton = view.findViewById(R.id.stop)
        resumePauseButton!!.setOnClickListener(onCLick)
        resetButton = view.findViewById(R.id.reset)
        resetButton!!.setOnClickListener(onCLick)

        resumePauseButton!!.visibility =View.INVISIBLE

        MyRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptater
        }
    }


    val partialCellClickListener = {
            Y : Int ->
        {
                X: Int ->
            View.OnClickListener {
                game.switch(Y, X)
                adaptater!!.notifyDataSetChanged();
            }
        }
    }

    val onCLick : View.OnClickListener = View.OnClickListener {
        if (it.id == R.id.start){
            startGame()
        } else if (it.id == R.id.stop) {
           if (isGameRunning) pauseGame() else startGame()
        } else if (it.id == R.id.reset) {
            resetGame()
        }
    }

    private fun startGame() {

        if (gameTimer == null) {
            gameTimer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    game.nextTurn()
                    adaptater!!.notifyDataSetChanged();
                }

                override fun onFinish() {
                    gameTimer!!.start() //To change body of created functions use File | Settings | File Templates.
                }
            }
        }
        gameTimer!!.start();
        isGameRunning = true

        startButton!!.visibility = View.INVISIBLE
        resetButton!!.visibility = View.INVISIBLE
        resumePauseButton!!.visibility = View.VISIBLE
        resumePauseButton!!.setText(R.string.stop)
    }

    private fun pauseGame() {
        gameTimer!!.cancel();
        isGameRunning = false;
        resumePauseButton!!.setText(R.string.resume)
        resetButton!!.visibility = View.VISIBLE
    }

    private fun resetGame() {
        game.reset()
        adaptater!!.notifyDataSetChanged();
        isGameRunning = false;
        startButton!!.visibility = View.VISIBLE
        resumePauseButton!!.visibility = View.INVISIBLE
    }

}