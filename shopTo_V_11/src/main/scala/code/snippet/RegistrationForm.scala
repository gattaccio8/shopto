package code.snippet

import net.liftweb.http.SHtml._
import net.liftweb.util.Helpers._
import code.model.Client
import net.liftweb.http.{SessionVar, S}

object RegistrationForm extends SessionVar {
  def render = {

    var forenames = ""
    var surname = ""
    var email = ""
    var password = ""
    var securityAnswer = ""
    var address = ""
    var postCode = ""
    var country = ""
    var heardAboutUs = ""

    def process() {
      if (false)
        //S.error("too short!!")
        S.redirectTo("/")
      else {
        val client = Client(forenames, surname, email, password, securityAnswer, address, postCode, country, heardAboutUs)
        client.save()
        S.redirectTo("/index.html")
      }
    }

    "#forenames" #> text (forenames, forenames = _ , "id" -> "forenames") &
    "#surname" #> text (surname, surname = _ , "id" -> "surname") &
    "#email" #> text (email, email = _ , "id" -> "email") &
    "#password" #> text (password, password = _ , "id" -> "password") &
    "#securityAnswer" #> text (securityAnswer, securityAnswer = _ , "id" -> "securityAnswer") &
    "#address" #> text (address, address = _ , "id" -> "address") &
    "#postCode" #> text (postCode, postCode = _ , "id" -> "postCode") &
    "#country" #> text (country, country = _ , "id" -> "country") &
    "#heardAboutUs" #> text (heardAboutUs, heardAboutUs = _ , "id" -> "heardAboutUs") &
    "#submit" #>  ajaxSubmit("Register", () => {
      process()
    })

  }
}

