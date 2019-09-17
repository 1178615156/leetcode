object smallestSufficientTeam {

  case class Value(peoples: Set[Int], skills: Seq[Boolean]) {
    val full: Boolean = skills.forall(identity)
    val size: Int     = skills.count(identity)

    def addPeople(people: Int, skill: Array[Boolean]) =
      Value(peoples + people, skills.zip(skill).map(e => e._1 || e._2))

    def contain(that: Value): Boolean =
      (this.peoples.intersect(that.peoples).size == this.peoples.size
        || this.skills.zip(that.skills).forall { case (l, r) => l || !r })
  }

  def smallestSufficientTeam(req_skills: Array[String], people: List[List[String]]): Array[Int] = {
    val skillSize                   = req_skills.length
    val skillSet : Map[String, Int] = req_skills.zipWithIndex.toMap
    val skillReverseSet             = skillSet.toList.map(_.swap).toMap
    val peopleSet: Seq[Set[String]] = people.map(e => Set(e: _*))
    val matrix                      = mkMatrix(skillSet, peopleSet)

    val peopleHasSkill     = people.zipWithIndex.toMap.filterKeys(_.nonEmpty).values.toList.sorted
    val skillOnlyOnePeople = req_skills.indices
      .filter { col => people.indices.map(row => if(matrix(row)(col)) 1 else 0).sum == 1 }
      .map(skillReverseSet.apply)
    val initPeople         = skillOnlyOnePeople.flatMap(e => peopleSet.zipWithIndex.toMap.filterKeys(_.contains(e)).values.toList)
    val initValue          = Value(initPeople.toSet, initPeople.map(row => matrix(row)).foldLeft(List.fill(skillSize)(false))((l, r) => l.zip(r).map(e => e._1 || e._2)))
    if(initValue.full)
      initValue.peoples.toList.toArray
    else
      select(matrix, peopleHasSkill.filter(e => !initPeople.contains(e)), initValue :: Nil).toArray
  }

  def select(matrix: Array[Array[Boolean]], peoples: Seq[Int], values: Seq[Value]): Set[Int] = {

    val next = peoples.flatMap { people =>
      values
        .filter(_.peoples.contains(people) == false)
        .map(_.addPeople(people, matrix(people)))
    }

    next.find(_.full) match {
      case None         => select(matrix, peoples, reduce(next, Nil))
      case Some(result) => result.peoples
    }
  }

  def reduce(values: Seq[Value], result: Seq[Value]): Seq[Value] = {
    if(values.isEmpty)
      result
    else {
      val head = values.head
      val tail = values.tail
      if(tail.exists(_.contain(head))) {
        reduce(tail, result)
      } else {
        reduce(tail, head +: result)
      }
    }
  }

  def mkMatrix(skillSet: Map[String, Int], peopleSet: Seq[Set[String]]): Array[Array[Boolean]] = {
    val matrix = Array.fill(peopleSet.size, skillSet.size)(false)
    peopleSet.map(e => e.map(skillSet.get).filter(_.nonEmpty).map(_.get)).zipWithIndex.foreach {
      case (cols, row) =>
        cols.foreach(col => matrix(row)(col) = true)
    }
    matrix
  }

  def main(args: Array[String]): Unit = {
    //    val result = smallestSufficientTeam(
    //      List("mwobudvo", "goczubcwnfze", "yspbsez", "pf", "ey", "hkq").toArray,
    //      List(List(), List("mwobudvo"), List("hkq"), List("pf"), List("pf"), List("mwobudvo", "pf"), List(), List("yspbsez"), List(), List("hkq"), List(), List(), List("goczubcwnfze", "pf", "hkq"), List("goczubcwnfze"), List("hkq"), List("mwobudvo"), List(), List("mwobudvo", "pf"), List("pf", "ey"), List("mwobudvo"), List("hkq"), List(), List("pf"), List("mwobudvo", "yspbsez"), List("mwobudvo", "goczubcwnfze"), List("goczubcwnfze", "pf"), List("goczubcwnfze"), List("goczubcwnfze"), List("mwobudvo"), List("mwobudvo", "goczubcwnfze"), List(), List("goczubcwnfze"), List(), List("goczubcwnfze"), List("mwobudvo"), List(), List("hkq"), List("yspbsez"), List("mwobudvo"), List("goczubcwnfze", "ey"))
    //    )
    val result = smallestSufficientTeam(Array("java", "nodejs", "reactjs"),
      List(
        List("java"),
        List("nodejs"),
        List("nodejs", "reactjs")
      ))
    //    println(result.toList)
    //    ["mwobudvo","goczubcwnfze","yspbsez","pf","ey","hkq"]
    //    [[],["mwobudvo"],["hkq"],["pf"],["pf"],["mwobudvo","pf"],[],["yspbsez"],[],["hkq"],[],[],["goczubcwnfze","pf","hkq"],["goczubcwnfze"],["hkq"],["mwobudvo"],[],["mwobudvo","pf"],["pf","ey"],["mwobudvo"],["hkq"],[],["pf"],["mwobudvo","yspbsez"],["mwobudvo","goczubcwnfze"],["goczubcwnfze","pf"],["goczubcwnfze"],["goczubcwnfze"],["mwobudvo"],["mwobudvo","goczubcwnfze"],[],["goczubcwnfze"],[],["goczubcwnfze"],["mwobudvo"],[],["hkq"],["yspbsez"],["mwobudvo"],["goczubcwnfze","ey"]]
    //    val result = smallestSufficientTeam(Array("algorithms", "math", "java", "reactjs", "csharp", "aws"),
    //      List(
    //        List("algorithms", "math", "java"),
    //        List("algorithms", "math", "reactjs"),
    //        List("java", "csharp", "aws"),
    //        List("reactjs", "csharp"),
    //        List("csharp", "math"),
    //        List("aws", "java")
    //      ))
    println(result.toList)
  }
}