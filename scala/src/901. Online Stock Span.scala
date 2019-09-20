final case class Price(price: Int, span: Int, jump: Seq[Price])

class StockSpanner() {
  private val ZERO = Price(Int.MaxValue, 0, Nil)
  private val HEAD = ZERO :: Nil
  private var list = HEAD

  def next(price: Int): Int = {
    val result = search(price, 0, list)
    list = result :: list
    result.span
  }


  @scala.annotation.tailrec
  final def search(price: Int, span: Int, list: Seq[Price]): Price = {
    val head = list.head
    if(head.price <= price)
      search(price, span + head.span, head.jump)
    else
      Price(price, span + 1, list)
  }


}

object StockSpanner {
  def main(args: Array[String]): Unit = {
    val sp = new StockSpanner()

    List(100, 80, 60, 70, 60, 75, 85).foreach(price => println(sp.next(price)))
  }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * var obj = new StockSpanner()
 * var param_1 = obj.next(price)
 */