import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.{YouTube => YouTubeClient, YouTubeScopes}
import youtube.Settings
import scala.collection.JavaConversions._

import scala.collection.JavaConverters._

trait YouTubeAccess extends Settings {
  def appName: String = getMandatoryString("name")
  def contentOwner: String = getMandatoryString("youtube.contentOwner")
  val allowedChannels: List[String] = getStringList("youtube.allowedChannels")
  val disallowedVideos: List[String] = getStringList("youtube.disallowedVideos")

  def clientId = getMandatoryString("youtube.clientId")
  def clientSecret = getMandatoryString("youtube.clientSecret")
  def refreshToken = getMandatoryString("youtube.refreshToken")

  def monetizationPolicyId = getMandatoryString("youtube.monetizationPolicyId")
  def trackingPolicyId = getMandatoryString("youtube.trackingPolicyId")

  private val httpTransport = new NetHttpTransport()
  private val jacksonFactory = new JacksonFactory()

  private val credentials: GoogleCredential = new GoogleCredential.Builder()
    .setTransport(httpTransport)
    .setJsonFactory(jacksonFactory)
    .setClientSecrets(clientId, clientSecret)
    .build
    .setRefreshToken(refreshToken)

  private val partnerCredentials = credentials.createScoped(List(YouTubeScopes.YOUTUBE_FORCE_SSL))

  // lazy to avoid initialising when in test
  lazy val client: YouTubeClient = new YouTubeClient.Builder(httpTransport, jacksonFactory, credentials)
    .setApplicationName(appName)
    .build


  def accessToken(): String = {
    credentials.refreshToken()
    credentials.getAccessToken
  }
}

