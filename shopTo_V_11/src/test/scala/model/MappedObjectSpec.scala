package model

import org.specs2.mutable.SpecificationWithJUnit
import net.liftweb.mapper._
import code.model.{Client, MappedObject}
import org.openqa.selenium.firefox.FirefoxDriver
import code.acceptance.infrastructure.WebSpecification

class MappedObjectSpec extends SpecificationWithJUnit with WebSpecification {
  val driver = new FirefoxDriver

  "MappedObject object" should {
    "connect to the h2 DB" in {
      MappedObject.init
      DB.use(DefaultConnectionIdentifier) {
        conn => true
      }
    }

    "create a table 'client'" in {
      Client.dbTableName must_== "clients"
    }

    "populate the 'client' DB table" in {
      driver.get("http://localhost:8080/registrationForm.html") //webapp running port
      driver.findElementById("forenames").sendKeys("anyname")
      driver.findElementById("submit").click()
      val start = System.currentTimeMillis()
      var result = false
      var forename = ""
      while (!result && forename.equals("") && System.currentTimeMillis() - start < 5000) {
        result = driver.getTitle.equals("App: test Home")
        forename = Client.forenames
        Thread.sleep(50)
      }


      val clients: List[Client] = Client.findAll
      driver.close()
      clients.length must_== 1
    }
  }
}
