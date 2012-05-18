package code.utility

import org.joda.time.DateTime


object DateTimeClock {

  def currentTime() = {
    new DateTime()
  }
}