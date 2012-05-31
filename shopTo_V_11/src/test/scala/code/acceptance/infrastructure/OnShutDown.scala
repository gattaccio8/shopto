package code.acceptance.infrastructure


object OnShutDown {

  def execute(f: () => Unit, message: String) {
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() {
        f()
        println(message)
      }
    })
  }

}