package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class CountChangeSuiteSameElements extends FunSuite {

  import Main.areSimilar
  import Main.hasSimilarElement

  test("lists same 1") {
    assert(areSimilar(Vector(1, 2), Vector(1, 2)) === true)
  }

  test("lists same 2") {
    assert(areSimilar(Vector(2, 7), Vector(2, 8)) === false)
  }

  test("lists same 3") {
    assert(areSimilar(Vector(), Vector()) === true)
  }

  test("lists same 4") {
    assert(hasSimilarElement(ArrayBuffer(Vector(1, 2), Vector(7, 9)), Vector(7, 9)) === true)
  }

  test("lists same 5") {
    assert(hasSimilarElement(ArrayBuffer(Vector(1, 2), Vector(7, 9)), Vector(3, 1)) === false)
  }

  test("lists same 6") {
    assert(hasSimilarElement(ArrayBuffer(Vector(1, 2), Vector(7, 9, 6)), Vector(7, 9, 6)) === true)
  }

  test("lists same 7") {
    assert(hasSimilarElement(ArrayBuffer(Vector(1, 2), Vector(7, 9, 6)), Vector(7, 9, 6)) === true)
  }
}
