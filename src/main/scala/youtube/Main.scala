package youtube

import java.io.FileOutputStream

object Main extends App with YouTubeAccess  {
println("Hello, World!")

  val captions = client.captions()
  val listReq = captions.list("snippet", "lUpCqBonOFw")
  val items = listReq.execute().getItems
  println(items)

  val req = captions.download("Y6HXK1vdgdcx49kZ5e227-0X2IZMdDf8t_GbleN3tqA=").setTfmt("SRT")
  val outputStream  = new FileOutputStream("captionFile.srt")
  req.executeMediaAndDownloadTo(outputStream)

}
