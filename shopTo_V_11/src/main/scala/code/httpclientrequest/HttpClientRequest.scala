package code.httpclientrequest

import org.apache.http.client._
import java.text.SimpleDateFormat
import java.net.URLEncoder
import java.io.{BufferedReader, InputStreamReader}
import methods.{HttpRequestBase, HttpPost, HttpGet}
import org.apache.http.entity.StringEntity
import java.util.Date
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}

object HttpClientRequest {

  def main(args : Array[String]) : Unit = {
     doGet
     doPost
  }

  def doGet = {
    val encodedUrl = new String("http://api.skream.com/?xml=" + URLEncoder.encode("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
      "<REQUEST><CREATED>2012-01-10 10:21:23</CREATED><APIVERSION>0.1</APIVERSION><IDENTIFY><USERID>0</USERID>" +
      "<NAME>Skream</NAME><PASSWORD>3a53a6e8aa4c64a824c36f8d0cc3a604</PASSWORD></IDENTIFY><CALL>" +
      "<OBJECT>PRODUCT</OBJECT><METHOD>getAll</METHOD><PARAMS><LIMIT>5</LIMIT></PARAMS></CALL></REQUEST>", "UTF-8"))

    val getUrl = new String("http://api.skream.com/?xml=<?xml version=\"1.0\" encoding=\"UTF-8\"?>".replaceAll(" ", "%20").replaceAll("\"", "%27").replaceAll("<", "%3C").replaceAll(">", "%3E")  +
    "<REQUEST><CREATED>2012-01-10%2010:21:23</CREATED><APIVERSION>0.1</APIVERSION><IDENTIFY><USERID>0</USERID><NAME>Skream</NAME><PASSWORD>3a53a6e8aa4c64a824c36f8d0cc3a604</PASSWORD></IDENTIFY><CALL><OBJECT>PRODUCT</OBJECT><METHOD>getAll</METHOD><PARAMS><LIMIT>5</LIMIT></PARAMS></CALL></REQUEST>".replaceAll("<", "%3C").replaceAll(">", "%3E")).replace("2012-01-10%2010:21:23",getDate.substring(0,10) + "%20" + getDate.substring(11,19))

    println("get url:     " + getUrl)
    println("encoded url: " + encodedUrl + '\n' +  "is it equal? " + encodedUrl.equals(getUrl) + '\n' + "compared: " + encodedUrl.compare(getUrl))
    println("request:     " + request.getMethod)

    val httpClient = new DefaultHttpClient
    val httpGet = new HttpGet(getUrl)
    val brh = new BasicResponseHandler
    val responseBody = httpClient.execute(httpGet, brh)
    println("response: " + responseBody)
    httpClient.getConnectionManager.shutdown
  }

  def doPost = {
    val getUrl = new String("?xml=<?xml version=\"1.0\" encoding=\"UTF-8\"?>".replaceAll(" ", "%20").replaceAll("\"", "%27").replaceAll("<", "%3C").replaceAll(">", "%3E")  +
    "<REQUEST><CREATED>2012-01-10%2010:21:23</CREATED><APIVERSION>0.1</APIVERSION><IDENTIFY><USERID>0</USERID><NAME>Skream</NAME><PASSWORD>3a53a6e8aa4c64a824c36f8d0cc3a604</PASSWORD></IDENTIFY><CALL><OBJECT>PRODUCT</OBJECT><METHOD>getAll</METHOD><PARAMS><LIMIT>5</LIMIT></PARAMS></CALL></REQUEST>".replaceAll("<", "%3C").replaceAll(">", "%3E")).replace("2012-01-10%2010:21:23",getDate.substring(0,10) + "%20" + getDate.substring(11,19))

    val httpClient = new DefaultHttpClient
    val httpPost = new HttpPost("http://api.skream.com/" + getUrl)
//    val httpPost = new HttpPost("http://127.0.0.1:3000/")
    val xmlMessage = request.getMethod
    val basicResponderHandler = new BasicResponseHandler
    httpPost.setHeader("Content-Type", "text/xml")
    val stringEntity = new StringEntity("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xmlMessage,"text/xml","UTF-8") //add '\n' for server socket
//    val stringEntity = new StringEntity(URLEncoder.encode("?xml=<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xmlMessage),"text/xml","UTF-8")
//    stringEntity.setChunked(true)
    httpPost.setEntity(stringEntity)
    val responseBody = httpClient.execute(httpPost, basicResponderHandler)

    println("responseBody: " + responseBody)
    val fromClient = new BufferedReader(new InputStreamReader(httpPost.getEntity.getContent))
    println("sending...." + '\n' + fromClient.readLine() + "  *** type: " + httpPost.getEntity.getContentType)

    httpClient.getConnectionManager.shutdown
  }


  val request = new HttpRequestBase {
    def getMethod: String =
      <REQUEST><CREATED>{getDate}</CREATED><APIVERSION>0.1</APIVERSION><IDENTIFY><USERID>0</USERID><NAME>Skream</NAME><PASSWORD>3a53a6e8aa4c64a824c36f8d0cc3a604</PASSWORD></IDENTIFY><CALL><OBJECT>PRODUCT</OBJECT><METHOD>getAll</METHOD><PARAMS><LIMIT>5</LIMIT></PARAMS></CALL></REQUEST>.mkString
  }

  def getDate: String = {
    val date = new Date
    val timeStamp = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    timeStamp.format(date) //.mkString
  }
}