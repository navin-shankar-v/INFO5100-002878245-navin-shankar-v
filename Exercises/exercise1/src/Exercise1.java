import java.util.*;

import static java.lang.System.out;

class Student {
    private final String studentName;
    private final String studentType;
    private List<Integer> quizScores = new ArrayList<>();

    public Student(String studentName, String studentType, List<Integer> quizScores) {
        this.studentName = studentName;
        this.studentType = studentType;
        this.quizScores = quizScores;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentType() {
        return studentType;
    }

    public List<Integer> getQuizScores() {
        return quizScores;
    }

    public void calculateAverageScore() {
        int totalScore = 0;
        for (Integer score : quizScores) {
            totalScore += score;
        }
        double averageScore = (double) totalScore / quizScores.size(); //Total score is cast to double

        out.println(studentName +"  "+studentType+"  : Average Quiz Score = " + averageScore+"\n");
    }
}

class PartTimeStudent extends Student {
    public PartTimeStudent(String studentName, List<Integer> quizScores) {
        super(studentName, "Part Time Student", quizScores);
    }
}

class FullTimeStudent extends Student {
    public FullTimeStudent(String studentName, List<Integer> quizScores) {
        super(studentName, "Full Time Student", quizScores);
    }
}

class Session {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printStudentList() {
        out.println("Student List in a Session: \n");
        for (Student student : students) {
            out.println(student.getStudentName() + " " + student.getStudentType() + "\n");
        }
    }

    public void printQuizScores() {
        System.out.println("Quiz Scores of Each Student: \n");
        for (Student student : students) {
            System.out.println(student.getStudentName() + " " + student.getStudentType() + ": " + student.getQuizScores()+"\n");
        }
    }

    public void calculateAndPrintAverageScores() {
        out.println("Average Quiz Scores of Student : \n");
        for (Student student : students) {
            student.calculateAverageScore();
        }
    }

    public void printScoresAscending() {
        for (Student student : students) {
            List<Integer> quizScores = student.getQuizScores();
            Collections.sort(quizScores); // Sort the quiz scores in ascending order
            System.out.println(student.getStudentName() + " " + student.getStudentType() + ": Quiz Scores in Ascending Order " + quizScores + "\n");
        }
    }

    public void printPartTimeStudentNames() {
        out.println("Part-Time Students:");
        for (Student student : students) {
            if (student.getStudentType().equals("Part Time Student")) {
                out.println(student.getStudentName() + " " + student.getStudentType() + "\n");
            }
        }
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        List<String> randomNames = Arrays.asList("Navin", "Shruthi", "Sudharshana", "Vasuki", "Mithun", "Prem", "Sanjay", "Rithick", "Vijay", "Leo", "Harold", "Anotny", "Parthi", "Siddarth", "Trisha", "Lokesh", "Elisa", "Ratna", "Anirudh", "Vikram");
        Session session = new Session();

        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            List<Integer> quizScores = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                quizScores.add(random.nextInt(101));
            }
            String randomName = randomNames.get(random.nextInt(randomNames.size()));
            Student student;
            if (i % 2 == 0) {
                student = new FullTimeStudent(randomName + i, quizScores);
            } else {
                student = new PartTimeStudent(randomName + i, quizScores);
            }
            session.addStudent(student);
        }

        session.printStudentList();
        session.printQuizScores();
        session.calculateAndPrintAverageScores();
        session.printScoresAscending();
        session.printPartTimeStudentNames();
    }
}