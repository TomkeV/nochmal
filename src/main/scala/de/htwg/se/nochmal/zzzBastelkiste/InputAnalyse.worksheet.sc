val eol = sys.props("line.separator")
val input = "x 1, 2 x 4, 12 x 3, 3"


val splitArray = input.split("""x""")


for (i <- 1 to splitArray.length-1) {
    println(splitArray(i))
}

val res = splitArray.map(x => 
    x.toString()
    )

for (i <- 1 to res.length-1) {
    println(res(i))
    println(res(i)(1))
}