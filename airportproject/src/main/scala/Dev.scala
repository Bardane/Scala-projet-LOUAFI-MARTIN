import parsing.{CountryFile, AirportFile, RunwayFile}

object Dev extends App {
//  val countries = CountryFile("./././resources/countries.csv")
//  println(countries.countries)
//  println(countries.getCountryByCode("FR"))
//  println(countries.getCountryByName("France"))

  val airports = AirportFile("./././resources/airports.csv")
  println(airports.getAirportByCountryCode("FR"))

  val runways = RunwayFile("./././resources/runways.csv")
  println(runways.getRunwayByAirport("LFPG"))
}
