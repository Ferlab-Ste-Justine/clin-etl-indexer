package bio.ferlab.clin.idx.hpo

import org.apache.spark.sql.{DataFrame, SparkSession}

object ReadHPOData {
  def fromJson(spark: SparkSession): DataFrame = {
    spark.read.json(getClass.getResource("./hpo_terms.json").getFile)
  }

  def fromUpstream(spark: SparkSession): DataFrame = {
    throw new FunctionNotImplementedException()
  }
}