package parsing
import model.Country

import scala.collection.mutable.HashMap

case class CountryFile(filepath: String){
  val countries = CSV.read(filepath, Country.fromCsvLine, ",")

  """val countriesMap: HashMap[String, Country] = HashMap()
  val countriesIdMap: HashMap[String, String] = HashMap()

  def loadCountries(): Unit = countries.map(country => countriesMap.addOne(country.countryCode -> country))
  def loadCountryIds(): Unit = countries.map(country => countriesIdMap.addOne(countryName(country).get -> countryCode(country).get))

  def getCountryById(countryId: String): Option[Country] = countriesMap.get(countryId)
  def getCountryByName(name: String): Option[Country] = {
    val countryId = countriesIdMap.get(name)
    countryId.isEmpty match {
      case true => None
      case false => getCountryById(countryId.get)
    }
  }

  loadCountries()
  loadCountryIds()"""

}
