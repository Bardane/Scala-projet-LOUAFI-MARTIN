import parsing.countryFile

import scala.collection.immutable.HashMap

object Main extends App {
  val countries = countryFile("./././resources/countries.csv")
  println(countries.getCountryByName("Kenya"))


}
