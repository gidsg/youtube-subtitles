package youtube

object Main extends App with YouTubeAccess  {
println("Hello, World!")

  val captions = client.captions()
  println(captions)
}
