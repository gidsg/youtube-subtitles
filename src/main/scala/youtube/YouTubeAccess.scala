package youtube

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.{YouTubeScopes, YouTube => YouTubeClient}

import scala.collection.JavaConversions._

trait YouTubeAccess {
  def appName = Config.appName
  def clientId = Config.clientId
  def clientSecret = Config.clientSecret
  def refreshToken = Config.refreshToken


  private val httpTransport = new NetHttpTransport()
  private val jacksonFactory = new JacksonFactory()

  private val credentials: GoogleCredential = new GoogleCredential.Builder()
    .setTransport(httpTransport)
    .setJsonFactory(jacksonFactory)
    .setClientSecrets(clientId, clientSecret)
    .build
    .setRefreshToken(refreshToken)

  // lazy to avoid initialising when in test
  lazy val client: YouTubeClient = new YouTubeClient.Builder(httpTransport, jacksonFactory, credentials)
    .setApplicationName(appName)
    .build


  def accessToken(): String = {
    credentials.refreshToken()
    credentials.getAccessToken
  }
}

