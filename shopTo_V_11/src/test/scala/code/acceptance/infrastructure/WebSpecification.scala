package code.acceptance.infrastructure

import java.math.BigInteger
import java.security.SecureRandom


trait WebSpecification {
  WebSpecificationSuite
}


object WebSpecificationSuite {
  messageOut
  RunApp

  def messageOut = println("I am web specification")
}

