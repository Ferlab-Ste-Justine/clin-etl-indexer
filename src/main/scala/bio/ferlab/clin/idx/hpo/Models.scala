package bio.ferlab.clin.idx.hpo

case class HPOEntry(id: String, name: String, parents: Seq[String] = Seq.empty, ancestors: Seq[AncestorData] = Seq.empty, is_leaf: Boolean)

case class AncestorData(id: String, name: String, parents: Seq[String] = Seq.empty)

case class CompactAncestorData(id: String, name: String)

case class CompactHPOEntry(id: String, name: String, parents: Seq[String], ancestors: Seq[CompactAncestorData])