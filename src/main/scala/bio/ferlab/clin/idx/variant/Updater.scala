package bio.ferlab.clin.idx.variant

import org.apache.spark.sql.SparkSession
import org.elasticsearch.spark.sql._


case class Data(id: String, person: Person, address: Option[Address])

case class Person(name: String, age: Int)

case class Address(city: String, civic: Int)

object Updater extends App {

  implicit val spark: SparkSession = SparkSession.builder
        .master("local")
    .config("es.index.auto.create", "true")
    .appName(s"Indexer").getOrCreate()

  import spark.implicits._


  val df = Seq(
    Data("A", Person("Jean", 12), None),
    Data("B",
      Person("George", 38),
      Some(Address("NY", 345 ))
    )
    ).toDF


  df.saveToEs("spark4/docs", Map("es.mapping.id" -> "id"))
//, "es.write.operation" -> "update"

}
