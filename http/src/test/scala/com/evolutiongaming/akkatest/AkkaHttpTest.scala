package com.evolutiongaming.akkatest

import org.scalatest.Assertion
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success, Try}

class AkkaHttpTest extends AsyncFunSuite with Matchers {
  import AkkaHttpSuite._

  test("akka-http modules are not of same version") {
    `akka-http modules are of same version`
      .transform((a: Try[Assertion]) => Success(a))
      .map {
        case Failure(a: IllegalStateException) if a.getMessage contains "akka-http-testkit" =>
          succeed
        case Failure(unexpectedException) =>
          fail("unexpected exception", unexpectedException)
        case Success(_) =>
          fail("unexpected success")
      }
  }
}
