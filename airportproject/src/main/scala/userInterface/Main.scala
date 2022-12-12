package userInterface
import parsing._
import model._
import storingQuery._

import scala.io.StdIn.readLine

object Main extends App {

  val storingQueryApp = storingQuery.Projet
  val countryFileProj = storingQueryApp.countryFileProj
  val runwayFileProj = storingQueryApp.runwayFileProj
  val airportFileProj = storingQueryApp.airportFileProj


  println("Scala Airport Project")
  val queryOrReports = readLine("Type q for query or r for reports\n")

  def formatInput(input: String): String = input.length match
    {
    case 2 => input.toUpperCase
    case _ => input.toLowerCase.capitalize
  }


  if (queryOrReports == "q") {
    query()
  }
  else if (queryOrReports == "r"){
    reports()
  }

  def query(): Unit = {
    val countryCodeOrName = formatInput(readLine("Type the Country \n"))
      storingQueryApp.Query(countryCodeOrName) match {
        case "Wrong Country" =>
          println("This country does not exist, \n")
          query()
        case _ => println(storingQueryApp.Query(countryCodeOrName))
      }
  }

  def reports() = {
    println(storingQueryApp.Report())
  }

}
