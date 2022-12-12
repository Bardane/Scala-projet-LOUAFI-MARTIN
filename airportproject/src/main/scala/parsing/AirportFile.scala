package parsing

import model.Airport

case class AirportFile(filepath: String){
  val airports = CSV.read(filepath, Airport.fromCsvLine, ",")

  val airportsMap: Map[String, List[Airport]] = airports.groupBy(_.airportCountry)

  def getAirportByCountryCode(countryCode: String): List[Airport] = {
    airportsMap.get(countryCode) match {
      case Some(airport) => airportsMap(countryCode)
      case None => List(Airport("", ""))
    }
  }
}
