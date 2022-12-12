package model

import scala.util.Try

case class Airport(airportId: String, airportCountry: String)

object Airport {
  def fromCsvLine(line: Array[String]): Option[Airport] = {
    parseAirport(line)
  }

  def parseAirport(line: Array[String]): Option[Airport] = {
    (Try(line(1)).toOption, Try(line(8)).toOption) match {
      case (Some(airportId), Some(airportCountry)) => Some(Airport(airportId, airportCountry))
      case _ => None
    }
  }

  def Print(): String = {
    "pui"
  }
}

