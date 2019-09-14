

import scala.collection.immutable.TreeSet

object MinimumNumberofRefuelingStops {

  case class Station(x: Int, gus: Int)

  implicit val StationOrdering: Ordering[Station] = Ordering.by(e => (e.gus.toString + "." + e.x).toDouble)

  def minRefuelStops(target: Int, startFuel: Int, stations: Array[Array[Int]]): Int = {

    var remainStation = stations.map(e => Station(e(0), e(1)))
    var passStation = TreeSet.empty[Station]
    var maxAchieve = startFuel
    var count = 0
    while (maxAchieve < target) {
      lazy val station = remainStation.head
      if(remainStation.nonEmpty && maxAchieve >= station.x) {
        passStation += station
        remainStation = remainStation.tail
      }
      else {
        if(passStation.isEmpty) {
          count = -1
          maxAchieve = target
        } else {

          val max = passStation.max
          count += 1
          maxAchieve += max.gus
          passStation -= max
        }
      }
    }
    if(maxAchieve < target) -1 else count
  }

  def main(args: Array[String]): Unit = {
    println(TreeSet(Station(1, 2), Station(2, 2)))
  }

}
