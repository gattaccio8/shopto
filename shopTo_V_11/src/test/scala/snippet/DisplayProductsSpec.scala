package snippet

import org.specs2.mutable.SpecificationWithJUnit
import org.openqa.selenium.firefox.FirefoxDriver
import code.acceptance.infrastructure.WebSpecification

class DisplayProductsSpec extends SpecificationWithJUnit with WebSpecification {

  val driver = new FirefoxDriver
  driver.get("http://localhost:8080/")

  "index page" should {
    "display the products list" in {
      driver.getTitle.equals("App: test Home")
      driver.findElementByClassName("products").isDisplayed
    }

    "display the items list" in {
      val bool = driver.findElementByClassName("td2").getText.equals("One Piece Gigant Battle")
      println(" result = " + driver.findElementByClassName("td2").getText)
      driver.close()
      bool
    }

    "display the item price" in {
      val bool = driver.findElementByClassName("td3").getText.equals("£35.79 GBP")
      println(" result = " + driver.findElementByClassName("td3").getText)
      driver.close()
      bool
    }
  }
}