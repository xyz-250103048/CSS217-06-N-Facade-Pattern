
import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private Map<String, Boolean> students;

    public StudentService() {
        students = new HashMap<>();
        students.put("S1", true);
        students.put("S2", false);
        students.put("S3", true);
    }

    public boolean studentExists(String studentId) {
        return students.containsKey(studentId);
    }

    public boolean isActive(String studentId) {
        return students.get(studentId);
    }
}
