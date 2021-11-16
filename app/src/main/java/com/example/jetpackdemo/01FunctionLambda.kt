package com.example.jetpackdemo

import android.os.Build

fun main() {
    println("funcationLearn:${funcationLearn(120)}")
    Person().test1()
    Person.test2()
    println("NumUtil:${NumUtil.double(11)}")
    println("double:${double(14)}")
    println("append:${append('a', 'b')}")
    println("magic:${magic()}")
    println("test3:${test3(1, 2)}")
    test4()
    val list = listOf(1, 2, 3)
    val result = list.sum {
        println("it:${it}")
    }
    println("result:${result}")

    var listString = listOf("1", "2", "3")
    var result2 = listString.toIntSum()(3)
    println("result:${result2}")
}

fun funcationLearn(days: Int): Boolean {
    return days > 10;
}

class Person() {
    /**
     * 成员方法
     */
    fun test1() {
        println("成员方法")
    }

    companion object {
        fun test2() {
            println("companion object 实现类方法")
        }
    }
}

/**
 * 整个静态类
 */
object NumUtil {
    fun double(num: Int): Int {

        return num * 2
    }
}

/**单表达式方法，当方法返回单个表达式时，可以省略花括号并且在= 符号之后指定代码体即可*/
fun double(x: Int) = x * 2

/***
 * 默认值，方法参数可以有默认值，当省略相应的参数时使用默认值，与java相比，这可以减少重载数量
 */
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {

}

/***
 * 可变数量的参数
 */
fun append(vararg str: Char): String {
    var result = StringBuffer()
    for (char in str) {
        result.append(char)
    }
    return result.toString()
}

/**
 * 局部方法
 * */
fun magic(): Int {

    fun foo(aa: Int): Int {
        return aa * aa
    }

    var bb = (0..100).random()
    return foo(bb)
}

//----------------------lambda 表达式-----------------------------------
var test = { println("无参数") }

//正常写法
fun test1(a: Int, b: Int): Int {
    return a + b
}

//正常写法-->lambda写法
var test2: (Int, Int) -> Int = { a, b -> a + b }

//正常写法-->lambda写法
var test3 = { a: Int, b: Int -> a + b }

//lambda的缺省值it
fun test4() {
    var arr = arrayOf(1, 2, 3, 4, 5)
    println("test4:${arr.filter { it < 5 }}")

    val map = mapOf("key1" to "value1", "key2" to "value2")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        map.forEach { key, value ->
            println(value)
        }
    }
    //下划线可以替代不需要使用的参数
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        map.forEach { _, value ->
            println("--" + value)
        }
    }
}

/**
 * 高阶函数-函数作为参数
 * */
fun List<Int>.sum(callback: (Int) -> Unit): Int {
    var result = 0
    for (v in this) {
        result += v
        callback(v)
    }
    return result
}

/**
 * 需求：实现一个能够对集合元素进行求和的高阶函数，并且返回一个声明为（scale:Int）->Float的函数
 * */
fun List<String>.toIntSum(): (scale: Int) -> Float {
    println("第一层函数")
    return fun(scale): Float {
        var result = 0f
        for (v in this) {
            result += v.toInt() * scale
        }
        return result
    }
}