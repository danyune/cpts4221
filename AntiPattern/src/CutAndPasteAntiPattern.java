import java.util.*;

public class CutAndPasteAntiPattern {

	public static void main(String[] args) {
		List<Student> classRoster = new ArrayList<Student>();
		
		// Alice's gradebook
		Student student = new Student("Alice");
		Map<String, List<Integer>> studentMap = student.getStudentMap();
		List<Integer> homeworkGrades = new ArrayList<Integer>();
		homeworkGrades.add(80);
		homeworkGrades.add(90);
		homeworkGrades.add(100);
		List<Integer> examGrades = new ArrayList<Integer>();
		examGrades.add(50);
		examGrades.add(60);
		examGrades.add(70);
		studentMap.put("homework", homeworkGrades);
		studentMap.put("exam", examGrades);
		classRoster.add(student);
		
		// Bob's gradebook
		student = new Student("Bob");
		studentMap = student.getStudentMap();
		homeworkGrades = new ArrayList<Integer>();
		homeworkGrades.add(85);
		homeworkGrades.add(95);
		homeworkGrades.add(99);
		examGrades = new ArrayList<Integer>();
		examGrades.add(55);
		examGrades.add(65);
		examGrades.add(75);
		studentMap.put("homework", homeworkGrades);
		studentMap.put("exam", examGrades);
		classRoster.add(student);
		
		// Eve's gradebook
		student = new Student("Eve");
		studentMap = student.getStudentMap();
		homeworkGrades = new ArrayList<Integer>();
		homeworkGrades.add(33);
		homeworkGrades.add(44);
		homeworkGrades.add(55);
		examGrades = new ArrayList<Integer>();
		examGrades.add(66);
		examGrades.add(77);
		examGrades.add(88);
		studentMap.put("homework", homeworkGrades);
		studentMap.put("exam", examGrades);
		classRoster.add(student);
		
		for (Student s : classRoster) {
			System.out.println(s.getStudentName() + ": ");
			for (String thing : s.getStudentMap().keySet()) {
				System.out.print(thing + ": ");
				for (Integer grade : s.getStudentMap().get(thing)) {
					System.out.print(grade + " ");
				}
				System.out.println();
			}
		}
	}
}

class Homework {
	private int homeworkScore = 0;
	public Homework(int score) {
		homeworkScore = score;
	}
	
	public int getHomeworkScore() {
		return homeworkScore;
	}
}
class Exam {
	private int examScore = 0;
	public Exam(int score) {
		examScore = score;
	}
	
	public int getExamScore() {
		return examScore;
	}
}

class Student {
	Map<String, List<Integer>> studentMap = new LinkedHashMap<String, List<Integer>>();
	private String studentName;
	
	public Student(String name) {
		studentName = name;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public Map<String, List<Integer>> getStudentMap() {
		return studentMap;
	}
}
