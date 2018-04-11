package cn.leo.game357

import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)
        val list = intArrayOf(1, 5, 1)
        GameBrain().think(list)
        println(Arrays.toString(list))
    }

    @Test
    fun test() {
        val max = 10000 //和的上限
        for (sum in 5..max) {
            for (a in 2..sum / 2) { //a + b = sum
                val b = sum - a //b 比 a 大
                for (c in a + 1..sum / 2) {
                    val d = sum - c
                    if (a * b == c * d) { //乘积相等
                        if (!((a == c && b == d) || (a == d && b == c))) {
                            println("发现乘积相等和也相等的2组数:[$a,$b][$c,$d]")
                        }
                    }
                }
            }
        }
        println("没有发现乘积相等和也相等的2组数")
    }
}
