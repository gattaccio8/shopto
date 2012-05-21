package code.snippet

import net.liftweb._
import http._
import util.Helpers._


object OnSubmit {
  def render = {
    // process the form
    def process() {
        S.redirectTo("/registrationForm.html")
    }
    "type=submit" #> SHtml.onSubmitUnit(process)
    println("test gitHub")
  }
}