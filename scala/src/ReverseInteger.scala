

class ReverseInteger {
  def reverse(int: Int): Int = {
    var result = 0L
    var i = math.abs(int)
    while (i > 0 && result >= 0) {
      result *= 10

      result += i % 10
      i /= 10

    }
    if(result > Int.MaxValue)
      0
    else if(int >= 0)
      result.toInt
    else
      -result.toInt
  }
}
