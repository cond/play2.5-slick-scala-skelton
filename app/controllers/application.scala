package controllers

import javax.inject._
import play.api.i18n._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import dao.UserDAO
import models.User

@Singleton
class Application @Inject()(val messagesApi: MessagesApi, userDao: UserDAO) extends Controller with I18nSupport {

  def list() = Action.async {
    userDao.list().map {
      userList => Ok(views.html.userlist(userList))
    }
  }

}
