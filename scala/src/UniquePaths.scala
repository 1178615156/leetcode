package leetcode

object UniquePaths {
  def uniquePaths(m: Int, n: Int): Int = {
    val arrays = Array.fill(n,m)(1)
    for{
      i <- 1 until n
      j <- 1 until m
    }{
      arrays(i)(j) = arrays(i-1)(j) + arrays(i)(j)
    }
    arrays(n-1)(m-1)

  }
}
