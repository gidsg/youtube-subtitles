package youtube

import java.io.File

import com.typesafe.config.ConfigFactory

/**
  * Created by ggoldberg on 10/07/2017.
  */
object Config {

  val home = System.getProperty("user.home")
  val myConfigFile = new File(home + "/.youtube-subtitles/youtube-subtitles.conf")
  val fileConfig = ConfigFactory.parseFile(myConfigFile)

  val conf = ConfigFactory.load(fileConfig)
  val youtubeConfig = conf.getConfig("youtube")
  conf.checkValid(ConfigFactory.defaultReference(), "youtube")

  def appName = youtubeConfig.getString("name")
  def clientId = youtubeConfig.getString("clientId")
  def clientSecret = youtubeConfig.getString("clientSecret")
  def refreshToken = youtubeConfig.getString("refreshToken")

}
