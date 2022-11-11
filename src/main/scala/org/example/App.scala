package org.example

/**
 * @author ${user.name}
 */
object App {
  def main(args : Array[String]) {
    var str = "Hello, Scala!"
    println( str.reverse)
    println( str.toLowerCase())
    println( str.replace("!", ""))
    println( str + " and goodbye python!")
  }
}
