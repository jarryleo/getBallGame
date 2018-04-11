package cn.leo.game357

import java.util.*

/**
 * Created by Leo on 2018/4/10.
 * 246 145 123 111 550 440 330 220 100
 */

class GameBrain {
    fun think(num: IntArray) {
        if (num.size != 3 || num.sum() < 1) return
        if (match(num)) return
        val random = Random()
        var r = random.nextInt(3)
        while (num[r] == 0) {
            r = random.nextInt(3)
        }
        num[r]--
    }

    private fun match(num: IntArray): Boolean {
        var flag = false
        val m = intArrayOf(246, 145, 123, 111, 550, 440, 330, 220, 100)
        for (i in m) {
            val a = i / 100
            val b = i / 10 % 10
            val c = i % 10
            if (a + b + c >= num.sum()) continue
            flag = check(num, 0, 1, 2, a, b, c, 0)
            if (flag) break
        }
        return flag
    }

    /**
     * 尾递归匹配
     */
    private tailrec fun check(num: IntArray, a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int): Boolean {
        if (g > 2) return false
        if ((num[a] == d && num[b] == e) || (num[a] == e && num[b] == d)) {
            num[c] = f
            return true
        }
        if ((num[a] == d && num[c] == f) || (num[a] == f && num[c] == d)) {
            num[b] = e
            return true
        }
        if ((num[b] == e && num[c] == f) || (num[b] == f && num[c] == e)) {
            num[a] = d
            return true
        }
        return check(num, b, c, a, d, e, f, g + 1)
    }
}

