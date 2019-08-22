package leetcode

class MergeInterval {

  class Interval(var _start: Int = 0, var _end: Int = 0) {
    var start: Int = _start
    var end  : Int = _end
  }

  def isInsert(left: Interval, right: Interval) = {
    (right.start <= left.end && left.end <= right.end) || (left.start <= right.start && right.start <= left.end)
  }

  def mergeInterval(left: Interval, right: Interval) = {
    new Interval(math.min(left.start, right.start), math.max(left.end, right.end))
  }

  def merge(heads: List[Interval], tails: List[Interval]): List[Interval] = {
    if(tails.isEmpty)
      heads.reverse
    else {
      if(isInsert(heads.head, tails.head))
        merge(mergeInterval(heads.head, tails.head) :: heads.tail, tails.tail)
      else
        merge(tails.head +: heads, tails.tail)
    }
  }

  def merge(intervals: List[Interval]): List[Interval] = {
    if(intervals.isEmpty)
      Nil
    else {
      val list = intervals.sortBy(_.start)
      merge(list.head :: Nil, list.tail)
    }
  }
}
