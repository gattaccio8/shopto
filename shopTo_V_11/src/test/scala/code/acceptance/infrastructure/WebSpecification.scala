package code.acceptance.infrastructure

trait WebSpecification {
  WebSpecificationSuite
}


object WebSpecificationSuite {
  messageOut
  RunApp

  def messageOut = println("I am web specification")
}

