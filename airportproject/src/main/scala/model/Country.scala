package model

import scala.util.Try
case class Country(countryCode: String, countryName: String)

object Country {
  def fromCsvLine(line: Array[String]): Option[Country] = {
    line.length match {
      case 5 | 6 => parseCountry(line)
      case _ => None
    }
  }
  def parseCountry(line : Array[String]): Option[Country] = {
    (Try(line(1)).toOption, Try(line(2)).toOption) match {
      case (Some(countryCode), Some(countryName)) => Some(Country(countryCode, countryName))
      case _ => None
    }
  }
}


