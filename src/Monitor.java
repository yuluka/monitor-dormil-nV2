import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {

    // Same attributes as in the Attention class
    // private Queue<Student> studentsQueue;
    private ArrayList<Student> studentsQueue;
    private Semaphore sOfficeChair;

    // The monitor can be sleeping or awake. True means sleeping, false means awake.
    private boolean status = true; 

    public Monitor(ArrayList<Student> studentsQueue, Semaphore sOfficeChair) {
        this.studentsQueue = studentsQueue;
        this.sOfficeChair = sOfficeChair;
    }

    public void run() {
        while (true) {
            try {
                if (studentsQueue.size() > 0) {
                    System.out.println("- [MONITOR] ¡Hay estudiantes en la fila, así que obviamente estoy despierto!");
                    status = false;
                } else {
                    System.out.println("- [MONITOR] No hay estudiantes en la fila. Me dormiré.");
                    status = true;

                    sleep(5000);

                    // We don't know why, but the line to stop the thread is being executed before the sleep time, and the print is not being shown.
                    break;
                }

                sleep(5000);
            } catch (Exception e) {
                System.out.println("Error en el monitor: " + e.getMessage());
            }
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public String getStatusStr() {
        return status ? "Dormido": "Despierto";
    }
}