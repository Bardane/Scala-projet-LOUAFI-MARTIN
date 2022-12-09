import parsing.{countryFile, airportFile, runwayFile}

object Main extends App {
  val countries = countryFile("./././resources/countries.csv")
  println(countries.getCountryByName("Kenya"))

  val airports = airportFile("./././resources/airports.csv")
  //println(airports.getAirportByCountry("FR"))

  val runways = runwayFile("./././resources/runways.csv")
  println(runways.getRunwayByAirport("LFPG"))


}
