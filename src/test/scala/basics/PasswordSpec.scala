package basics

import org.scalacheck.Gen
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import basics.Password._

class PasswordSpec extends AnyWordSpec with Matchers with ScalaCheckDrivenPropertyChecks {

  // Write some property-based tests for Password.isValid()
  //
  // This method determines whether a password
  // is "valid" for storage in our legacy database:
  //
  // object Password {
  //   def isValid(pws: String): Boolean = ...
  // }
  //
  // Properties of a valid password:
  // - it can't be more than 255 characters long
  // - it can't contain any whitespace, tabs, or newline characters

  "Password" should {
    "determine that a password up to 255 chars is valid" in {
      val genx = for {
        genLength <- Gen.choose(1, 255)
        genPass <- Gen.stringOfN(genLength, Gen.alphaChar)
      } yield genPass


      forAll(genx) { (x) =>
        isValid(x) shouldBe true
      }
    }

    "determine that a password over 255 chars is invalid" in {
      val genx = Gen.stringOfN(256, Gen.alphaChar)

      forAll(genx) { (x) =>
        isValid(x) shouldBe false
      }
    }

    "determine that a password with any whitespace, tabs, or newline characters is invalid" in {
      val genx = for {
        genBadChars <- Gen.oneOf(' ', '\t', '\r', '\n')
        genLength <- Gen.choose(1, 200)
        genPass <- Gen.stringOfN(genLength, Gen.alphaChar)
      } yield s"$genPass$genBadChars"

      forAll(genx) { (x) =>
        isValid(x) shouldBe false
      }
    }
  }

  // Write some property-based tests for Password.isStrong()
  //
  // This method determines whether a password
  // is a "strong" choice for our system.
  //
  // object Password {
  //   def isStrong(pws: String): Boolean = ...
  // }
  //
  // The rules for a "strong" password are:
  // - must be at least 6 characters
  // - must contain at least one digit
  // - must contain at least one lowercase number
  // - must contain at least one uppercase number
  // - must not contain the word "password"
  // - must be valid

  val genStrongPassword: Gen[String] = for {
    genDigit <- Gen.oneOf(1,2,3)
    genPassLower <- Gen.stringOfN(20, Gen.alphaLowerChar)
    genPassHigher <- Gen.stringOfN(20, Gen.alphaUpperChar)
  } yield s"$genPassLower$genDigit$genPassHigher"

  "Password isStrong" should {
    "must be strong and valid" in {
      forAll(genStrongPassword) { (x) =>
        isValid(x) shouldBe true
        isStrong(x) shouldBe true
      }
    }

    "return false for a password under 6 chars in length" in {
      val genx = for {
        genLength <- Gen.choose(1,6)
        genPass <- Gen.stringOfN(genLength, Gen.alphaChar)
      } yield genPass

      forAll(genx) { (x) =>
        isStrong(x) shouldBe false
      }
    }

    "return false for a password with no digits" in {
      val genx = Gen.stringOfN(8, Gen.alphaChar)

      forAll(genx) { (x) =>
        isStrong(x) shouldBe false
      }
    }

    "return false for a password with no lowecase letters" in {
      val genx = Gen.stringOfN(8, Gen.alphaChar)

      forAll(genx) { (x) =>
        isStrong(x) shouldBe false
      }
    }

  }

}
