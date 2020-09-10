package com.kami.guess

import kotlin.random.Random

class secretNumber {
    val secret : Int = Random.nextInt(1, 10)
    val count = 0

    fun validate(number : Int) : Int {
        return number - secret;
    }

}
