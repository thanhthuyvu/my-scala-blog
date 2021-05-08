package daos

import models.PostModel

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class PostDAO @Inject() (
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Posts = TableQuery[PostsTable]

  def all(): Future[Seq[PostModel]] = db.run(Posts.result)

  private class PostsTable(tag: Tag) extends Table[PostModel](tag, "posts") {

    def id = column[Long]("id", O.PrimaryKey)
    def title = column[String]("title")
    def content = column[String]("content")

    def * = (id, title, content) <> (PostModel.tupled, PostModel.unapply)
  }
}
