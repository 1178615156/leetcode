package leetcode


object SkylineProblem {

  import scala.collection.immutable.TreeSet

  case class Line(start: Int, height: Int)

  case class Entity(start: Int, end: Int, height: Int) {
    val startLine = Line(start, height)
    val endLine   = Line(end, height)
  }


  type Entities = Seq[Entity]
  type Lines = TreeSet[Line]

  implicit val orderingLine  : Ordering[Line]   = Ordering.by(e => (e.start, e.height))
  implicit val orderingEntity: Ordering[Entity] = Ordering.by(e => (e.start, e.end, e.height))

  def getSkyline(buildings: Array[Array[Int]]): List[List[Int]] = {


    def impl(entities: TreeSet[Entity], result: List[Entity]): List[Entity] = {
      entities.take(2).toList match {
        case Nil                               => result
        case a :: Nil                          => a :: result
        case a :: b :: Nil if a.end >= b.start => impl(entities - a, a :: result)
        case a :: b :: Nil                     =>
          val noInsert = Entity(a.start, b.start, a.height)
          if(b.height > a.height) {
            if(b.end < a.end)
              impl(entities - a + Entity(b.end, a.end, a.height), noInsert :: result)
            else
              impl(entities - a, noInsert :: result)
          } else if(b.height == a.height) {
            if(b.end <= a.end) {
              impl(entities - b, result)
            }
            else
              impl(entities - a, noInsert :: result)
          } else {
            if(b.end <= a.end)
              impl(entities - b, result)
            else
              impl(entities - b + Entity(a.end, b.end, b.height), result)
          }
      }
    }

    val entities = buildings.toSeq.map(e => Entity(e(0), e(1), e(2))).sortBy(e => e.start)
    val splits = takeSplit(entities, Int.MinValue, Nil, Nil)
    val result = splits.map(e => impl(TreeSet(e: _*), Nil).sorted).map(e => e.map(_.startLine) :+ e.last.endLine.copy(height = 0))
//    val result = splits.map(e => impl(TreeSet(e: _*), Nil).sorted).map(e => e.map(_.startLine) :+ e.last.endLine.copy(height = 0))

    result.flatten
      .foldLeft(List.empty[Line])((acc, line) =>
        if(acc.headOption.exists(e => line.height == e.height)) acc
        else if (acc.headOption.exists(e=>e.start == line.start)) Line(line.start,math.max(acc.head.height,line.height)) :: acc.tail
        else line :: acc)
      .reverse
      .map(e => List(e.start, e.height))
  }

  def takeSplit(entities: Entities, currMax: Int, currEntities: Entities, result: Seq[Entities]): Seq[Entities] = {
    if(entities.isEmpty)
      (currEntities +: result).map(_.reverse).reverse.filter(_.nonEmpty)
    else {
      val value = entities.head
      if(value.start <= currMax)
        takeSplit(entities.tail, math.max(value.end, currMax), entities.head +: currEntities, result)
      else
        takeSplit(entities.tail, value.end, value :: Nil, currEntities +: result)
    }
  }

  def main(args: Array[String]): Unit = {
    //    getSkyline(Array(
    //      Array(2, 9, 10),
    //      Array(3, 7, 15),
    //      Array(5, 12, 12),
    //      Array(15, 20, 10),
    //      Array(19, 24, 8)
    //    ))


    getSkyline(Array(
      Array(2, 4, 7),
      Array(2, 4, 5),
      Array(2, 4, 6)
    ))
  }
}
