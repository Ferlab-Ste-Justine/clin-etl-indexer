package bio.ferlab.clin.idx.variant

import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{concat, sha1}
import org.elasticsearch.spark.sql._

object Indexer extends App {

  val Array(input, batchId, release) = args
  implicit val spark: SparkSession = SparkSession.builder
    .config("es.index.auto.create", "true")
    .config("es.write.operation", "index") //reindex data on update. to avoid reindexing use 'upsert' instead.
    .appName(s"Indexer")
    .getOrCreate()

  import spark.implicits._

  val df = spark.read.json(input).withColumn("id", sha1(concat($"chromosome", $"start", $"reference", $"alternate")))

  df.saveToEs(s"variants_bt_${batchId.toLowerCase()}_re_${release}/_doc", Map("es.mapping.id" -> "id"))


}
