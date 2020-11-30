package bio.ferlab.clin.idx.genes

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{concat, sha1}
import org.elasticsearch.spark.sql._

object Indexer extends App {

  val Array(input, batchId, release) = args
  implicit val spark: SparkSession = SparkSession.builder
    .config("es.index.auto.create", "true")
    .appName(s"Indexer").getOrCreate()

  import spark.implicits._

  val df = spark.read.json(input)

  df.saveToEs(s"genes_re_${release}/_doc", Map("es.mapping.id" -> "ensembl_gene_id"))


}
