package code.acceptance.infrastructure

import net.liftweb.util.Props
import org.mortbay.jetty.Server
import org.mortbay.jetty.nio.SelectChannelConnector
import org.mortbay.jetty.webapp.WebAppContext
import java.io.File
import java.lang.Thread


object RunApp {

  val serverPort = 8080

  val testMode = "test"
  val productionMode = "production"
  setRunningMode(testMode)

  private def setRunningMode(runningMode: String) {
    System.setProperty("run.mode", runningMode)
    println("Running mode: " + Props.mode)
  }

   private val server = createServer
   private val context = createContext
   server.setHandler(context)


   private def createServer = {
     val server = new Server
     val selectChannelConnector = new SelectChannelConnector
     selectChannelConnector.setPort(serverPort)
     server.setConnectors(Array(selectChannelConnector))
     server
   }

   def startServer = {
     try {
       println(">>> STARTING EMBEDDED JETTY SERVER")
       server.start()
       while (!server.isRunning) Thread.sleep(100)
     } catch {
       case exception: Exception => {
         println("FAILED TO START JETTY SERVER")
         exception.printStackTrace()
         throw exception
       }
     }
   }


   private def createContext = {
     val context = new WebAppContext()
     context.setServer(server)
     context.setContextPath("/")
     if(new File("src/main/webapp").exists()) context.setWar("src/main/webapp") else context.setWar(getClass.getClassLoader.getResource("webapp").toExternalForm)
     server.addHandler(context)
     context
   }


   private def stopServer() {
     server.stop()
     //    server.join()
     val end = System.currentTimeMillis() + 10000
     while (!server.isStopped && end > System.currentTimeMillis()) Thread.sleep(100)
     if (!server.isStopped) print("!!!!! - SERVER FAILED TO STOP - !!!!!")
   }

   startServer

   OnShutDown.execute(() => stopServer() )
   //stopServer
 }



