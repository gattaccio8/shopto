package code.xmlmessages

import org.specs2.mutable.SpecificationWithJUnit
import java.io.File
import code.model.xmlmessages.XmlMessageUtil
import code.acceptance.infrastructure.WebSpecification

class XmlMessageUtilSpec extends SpecificationWithJUnit with WebSpecification {

  val url = "/Users/fabio/Documents/dev/projects/shopTo_V_11/src/test/resources/response/response.xml"
  val file = new File(url)

  "the xml file contains " should {
    "the first barcode" in {
      (XmlMessageUtil.readTags(file) \\ "Products" \\ "Barcode").headOption.map (_.text)  must beSome("3700577003066")
    }

    "the names " in {
      XmlMessageUtil.getProdDetails(file)
      true
    }
  }
}