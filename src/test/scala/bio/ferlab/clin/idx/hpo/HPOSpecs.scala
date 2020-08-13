package bio.ferlab.clin.idx.hpo

import bio.ferlab.clin.idx.hpo.utils.SparkSessionTrait
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class HPOSpecs extends AnyFlatSpec with SparkSessionTrait with Matchers {

  "HPO.filters" should "only keep descendants of specified ancestor" in {
    import spark.implicits._
    val path = this.getClass.getResource("/hpospecs_terms.json").getFile

    val dataSet = spark.read.json(path).as[HPOEntry]
    val matches = Seq(
      "HP:0000707"
    )

    val filtered = Utils.filterByAncestors(dataSet, matches);

    filtered.count should equal (2)
  }

}
