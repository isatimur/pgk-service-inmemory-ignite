logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.18")
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.2.2")
addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.0.3")