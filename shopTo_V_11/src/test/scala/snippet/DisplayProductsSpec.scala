package snippet

import org.specs2.mutable.SpecificationWithJUnit
import code.acceptance.infrastructure.WebSpecification
import infostructure.FirefoxWebDriver

class DisplayProductsSpec extends SpecificationWithJUnit with WebSpecification with FirefoxWebDriver {

  fireFoxDriver.get("http://localhost:8080/")

  "index page" should {
    "display the products list" in {
      fireFoxDriver.getTitle.equals("App: test Home")
      fireFoxDriver.findElementByClassName("products").isDisplayed
    }

    "display the items list" in {
      val bool = fireFoxDriver.findElementByClassName("td2").getText.equals("One Piece Gigant Battle")
      println(" result = " + fireFoxDriver.findElementByClassName("td2").getText)
      fireFoxDriver.close()
      bool
    }

    "display the item price" in {
      val bool = fireFoxDriver.findElementByClassName("td3").getText.equals("£35.79 GBP")
      println(" result = " + fireFoxDriver.findElementByClassName("td3").getText)
//      fireFoxDriver.close()
      bool
    }
  }
}