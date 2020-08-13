package bio.ferlab.clin.idx.hpo

import org.apache.spark.sql.{Dataset, SparkSession}


object Utils {


  /**
   * Filters a Dataset to remove all the entries without any ancestor found in @param matches.
   *
   * @param dataSet Dataset to filter
   * @param matches Contains all the ids of the ancestors to keep
   * @return Filtered Dataset
   */
  def filterByAncestors(dataSet: Dataset[HPOEntry], matches: Seq[String]): Dataset[HPOEntry] = {
    dataSet.filter(data => data.ancestors.map(ancestor => ancestor.id).intersect(matches).nonEmpty)
  }

  def compactHPOEntries(entries: Dataset[HPOEntry])(implicit spark: SparkSession): Dataset[CompactHPOEntry] = {
    import spark.implicits._
    entries.map(compactEntry)
  }

  def compactEntry(entry: HPOEntry): CompactHPOEntry = {
    CompactHPOEntry(entry.id, entry.name, entry.parents, entry.ancestors.map(compactAncestor))
  }

  def compactAncestor(entry: AncestorData): CompactAncestorData = {
    CompactAncestorData(entry.id, entry.name)
  }

}



