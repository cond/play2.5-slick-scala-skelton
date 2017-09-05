package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.User

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Users = TableQuery[UserTable]

  def list(): Future[Seq[User]] = {
    db.run(Users.result)
  }

  def insert(user: User): Future[Int] = {
    db.run(Users += user)
  }

  def findById(id: Long): Future[User] = {
    db.run(Users.filter(_.id === id.bind).result.head)
  }

  def update(id: Long, user: User): Future[Int] = {
    db.run(Users.filter(_.id === id.bind).update(user))
  }

  def delete(id: Long): Future[Int] =  {
    db.run(Users.filter(_.id === id.bind).delete)
  }

  private class UserTable(tag:Tag) extends Table[User](tag, "USER") {

    def id      = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name    = column[String]("NAME")
    def email   = column[String]("EMAIL")
    def company = column[String]("COMPANY")

    def * = (id, name, email, company) <> (User.tupled, User.unapply _)
  }
}
