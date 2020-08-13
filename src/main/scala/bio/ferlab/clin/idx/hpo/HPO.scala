package bio.ferlab.clin.idx.hpo

import org.apache.spark.sql.SparkSession
import org.elasticsearch.spark.sql.sparkDatasetFunctions

object HPO extends App{
  private val path: String = args(0)
  private val esIndexName: String = args(1)
  start(path, esIndexName)(SparkSession.builder().appName(s"HPOIndexer").getOrCreate())

  def start(path: String, esIndexName: String)(implicit spark: SparkSession): Unit = {
    val matches = Seq(
      "HP:0001197",
      "HP:0001507",
      "HP:0000478",
      "HP:0000598",
      "HP:0001574",
      "HP:0001626",
      "HP:0002086",
      "HP:0000924",
      "HP:0002086",
      "HP:0000924",
      "HP:0003011",
      "HP:0000119",
      "HP:0025031",
      "HP:0000152",
      "HP:0000707"
    )

    import spark.implicits._

    val dataSet = spark.read.json(path).as[HPOEntry]
    val filteredDataSet = Utils.compactHPOEntries(Utils.filterByAncestors(dataSet, matches))

//    filteredDataSet.toDF.saveToEs(esIndexName)
  }
}
