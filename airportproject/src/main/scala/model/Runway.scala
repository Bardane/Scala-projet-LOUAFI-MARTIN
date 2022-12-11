package model

import scala.util.Try

case class Runway(airportId: String, runwayType: String, runwayLatitude: String)

object Runway {
  def fromCsvLine(line: Array[String]): Option[Runway] = {
    parseRunway(line)
  }

  def parseRunway(line: Array[String]): Option[Runway] = {
    (Try(line(2)).toOption, Try(line(5)).toOption, Try(line(8)).toOption) match {
      case (Some(airportId), Some(runwayType), Some(runwayLatitude)) => Some(Runway(airportId, runwayType, runwayLatitude))
      case _ => None
    }
  }
}

