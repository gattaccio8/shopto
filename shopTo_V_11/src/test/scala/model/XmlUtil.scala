//package model
//
//import io.Source
//import java.io.File
//import xml.{XML, NodeSeq}
//
//object XmlUtil {
//
////  def loadFile(file:File) : String = {
////    val source = Source.fromFile(file)
////    if (!file.exists) throw new IllegalArgumentException("the file " + file.getPath + " do not exists!")
////    val line = source.mkString
////    line
////  }
//
//  def readTags(file:File) : NodeSeq = {
//    val xml = XML.loadFile(file)
//    xml
//  }
//
//  def getProdDetails(file: File) = {
//    val nodeS = readTags(file)
//    for(val x <- nodeS \\ "Products") {
//      println((x \\ "Price").text)
//      println((x \\ "Name").text)
//      println((x \\ "Media" \\ "Boxart").text)
//    }
//  }
//}
