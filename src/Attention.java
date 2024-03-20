import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Attention {

    public static void main(String[] args) {
        // Contains the students that are waiting to be attended. The system ensures that it just can be four students in the queue (three waiting and one in the monitor)
        // Queue<Student> studentsQueue = new LinkedList<Student>();
        ArrayList<Student> studentsQueue = new ArrayList<Student>();

        // Semaphore to start or end a tutoring session
        Semaphore sOfficeChair = new Semaphore(1, true);
		
		Monitor monitor = new Monitor(studentsQueue, sOfficeChair);
		Student student1 = new Student("Estudiante 1", sOfficeChair, studentsQueue, monitor);
		Student student2 = new Student("Estudiante 2", sOfficeChair, studentsQueue, monitor);
		Student student3 = new Student("Estudiante 3", sOfficeChair, studentsQueue, monitor);
		Student student4 = new Student("Estudiante 4", sOfficeChair, studentsQueue, monitor);
		Student student5 = new Student("Estudiante 5", sOfficeChair, studentsQueue, monitor);
		Student student6 = new Student("Estudiante 6", sOfficeChair, studentsQueue, monitor);
		
		// sOfficeChair.drainPermits();

		monitor.start();
		student1.start();
		student2.start();
		student3.start();
		student4.start();
		student5.start();
		student6.start();
    }
}
