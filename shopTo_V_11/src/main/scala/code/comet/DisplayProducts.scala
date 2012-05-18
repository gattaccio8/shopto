package code.comet

import net.liftweb.util.Helpers
import code.model.xmlmessages.XmlMessageUtil
import java.io.File
import net.liftweb.http.CometActor
import code.httpclientrequest.HttpClientRequest

class DisplayProducts extends CometActor {

   def render = {
    import Helpers._

    val file = new File("/Users/fabio/Documents/dev/projects/shopTo_V_11/src/test/resources/response/response.xml")
//    val products = XmlMessageUtil.getProdDetails //file)
    val products = XmlMessageUtil.getProdDetails(file)
     println("item " + products.map(y => y.toString))
     ".products *" #> products.map(x => ".td1 *" #> <img class="images" src={x.image} alt="picture"/> &
     ".td2 *" #>  x.name &
     ".td3 *" #> (<div>£{x.price} {x.currency}</div>) )
   }


  override def localSetup {
    super.localSetup()
  }

  override def localShutdown {
    super.localShutdown()
  }
}