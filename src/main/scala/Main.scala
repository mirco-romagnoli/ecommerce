package it.bitrock

import slick.jdbc.PostgresProfile.api._

import java.time.LocalDate
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.io.Source
import scala.language.postfixOps

object Main extends App {
  val db = Database.forConfig("postgres")

  val products = TableQuery[Product]
  val customers = TableQuery[Customer]
  ujson.read(Source.fromResource("mock_products.json").mkString).arrOpt
    .map(
      _.map(p => (p("id").num.toInt, p("name").str, p("description").str, p("price").num.toString, p("isActive").bool)))
    .map(prods => DBIO.seq(products ++= prods))
    .map(db.run(_))
    .foreach(Await.result(_, 10 seconds))

  ujson.read(Source.fromResource("mock_customers.json").mkString).arrOpt
    .map(
      _.map(p => (
        p("id").num.toInt, p("fiscalCode").str, p("name").str, p("surname").str, LocalDate.parse(p("birthDate").str),
        p("cityOfBirth").str, p("countryOfBirth").str, p("cityOfResidence").str, p("streetOfResidence").str,
        p("regionOfResidence").str, p("countryOfResidence").str, p("email").str, p("phoneNumber").str, p("gender").str,
        true)
      )
    )
    .map(custs => DBIO.seq(customers ++= custs))
    .map(db.run(_))
    .foreach(Await.result(_, 10 seconds))
}