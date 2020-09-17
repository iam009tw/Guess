package com.kami.guess

import kotlin.random.Random

class secretNumber {
    var secret : Int = Random.nextInt(1, 10)
    var count = 0

    fun reset() {
        count = 0
        secret = Random.nextInt(1, 10)
    }

    fun validate(number : Int) : Int {
        count++
        return number - secret;
    }

}
