package parsing
import model.Airport
import model.Airport.airportCountry

case class airportFile(filepath: String) extends csvFile(filepath) {
  val airports = data.map(Airport(_))

  val airportsMap: Map[String, List[Airport]] = airports.groupBy(airportCountry(_).get)

  def getAirportByCountry(countryId: String): Option[List[Airport]] = airportsMap.get(countryId)
}
