# Property Based Testing Workshop

Useful links:

- [ScalaCheck web site](https://www.scalacheck.org)
- [Source code for Gen](https://github.com/typelevel/scalacheck/blob/master/src/main/scala/org/scalacheck/Gen.scala)
- [Noel Markham's in-depth guide to ScalaCheck](https://noelmarkham.github.io/practical-scalacheck/index.html)
- [Examples of Gen.sized](https://stackoverflow.com/questions/42834516/understanding-scalachecks-generation-size)

### Gens

Gen.choose(5, 10)
Gen.oneOf(1, 3, 5)
Gen.alphaLowerStr
Gen.listOfN(10, Gen.oneOf(Gen.alphaNumChar, Gen.choose(1, 10)))

- numbers from 1 to 5 and 100 to 105
- instances of a case class: case class Coord(x: Int, y: Int) 
- strings of lowercase alpha characters of length 5 to 10