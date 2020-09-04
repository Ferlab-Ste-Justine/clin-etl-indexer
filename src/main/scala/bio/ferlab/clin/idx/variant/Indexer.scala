package bio.ferlab.clin.idx.variant

import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{concat, sha1}
import org.elasticsearch.spark.sql._

object Indexer extends App {

  val Array(input, batchId) = args
  implicit val spark: SparkSession = SparkSession.builder
    .config("es.index.auto.create", "true")
    .appName(s"Indexer").getOrCreate()

  import spark.implicits._

  val df = spark.read.json(input).withColumn("id", sha1(concat($"chromosome", $"start", $"reference", $"alternate")))

  df.saveToEs(s"variants_re_${batchId.toLowerCase()}/_doc", Map("es.mapping.id" -> "id"))


}
