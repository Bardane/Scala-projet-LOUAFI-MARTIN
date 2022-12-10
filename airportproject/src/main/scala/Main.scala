import parsing.{CountryFile, airportFile, runwayFile}

object Main extends App {
  val countries = CountryFile("./././resources/countries.csv")
  println(countries.countries(0))
}
