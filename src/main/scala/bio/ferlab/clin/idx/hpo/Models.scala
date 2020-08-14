package bio.ferlab.clin.idx.hpo

case class HPOEntry(id: String, name: String, parents: Seq[ParentData] = Seq.empty, ancestors: Seq[AncestorData] = Seq.empty, is_leaf: Boolean)
case class ParentData(id: String, name: String, parents: Seq[String] = Seq.empty)
case class AncestorData(id: String, name: String, parents: Seq[String] = Seq.empty)

case class CompactHPOEntry(id: String, name: String, parents: Seq[CompactParentData] = Seq.empty, ancestors: Seq[CompactAncestorData] = Seq.empty)
case class CompactParentData(id: String, name: String)
case class CompactAncestorData(id: String, name: String)

