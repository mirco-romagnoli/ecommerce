package it.bitrock

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

import java.time.{LocalDate, LocalDateTime}

class Product(tag: Tag) extends Table[(Int, String, String, String, Boolean)](tag, "product") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name", O.Length(100))
  def description = column[String]("description", O.Length(250))
  def price = column[String]("price", O.Length(9))
  def isActive = column[Boolean]("is_active")

  def * = (id, name, description, price, isActive)
}

class Customer(tag: Tag) extends Table[(Int, String, String, String, LocalDate, String, String, String, String, String, String, String, String, String, Boolean)](tag, "customer") {
  def id = column[Int]("id", O.PrimaryKey)
  def fiscalCode = column[String]("fiscal_code", O.Unique, O.Length(16))
  def name = column[String]("name", O.Length(50))
  def surname = column[String]("surname", O.Length(50))
  def birthDate = column[LocalDate]("birth_date")
  def cityOfBirth = column[String]("city_of_birth", O.Length(50))
  def countryOfBirth = column[String]("country_of_birth", O.Length(50))
  def cityOfResidence = column[String]("city_of_residence", O.Length(50))
  def streetOfResidence = column[String]("street_of_residence", O.Length(50))
  def regionOfResidence = column[String]("region_of_residence", O.Length(50))
  def countryOfResidence = column[String]("country_of_residence", O.Length(50))
  def email = column[String]("email", O.Unique, O.Length(150))
  def phoneNumber = column[String]("phone_number", O.Unique, O.Length(150))
  def gender = column[String]("gender", O.Length(1))
  def isActive = column[Boolean]("is_active")

  def * = (
    id, fiscalCode, name, surname, birthDate, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence,
    regionOfResidence, countryOfResidence, email, phoneNumber, gender, isActive
  )
}

class Basket(tag: Tag) extends Table[(Int, String, Boolean)](tag, "basket") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name", O.Length(50))
  def isActive = column[Boolean]("is_active")

  def * = (id, name, isActive)
}

class CustomerBasket(tag: Tag) extends Table[(Int, Int, Int, Int, Boolean)](tag, "customer_basket") {
  def id = column[Int]("id", O.PrimaryKey)
  def customerId = column[Int]("customer_id")
  def productId = column[Int]("product_id")
  def quantity = column[Int]("quantity")
  def isActive = column[Boolean]("is_active")

  def * = (id, customerId, productId, quantity, isActive)

  def customer = foreignKey("customer_basket_customer_id_fkey", customerId, TableQuery[Customer])(_.id)
  def product = foreignKey("customer_basket_product_id_fkey", customerId, TableQuery[Product])(_.id)
}

class EcommerceOrder(tag: Tag) extends Table[(Int, String, Boolean)](tag, "ecommerce_order") {
  def id = column[Int]("id", O.PrimaryKey)
  def code = column[String]("code", O.Length(50))
  def isActive = column[Boolean]("is_active")

  def * = (id, code, isActive)
}

class CustomerOrder(tag: Tag) extends Table[(Int, Int, Int, Int, String, LocalDate, LocalDateTime, Boolean)](tag, "customer_orders") {
  def id = column[Int]("id", O.PrimaryKey)
  def customerId = column[Int]("customer_id")
  def productId = column[Int]("product_id")
  def quantity = column[Int]("quantity")
  def totalAmount = column[String]("total_amount", O.Length(9))
  def orderDate = column[LocalDate]("order_date")
  def orderTime = column[LocalDateTime]("order_date")
  def isActive = column[Boolean]("is_active")

  def * = (id, customerId, productId, quantity, totalAmount, orderDate, orderTime, isActive)

  def customer = foreignKey("customer_basket_customer_id_fkey", customerId, TableQuery[Customer])(_.id)

  def product = foreignKey("customer_basket_product_id_fkey", customerId, TableQuery[Product])(_.id)
}