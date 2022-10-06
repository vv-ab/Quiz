import scala.util.Random

class Question(
  val text: String,
  val answers: Map[Answer, String],
  val correctAnswer: Answer,
)

enum Answer(val key: String) {
  case A extends Answer("a")
  case B extends Answer("b")
  case C extends Answer("c")
  case D extends Answer("d")

  def matches(value: String): Boolean = {
    value.toLowerCase() == this.key
  }
}

@main
def main(): Unit = {

  val questions: List[Question] = List(
    new Question(
      "Wie heisst der Architekt des Ulmer Stadthauses?",
      Map(
        Answer.A -> "A: Frank Gehry",
        Answer.B -> "B: Richard Meier",
        Answer.C -> "C: Cesar Pelli",
        Answer.D -> "D: Meister Gerhard",
      ),
      Answer.B,
    ),
    new Question(
      "Welches ist die größte Stadt der Welt?",
      Map(
        Answer.A -> "A: Moskau",
        Answer.B -> "B: Beijing",
        Answer.C -> "C: Bahrain",
        Answer.D -> "D: Mexiko-Stadt"
      ),
      Answer.D,
    ),
    new Question(
      "Mit wie vielen Figuren startet ein Schachspiel?",
      Map(
        Answer.A -> "A: 16",
        Answer.B -> "B: 22",
        Answer.C -> "C: 32",
        Answer.D -> "D: 48",
      ),
      Answer.C,
    ),
    new Question(
      "Wie heißt die Schicht der Atmosphäre, die der Erde am nächsten ist?",
      Map(
        Answer.A -> "A: Stratosphäre",
        Answer.B -> "B: Mesosphäre",
        Answer.C -> "C: Troposphäre",
        Answer.D -> "D: Thermosphäre"
      ),
      Answer.C,
    )
  )

  for (question <- questions) {
    askQuestion(question)
  }

}

def askQuestion(question: Question): Unit = {
  println(question.text)

  for ((answer, text) <- question.answers) {
    println(text)
  }

  println("J: Joker")

  val answer = Console.in.readLine().toLowerCase()

  if (question.correctAnswer.matches(answer)) {
    println("Correct!")
  }
  else if (answer == "j") {
    val correctAnswer = (question.correctAnswer, question.answers(question.correctAnswer))
    val wrongAnswer = Random.shuffle(question.answers
      .filter({ case (answer, text) => answer != question.correctAnswer})
    ).head

    val answers: List[(Answer, String)] = List(correctAnswer, wrongAnswer).sortBy({ case (answer, text) => answer.ordinal })
    for ((answer, text) <- answers) {
      println(text)
    }

    val answer = Console.in.readLine()

    if (question.correctAnswer.matches(answer)) {
      println("Correct!")
    }
    else {
      println("Wrong...")
    }
  }
  else {
    println("Wrong!")
  }
}

