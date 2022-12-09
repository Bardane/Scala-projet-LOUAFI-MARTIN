import parsing.countryFile

object Main extends App {
  val countries = countryFile("./././resources/countries.csv")
  println(countries.getCountryByName("France"))


}
