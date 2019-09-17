object distanceBetweenBusStops {
  def distanceBetweenBusStops(distance: Array[Int], start: Int, destination: Int): Int = {
    val a = distance.slice(math.min(start,destination),math.max(start,destination)).sum
    val b = distance.sum - a
    math.min(a, b)
  }
}