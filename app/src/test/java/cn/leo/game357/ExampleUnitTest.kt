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
        val list = intArrayOf(1,0,0)
        GameBrain().think(list)
        println(Arrays.toString(list))
    }
}
