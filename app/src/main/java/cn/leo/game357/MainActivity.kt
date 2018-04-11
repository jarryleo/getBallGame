package cn.leo.game357

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import cn.leo.localnet.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ColorCircle.OnColorClickListener, View.OnClickListener {
    private var playerGet = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "取球游戏"
        initView()
        btnSwitch.setOnClickListener(this)
    }

    private fun initView() {
        playerGet = -1
        ball1.removeAllViews()
        ball2.removeAllViews()
        ball3.removeAllViews()
        for (i in 3..7 step 2) {
            for (j in 1..i) {
                when (i) {
                    3 -> {
                        val colorCircle = ColorCircle(this)
                        colorCircle.setColor(Color.RED)
                        colorCircle.setOnColorClickListener(this)
                        ball1.addView(colorCircle)
                    }
                    5 -> {
                        val colorCircle = ColorCircle(this)
                        colorCircle.setColor(Color.GREEN)
                        colorCircle.setOnColorClickListener(this)
                        ball2.addView(colorCircle)
                    }
                    7 -> {
                        val colorCircle = ColorCircle(this)
                        colorCircle.setColor(Color.BLUE)
                        colorCircle.setOnColorClickListener(this)
                        ball3.addView(colorCircle)
                    }
                }
            }
        }
    }

    override fun onColorClick(view: View) {
        val parent = view.parent as LinearLayout
        when (parent) {
            ball1 -> {
                if (playerGet <= 0) {
                    playerGet = 1
                }
                checkLine(parent, 1)
            }
            ball2 -> {
                if (playerGet <= 0) {
                    playerGet = 2
                }
                checkLine(parent, 2)
            }
            ball3 -> {
                if (playerGet <= 0) {
                    playerGet = 3
                }
                checkLine(parent, 3)
            }
        }
        //判断结果
        checkWin(true)
    }

    private fun checkLine(parent: LinearLayout, index: Int) {
        if (playerGet == index) {
            parent.removeViewAt(0)
        } else {
            toast("每次只能取同一行的球")
        }
    }

    override fun onClick(v: View?) {
        if (playerGet == 0) {
            toast("玩家还没有取球")
            return
        }

        //电脑
        var a = ball1.childCount
        var b = ball2.childCount
        var c = ball3.childCount
        val ints = intArrayOf(a, b, c)
        //电脑思考
        GameBrain().think(ints)
        playerGet = 0
        while (a > ints[0]) {
            ball1.removeViewAt(0)
            a = ball1.childCount
        }
        while (b > ints[1]) {
            ball2.removeViewAt(0)
            b = ball2.childCount
        }
        while (c > ints[2]) {
            ball3.removeViewAt(0)
            c = ball3.childCount
        }
        //判断结果
        checkWin(false)
    }

    private fun checkWin(isPlayer: Boolean) {
        var a = ball1.childCount
        var b = ball2.childCount
        var c = ball3.childCount
        val ints = intArrayOf(a, b, c)
        //判断结果
        val sum = ints.sum()
        if (sum <= 1) {
            if (sum == 0) gameOver(!isPlayer)
            if (sum == 1) gameOver(isPlayer)
        }
    }

    private fun gameOver(win: Boolean) {
        val winNum = get("win", 0)
        var firstWin = get("firstWin", 0)
        val lose = get("lose", 0)
        val dialog = AlertDialog.Builder(this)
        if (win) {
            put("win", winNum + 1)
            if (firstWin == 0) {
                put("firstWin", lose + 1)
                firstWin = lose + 1
            }
            dialog.setTitle("你赢了")
            dialog.setMessage("首先，恭喜你赢了!\n你一共输了${lose}次,赢了${winNum + 1}次\n第一次赢是在第${firstWin}次")
        } else {
            put("lose", lose + 1)
            dialog.setTitle("你输了")
            dialog.setMessage("别气馁！大侠请重新来过!")
        }

        dialog.setPositiveButton("确定", { _, _ -> initView() })
        dialog.show()
    }

}
