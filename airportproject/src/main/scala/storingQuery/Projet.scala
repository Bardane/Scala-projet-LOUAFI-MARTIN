package storingQuery
import parsing._
import model._
import collection.mutable.HashMap

case class Projet(){
 import Projet._
  def askQuery(str: String): List[String] = Query(str)
  def showReports(): List[String] = Report()
}

object Projet {

  val countryFileProj = CountryFile("./././resources/countries.csv")
  val runwayFileProj = RunwayFile("./././resources/runways.csv")
  val airportFileProj = AirportFile("./././resources/airports.csv")


  val fullMap : HashMap[String, List[(Airport, List[Runway])]] = HashMap()

  def fullMapInit(): Unit = {
    countryFileProj.countries.map { country =>
      val listAirport = airportFileProj.getAirportByCountryCode(country.countryCode)
      listAirport.isEmpty match {
        case false =>
          val listAirport2 = listAirport.map {airport =>
            val runwayList = runwayFileProj.getRunwayByAirport(airport.airportId)
            runwayList.isEmpty match {
              case true => (airport, List())
              case false => (airport, runwayList)
            }
          }
          fullMap.put(country.Code(), listAirport2)
        case true => fullMap.put(country.Code(), List())
      }
    }
  }

  fullMapInit()

  def getCountryInput(input: String): Option[Country] = {
    input.length match {
      case 2 => countryFileProj.getCountryByCode(input)
      case _ => countryFileProj.getCountryByName(input)
    }
  }

  def Query(countryCodeOrName: String): List[String]=
  {
    val countryExist : Option[Country] = getCountryInput(countryCodeOrName.replace("\"",""))
    countryExist.isEmpty match
    {
      case true => List(s"${countryCodeOrName} country code or name does not exist ")
      case false =>
        val head : String = s"Airports and runways for ${
          countryExist.map(x => x.countryName)
        } : \n"

        val tail : List[String] =
          fullMap.get(countryExist.map(x => x.countryCode))
            .flatMap{
            airportRunways =>
              val airport: String = airportRunways
                ._1
                .Print() + "\n"
              airport::airportRunways._2.map{runway.Print() + "\n"}
          }
        head::tail
    }
  }

  def Report(): List[String]=
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
    val highestAirports: List[String] =
      head1::countryAirport
        .take(10)
        .map{
          x => s"    - ${x._1.countryName} with ${x._2}\n"
        }

    val head2 : String = "10 countries with lowest number of airports (with count):\n"
    val lowestAirports: List[String] =
      head2 :: countryAirport
        .takeRight(10)
        .reverseMap {
          x => s"    - ${x._1.countryName} with ${x._2}\n"
        }

    val head3 : String = " Type of runways per country:\n"

    val runwayCountry : List[String] = countryFileProj.countries.flatMap{
      country =>
        val countryNameStr : String = s"    - ${country.countryName} :\n"
        val runwayNum : List[String] =
          fullMap.get(country.countryCode)
            .flatMap{x => x._3}
            .filter{runway => runway.runwaySurface != ""}
            .groupBy{runway => runway.runwaySurface}
            .mapValues(_.size)
            .toList
            .map(x => s"        - ${x._1} (nb = ${x._2})\n")

        countryNameStr::runwayNum
    }
    val runwayCountryNum = head3::runwayCountry

    val head4 : String = "The top 10 most common runway latitude: \n"
    val mostCommonLatitude : List[String] =
      head4::runwayFileProj
        .runways
        .filter{runway => runway.getColumn(9) != Some("Unknown")}
        .groupBy(runway => runway.getColumn(5))
        .maValues(_.size)
        .toList
        .sortWith(_._2 > _._2)
        .take(10)
        .map(x => s"    - ${x._1} (nb = ${x._2})\n"))

    highestAirports:::lowestAirports:::runwayCountry:::mostCommonLatitude
  }
}


