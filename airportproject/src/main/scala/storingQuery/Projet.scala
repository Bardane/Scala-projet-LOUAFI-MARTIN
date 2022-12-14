package storingQuery
import parsing._
import model._
import collection.mutable.HashMap

case class Projet(){
 import Projet._
  def askQuery(str: String): String = Query(str)
  //def showReports(): List[String] = Report()
}

object Projet {

  val countryFileProj = CountryFile("./././resources/countries.csv")
  val runwayFileProj = RunwayFile("./././resources/runways.csv")
  val airportFileProj = AirportFile("./././resources/airports.csv")


  val fullMap : HashMap[Country, List[HashMap[Airport, List[Runway]]]] = HashMap()

  def fullMapInit(): Unit = {
    countryFileProj.countries.map { country =>
      val listAirport = airportFileProj.getAirportByCountryCode(country.countryCode)
      listAirport.isEmpty match {
        case false =>
          val listAirport2 = listAirport.map{ airport =>
            val runwayList = runwayFileProj.getRunwayByAirport(airport.airportId)
            HashMap(airport -> runwayList)
          }
          fullMap.put(country, listAirport2)
        case true => fullMap.put(country, List())
      }
    }
  }

  fullMapInit()

  def getCountryInput(input: String): Country = {
    input.length match {
      case 2 => countryFileProj.getCountryByCode(input)
      case _ => countryFileProj.getCountryByName(input)
    }
  }

  def Query(countryCodeOrName: String): String=
  {
    val countryExist : Country = getCountryInput(countryCodeOrName.replace("\"",""))
    countryExist.countryName match
    {
      case "" => "Wrong Country"
      case _ => s" ${fullMap(countryExist)}"
    }
  }

  def Report(choice: String) =
  {
    val countryAirport = countryFileProj.countries
      .map{country =>
        val airportCode = airportFileProj.getAirportByCountryCode(country.countryCode)
        airportCode.isEmpty match {
          case true => (country, 0)
          case false => (country, airportCode.size)
        }
      }
      .sortWith(_._2 > _._2)

    val head1 : String = "10 countries with highest number of airports (with count):\n"
    val highestAirports: String = head1 + countryAirport.take(10).map{x => s"${x._1.countryName} with ${x._2}\n"}

    val head2 : String = "10 countries with lowest number of airports (with count):\n"
    val lowestAirports: String =
      head2 + countryAirport
        .takeRight(10)
        .map{
          x => s"${x._1.countryName} with ${x._2}\n"
        }


    def runwayTypesCountry(countryIso: String) = {
      airportFileProj.getAirportByCountryCode(countryIso)
        .flatMap{x => runwayFileProj.getRunwayByAirport(x.airportId)}
        .groupBy(_.runwaySurface).mapValues(_.size).toList.sortBy(_._2).reverse
    }
    val head3 : String = "Type of runways per country:\n"
    val listIso = countryFileProj.countries.map(_.countryCode)
    val surfaceRunway = listIso.map(myListElement => (myListElement, runwayTypesCountry(myListElement)))
    val surfaceRunwayHead = head3 + surfaceRunway.map(x => s"${x._1}: ${x._2.map(_._1)}\n")

    val listRunwayLat = runwayFileProj.runways.map(_.runwayLatitude).groupBy(x => x).mapValues(_.size).toList.sortBy(_._2).reverse.take(10)
    val head4 : String = "Top 10 most common latitudes:\n"
    val latitudeRunwayHead = head4 + listRunwayLat

    choice match {
      case "1" => highestAirports
      case "2" => lowestAirports
      case "3" => surfaceRunwayHead
      case "4" => latitudeRunwayHead
    }

  }
}


