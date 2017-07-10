package youtube

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}

import scala.io.Source

/**
  * Created by ggoldberg on 10/07/2017.
  */
object Main extends App with YouTubeAccess  {
println("Hello, World!")

  val captions = client.captions()
  println(captions)
}
