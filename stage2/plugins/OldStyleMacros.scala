package cbt
trait OldStyleMacros extends BaseBuild{
  def oldStyleMacrosVersion = "2.1.0"

  private def scalaParadiseDependency =
    Resolver( mavenCentral ).bindOne(
      "org.scalamacros" % ("paradise_" ++ scalaVersion) % oldStyleMacrosVersion
    )

  override def dependencies = (
    super.dependencies // don't forget super.dependencies here
    ++ (
      if(scalaVersion.startsWith("2.10."))
        Seq(scalaParadiseDependency)
      else
        Seq()
    )
  )

  override def scalacOptions = (
    super.scalacOptions
    ++ (
      if(scalaVersion.startsWith("2.10."))
        Seq("-Xplugin:"++scalaParadiseDependency.jar.string)
      else
        Seq()
    )
  )
}
