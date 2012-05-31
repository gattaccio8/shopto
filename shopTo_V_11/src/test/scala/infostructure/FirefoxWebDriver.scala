package infostructure

import org.openqa.selenium.firefox.FirefoxDriver
import code.acceptance.infrastructure.OnShutDown

trait FirefoxWebDriver {
  val fireFoxDriver = new FirefoxDriver
  OnShutDown.execute(() => fireFoxDriver.close(), "-------- fireFoxDriver is closed --------")
}
