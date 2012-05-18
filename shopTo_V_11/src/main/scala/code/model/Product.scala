package code.model

object ListOfProducts {
  val allProducts = new scala.collection.mutable.ListBuffer[Product]
}


class Product(var name: String,var price: String, var currency:String, var image: String) {

}
