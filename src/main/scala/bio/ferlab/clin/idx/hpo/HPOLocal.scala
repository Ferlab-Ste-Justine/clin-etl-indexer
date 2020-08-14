package bio.ferlab.clin.idx.hpo

import org.apache.spark.sql.SparkSession

object HPOLocal extends App {
  implicit val spark: SparkSession = SparkSession.builder
    .master("local")
    .appName(s"HPOIndexer").getOrCreate()
  private val path = getClass.getResource("./hpo_terms.json").getFile

  HPO.start(path, "sparktest")
}
