package code.driver

import org.specs2.mutable.SpecificationFeatures
import org.openqa.selenium._
import firefox.FirefoxDriver


class RegistrationFormDriver(registrationUrl: String,  webDriver: WebDriver) extends SpecificationFeatures {


  //def registerUser = driver.click(By.id("submit"))

  def getDriver = {
    val driver = new FirefoxDriver
    driver.get("http://localhost:8080/registrationForm.html")
    driver.close()
  }

}