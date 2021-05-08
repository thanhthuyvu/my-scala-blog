package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

@Singleton
class PostsController @Inject() (val controllerComponents: ControllerComponents)
    extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    val posts = Array(
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
    Ok(Json.toJson(posts))
  }
}
