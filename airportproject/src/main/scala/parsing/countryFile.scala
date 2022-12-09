package parsing
import scala.collection.mutable.HashMap
import model.Country
import model.Country.{countryCode, countryName}

case class countryFile(filepath: String) extends csvFile(filepath){
  val countries = data.map(Country(_))

  val countriesMap: HashMap[String, Country] = HashMap()
  val countriesIdMap: HashMap[String, String] = HashMap()

  def loadCountries(): Unit = countries.map(country => countriesMap.addOne(countryCode(country).get -> country))
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
  loadCountryIds()

}
