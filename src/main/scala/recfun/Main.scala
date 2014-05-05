package recfun

import scala.collection.mutable.ArrayBuffer

object Main {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }


  /**
   * ======================================================
   * Exercise 1
   * ======================================================
   */
  def pascal(c: Int, r: Int): Int = {
    getPascalRow(r)(c)
  }

  def getPascalRow(index: Int): Array[Int] = {
    if (index == 0)
      Array(1)
    else {
      val currentRow: Array[Int] = Array.ofDim[Int](index + 1)
      val previousRow = getPascalRow(index - 1)
      for (i <- 0 to previousRow.length) {
        currentRow(i) = calculateRowElement(i, previousRow)
      }

      currentRow
    }
  }

  def calculateRowElement(index: Int, previousRow: Array[Int]): Int = {
    retrieveRowElementByIndex(previousRow, index - 1) + retrieveRowElementByIndex(previousRow, index)
  }

  def retrieveRowElementByIndex(buffer: Array[Int], i: Int) = {
    if (i < 0 || i >= buffer.length)
      0
    else
      buffer(i)
  }


  /**
   * ======================================================
   * Exercise 2
   * ======================================================
   */
  def balance(chars: List[Char]): Boolean = {
    val openSymbol = '('
    val closeSymbol = ')'

    def balanceIter(chars: List[Char], openedCount: Int, closedCount: Int): Boolean = {
      if (chars.isEmpty)
        closedCount == openedCount
      else if (chars.head == openSymbol)
        balanceIter(chars.tail, openedCount + 1, closedCount)
      else if (chars.head == closeSymbol) {
        if (closedCount == openedCount)
          false
        else
          balanceIter(chars.tail, openedCount, closedCount + 1)
      }
      else
        balanceIter(chars.tail, openedCount, closedCount)
    }

    balanceIter(chars, 0, 0)
  }

  /**
   * ======================================================
   * Exercise 3
   * ======================================================
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    val c = calcCombinations(money, coins, Vector[Int](), Vector[Vector[Int]]())
    //    val c = calcCombinationsTest(10, Vector[Vector[Int]]())

    println(c)

    c.length
  }

  //  def calcCombinationsTest(amountToFill: Int, allCombinations: Vector[Vector[Int]]): Vector[Vector[Int]] = {
  //
  //    if (amountToFill == 0)
  //      allCombinations
  //
  //
  //    else if (amountToFill / 5 == 0) {
  //      calcCombinationsTest(currCombination, coinsSet, allCombinations, amountToFill)
  //    }
  //
  //
  //    else if (amountToFill - coinsSet.head < 0) {
  //      overFilled(amountToFill, coinsSet, currCombination, allCombinations)
  //    }
  //
  //
  //    else if (amountToFill - coinsSet.head > 0) {
  //      notYetFilled(amountToFill, coinsSet, currCombination, allCombinations)
  //    }
  //
  //    allCombinations
  //  }

  def calcCombinations(amountToFill: Int, coinsSet: List[Int],
                       currCombination: Vector[Int], allCombinations: Vector[Vector[Int]]): Vector[Vector[Int]] = {

    println(s"$amountToFill, [$coinsSet], currCombination: $currCombination, allCombinations: $allCombinations")

    if (amountToFill == 0 || coinsSet.isEmpty) {
      println(s"<== $allCombinations")
      println()
      allCombinations
    }


    else if (amountToFill - coinsSet.head == 0) {
      filledSuccess(amountToFill, coinsSet, currCombination, allCombinations)
    }


    else if (amountToFill - coinsSet.head < 0) {
      overFilled(amountToFill, coinsSet, currCombination, allCombinations)
    }


    else {
      notYetFilled(amountToFill, coinsSet, currCombination, allCombinations)
    }

    //    allCombinations
  }





  def overFilled(amountToFill: Int, coinsSet: List[Int],
                 currCombination: Vector[Int], allCombinations: Vector[Vector[Int]]): Vector[Vector[Int]] = {
    // launching calculation to fill in (amount) with other coins (because others can fill in same amount too)
    calcCombinations(amountToFill, coinsSet.tail, currCombination, allCombinations)
  }




  def notYetFilled(amountToFill: Int, coinsSet: List[Int],
                   currCombination: Vector[Int], allCombinations: Vector[Vector[Int]]): Vector[Vector[Int]] = {
    // launching calculation to fill in (amount - head) with other coins
    calcCombinations(amountToFill - coinsSet.head, coinsSet.tail, currCombination :+ coinsSet.head, allCombinations)

    // launching calculation to fill in (amount - head) with same coins (because head can be fitted once more)
    calcCombinations(amountToFill - coinsSet.head, coinsSet, currCombination :+ coinsSet.head, allCombinations)

    // launching calculation to fill in (amount) with other coins (because others can fill in same amount too)
    calcCombinations(amountToFill, coinsSet.tail, currCombination, allCombinations)
  }




  def filledSuccess(amountToFill: Int, coinsSet: List[Int],
                    currCombination: Vector[Int], allCombinations: Vector[Vector[Int]]): Vector[Vector[Int]] = {
    val foundCombination = currCombination :+ coinsSet.head

    if (hasSimilarElement(allCombinations, foundCombination)) {
      // launching calculation to fill in (amount) with other coins (because others can fill in same amount too)
      calcCombinations(amountToFill, coinsSet.tail, currCombination, allCombinations)
    }
    else {
      println(s"  new element to result ${allCombinations :+ foundCombination}")
      println()

      // launching calculation to fill in (amount) with other coins (because others can fill in same amount too)
      calcCombinations(amountToFill, coinsSet.tail, currCombination, allCombinations :+ foundCombination)
    }
  }

  def hasSimilarElement(vecOfVec: Vector[Vector[Int]], targetVec: Vector[Int]) = {
    vecOfVec.find(areSimilar(_, targetVec)) != None
  }

  def areSimilar(a: Vector[Int], b: Vector[Int]): Boolean = {

    if (a.length != b.length)
      false
    else if (a.length == 0)
      true
    else if (a.head == b.head)
      areSimilar(a.tail, b.tail)
    else
      false
  }


  def dynamicAdd(accumulator: Vector[Int]): Vector[Int] = {
    if (accumulator.length < 3)
      dynamicAdd(accumulator :+ 666)
    else if (accumulator.length < 10)
      dynamicAdd(accumulator :+ 222)
    else
      accumulator
  }


}