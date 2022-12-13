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


  selectMenu()





  /** Functions */

  def query(): Unit = {
    val countryCodeOrName = formatInput(readLine("Type the Country \n"))
      storingQueryApp.Query(countryCodeOrName) match {
        case "Wrong Country" =>
          println("This country does not exist, \n")
          query()
        case _ => println(storingQueryApp.Query(countryCodeOrName))
      }
    val mainMenu = readLine("Press enter to go back to menu")
    mainMenu match {
      case _ => selectMenu()
    }
  }

  def reports(): Unit = {
    println(storingQueryApp.Report())
    val mainMenu = readLine("Press enter to go back to menu")
    mainMenu match {
      case _ => selectMenu()
    }
  }

  def formatInput(input: String): String = input.length match {
    case 2 => input.toUpperCase
    case _ => input.toLowerCase.capitalize
  }

  def selectMenu(): Unit = {
    println("_____________________")
    println("Scala Airport Project")
    println("1. Query")
    println("2. Reports ")
    println("Q. Quit ")
    val queryOrReports = formatInput(readLine("Enter Your Choice: "))
    queryOrReports match {
      case "1" => query()
      case "2" => reports()
      case "Q" => println("Quitting the app...")
      case _ => println("This is not a valid option, please retype\n")
        selectMenu()
    }
  }

}
