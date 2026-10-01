import java.util.*;

abstract class Question {
    String text;
    int points;

    Question(String text, int points) {
        this.text = text;
        this.points = points;
    }

    abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    String correctAnswer;

    MultipleChoiceQuestion(String text, String correctAnswer, int points) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    boolean correctAnswer;

    TrueFalseQuestion(String text, boolean correctAnswer, int points) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {
    String correctAnswer;

    ShortAnswerQuestion(String text, String correctAnswer, int points) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Examination {
    String name;
    ArrayList<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }
}

class Attempt {
    Student student;
    Examination examination;
    HashMap<Integer, String> answers = new HashMap<>();
    boolean submitted = false;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    void answer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        answers.put(questionNumber, answer);
        System.out.println("Answer recorded for Question " + questionNumber + ".");
    }

    void submit() {
        submitted = true;
        int total = 0;
        int score = 0;

        System.out.println(examination.name + " submitted by " + student.name + ".");

        for (int i = 0; i < examination.questions.size(); i++) {
            Question question = examination.questions.get(i);
            String answer = answers.get(i);

            if (answer != null && question.evaluate(answer)) {
                score += question.points;
                System.out.println("Result: Question " + (i + 1) + ": Correct (" + question.points + " points)");
            } else {
                System.out.println("Result: Question " + (i + 1) + ": Incorrect (0 points)");
            }

            total += question.points;
        }

        System.out.println("Total score: " + score + "/" + total);
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Student 1");
        Examination exam = new Examination("Exam A");

        exam.addQuestion(new MultipleChoiceQuestion("Question 1", "C", 5));
        exam.addQuestion(new TrueFalseQuestion("Question 2", false, 5));

        Attempt attempt = new Attempt(student, exam);

        System.out.println("Exam A started by Student 1.");
        attempt.answer(0, "C");
        attempt.answer(1, "True");
        attempt.submit();
        attempt.answer(0, "B");
    }
}
