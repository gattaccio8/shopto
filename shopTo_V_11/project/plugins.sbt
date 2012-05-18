


resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"


addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.0.0")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.0.0-M3")

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.11"))

