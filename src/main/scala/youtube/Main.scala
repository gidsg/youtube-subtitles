package youtube

object Main extends App with YouTubeAccess  {
println("Hello, World!")

  val captions = client.captions()
  val req = captions.list("snippet", "lUpCqBonOFw")
  val items = req.execute().getItems
  println(items)
}
