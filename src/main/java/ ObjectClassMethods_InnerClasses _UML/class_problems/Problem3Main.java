import java.util.*;

public class Problem3Main {

    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        1,
                        "Which is a programming language?",
                        "C",
                        5
                )
        );

        exam.addQuestion(
                new TrueFalseQuestion(
                        2,
                        "Java is an object-oriented language.",
                        false,
                        5
                )
        );

        Attempt attempt = exam.startExam(student);

        attempt.recordAnswer(1, "C");
        attempt.recordAnswer(2, "true");

        attempt.submit();

        attempt.recordAnswer(1, "B");
    }
}




class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}




abstract class Question {

    private int questionNumber;
    private String text;
    private int marks;

    public Question(
            int questionNumber,
            String text,
            int marks) {

        this.questionNumber = questionNumber;
        this.text = text;
        this.marks = marks;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getMarks() {
        return marks;
    }

    public abstract boolean evaluate(String answer);
}




class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    public MultipleChoiceQuestion(
            int questionNumber,
            String text,
            String correctAnswer,
            int marks) {

        super(questionNumber, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {

        return correctAnswer.equalsIgnoreCase(answer);
    }
}



class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(
            int questionNumber,
            String text,
            boolean correctAnswer,
            int marks) {

        super(questionNumber, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {

        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}




class ShortAnswerQuestion extends Question {

    private String correctAnswer;

    public ShortAnswerQuestion(
            int questionNumber,
            String text,
            String correctAnswer,
            int marks) {

        super(questionNumber, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {

        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}




class Examination {

    private String name;
    private List<Question> questions;

    public Examination(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Attempt startExam(Student student) {

        System.out.println(
                name +
                " started by " +
                student.getName() +
                "."
        );

        return new Attempt(student, this, questions);
    }

    public String getName() {
        return name;
    }
}



class Attempt {

    private Student student;
    private Examination examination;
    private List<Question> questions;
    private Map<Integer, String> answers;
    private boolean submitted;

    public Attempt(
            Student student,
            Examination examination,
            List<Question> questions) {

        this.student = student;
        this.examination = examination;
        this.questions = questions;
        this.answers = new HashMap<>();
        this.submitted = false;
    }

    public void recordAnswer(
            int questionNumber,
            String answer) {

        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination."
            );
            return;
        }

        answers.put(questionNumber, answer);

        System.out.println(
                "Answer recorded for Question " +
                questionNumber + "."
        );
    }

    public void submit() {

        if (submitted) {
            return;
        }

        submitted = true;

        System.out.println(
                examination.getName() +
                " submitted by " +
                student.getName() +
                "."
        );

        calculateResult();
    }

    private void calculateResult() {

        int totalScore = 0;
        int totalMarks = 0;

        for (Question question : questions) {

            totalMarks += question.getMarks();

            String answer =
                    answers.get(question.getQuestionNumber());

            boolean correct =
                    answer != null &&
                    question.evaluate(answer);

            int score = correct ? question.getMarks() : 0;

            totalScore += score;

            System.out.println(
                    "Question " +
                    question.getQuestionNumber() +
                    ": " +
                    (correct ? "Correct" : "Incorrect") +
                    " (" +
                    score +
                    " points)"
            );
        }

        System.out.println(
                "Total score: " +
                totalScore +
                "/" +
                totalMarks
        );
    }
}
