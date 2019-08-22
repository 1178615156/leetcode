    case class Format(t: String)

    implicit class MyFormat(val sc: StringContext) {
      def myFormat(args: Any*) = {
        val partIter = sc.parts.iterator
        var stringFormatArgs = List.empty[Object]
        val sb = new StringBuilder(partIter.next())

        def impl(list: List[Any]): Unit = {
          list match {
            case (_: Format) :: tail                  =>
              throw new Exception("")
            case (any: Object) :: (format: Format) :: tail =>
              sb.append(partIter.next()).append(format.t).append(partIter.next())
              stringFormatArgs = any :: stringFormatArgs
              impl(tail)
            case (any: Any) :: tail                        =>
              sb.append(any.toString).append(partIter.next())
              impl(tail)
            case Nil                                       =>
          }
        }

        impl(args.toList)
        String.format(sb.toString(), stringFormatArgs.reverse: _*)
      }
    }

    val amt = 1234.1234d
    val name = "James"
    val len = Format("20s")
    val prec = Format("7.2f")

    myFormat"$name%$len,$amt%$prec,"
