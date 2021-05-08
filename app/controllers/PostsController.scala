package controllers

import daos.PostDAO
import models.PostModel
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

import scala.concurrent.ExecutionContext

@Singleton
class PostsController @Inject() (
    postDao: PostDAO,
    controllerComponents: ControllerComponents
)(implicit executionContext: ExecutionContext)
    extends AbstractController(controllerComponents) {

  implicit val writer = new Writes[models.PostModel] {
    def writes(t: (models.PostModel)): JsValue = {
      Json.obj(
        "id" -> t.id,
        "title" -> t.title,
        "content" -> t.content
      )
    }
  }

  def index() = Action.async { implicit request: Request[AnyContent] =>
    postDao.all().map { case (posts) => Ok(Json.toJson(posts)) }
  /*     val posts = Array(
      Map(
        "id" -> "1",
        "title" -> "This is my first blog",
        "content" -> "It is written in Scala"
      ),
      Map(
        "id" -> "2",
        "title" -> "Today is so beautiful",
        "content" -> "The sun is shining everywhere and the bird is singing"
      )
    )
    Ok(Json.toJson(posts)) */
  }
}
