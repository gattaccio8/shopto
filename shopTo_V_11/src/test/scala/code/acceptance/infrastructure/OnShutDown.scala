package code.acceptance.infrastructure


object OnShutDown {

  def execute(f: () => Unit) {
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() {
        f()
        println("------------- RUN APP IS TERMINATED -------------")
      }
    })
  }

}