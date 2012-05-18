package code.model.xmlmessages

import java.io.File
import io.Source
import xml.{XML, NodeSeq}
import code.model.{ListOfProducts, Product}

object XmlMessageUtil {

  def readTags(file:File) : NodeSeq = {
    val xml = XML.loadFile(file)
    xml
  }

  def getProdDetails(file: File) = {
    val nodeS = readTags(file)
    for(val pro <- nodeS \\ "Product") {
      var image =  (pro \\ "Media" \\ "Boxart").text
      var name =  (pro \\ "Name").text
      var currency = (pro \\ "Price" \\ "Currency").text
      var price =  (pro \\ "Price" \\ "Amount").text
      val product = new Product(name, price, currency, image)
      ListOfProducts.allProducts += product
    }
    ListOfProducts.allProducts
  }
}