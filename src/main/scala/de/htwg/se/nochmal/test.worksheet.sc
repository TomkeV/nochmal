
case class PitchMatrix[T](rows: Vector[Vector[T]]):
    def this(lineNum:Int = 4, colNum:Int = 7, filling:T = " ") = this(Vector.tabulate(lineNum, colNum) { (row, col) => filling })

val myPitch = new PitchMatrix()