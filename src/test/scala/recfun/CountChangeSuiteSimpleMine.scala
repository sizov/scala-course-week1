package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountChangeSuiteSimpleMine extends FunSuite {

  import Main.countChange

  test("recur 1") {
    assert(countChange(1, List(1, 2)) === 1)
  }

  test("recur 2") {
    assert(countChange(10, List(2, 5, 10)) === 3)
  }

  test("recur 3") {
    assert(countChange(6, List(3, 2)) === 2)
  }

  test("recur 4") {
    assert(countChange(10, List(6, 4, 2)) === 5)
  }

  test("recur 5") {
    assert(countChange(14, List(6, 4, 2)) === 8)
  }
}
