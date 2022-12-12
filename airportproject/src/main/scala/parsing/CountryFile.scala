package parsing
import model.Country

import scala.collection.mutable.HashMap

case class CountryFile(filepath: String){
  val countries = CSV.read(filepath, Country.fromCsvLine, ",")

  val countriesMap: HashMap[String, Country] = HashMap()
  val countriesIdMap: HashMap[String, String] = HashMap()

  def loadCountries(): Unit = countries.map(country => countriesMap.addOne(country.countryCode -> country))
  def loadCountryIds(): Unit = countries.map(country => countriesIdMap.addOne(country.countryName -> country.countryCode))

  def getCountryByCode(countryCode: String): Country = {
    countriesMap.get(countryCode) match {
      case Some(country) => countriesMap(countryCode)
      case None => Country("", "")
    }
  }

  def getCountryByName(countryName: String): Country = {
    countriesIdMap.get(countryName) match {
      case Some(country) => getCountryByCode(countriesIdMap(countryName))
      case None => Country("", "")
    }
  }

  loadCountries()
  loadCountryIds()

}
