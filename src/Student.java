import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Thread {
    // Name of the student, to identify it in the console
    private String name;
    private Semaphore sOfficeChair;
    private Monitor monitor;

    private ArrayList<Student> queue;

    private Random random = new Random();

	public Student(String name, Semaphore sOfficeChair, ArrayList<Student> queue, Monitor monitor) {
		this.name = name;
		this.sOfficeChair = sOfficeChair;
		this.queue = queue;
        this.monitor = monitor;
	}

    public void run() {
        while (true) {
            try {
                System.out.println("- [" + name + "] Veré si el moni está disponible.");

                // If the queue is full, the student goes to the computer room to program.
                if (queue.size() == 4) {
                    System.out.println("- [" + name + "] No hay sillas en el pasillo, me iré a la sala de cómputo y volveré más tarde.");

                    // To simulate the time the student takes to program, we use a random number between 1 and 10 seconds.
                    int sleepTime = 1000 + random.nextInt(9001);
                    sleep(sleepTime);
                }
                
                else {
                    queue.add(this);

                    System.out.println("- [" + name + "] Esperaré mi turno en el pasillo.");

                    sOfficeChair.acquire();
                    System.out.println("- [" + name + "] ¡Es mi turno! ¡Voy a entrar a la monitoría!");

                    if (monitor.getStatus() == true) {
                        System.out.println("- [" + name + "] El monitor está dormido, lo despertaré.");
                        monitor.setStatus(false);
                        System.out.println("- [MONITOR] Estoy despierto.");
                    }

                    // To simulate the time the monitor takes to attend the student, we use a random number between 1 and 10 seconds.
                    int sleepTime = 1000 + random.nextInt(4001);

                    System.out.println("\n*** TIEMPO DE MONITORIA ***\n");

                    sleep(sleepTime);

                    System.out.println("- [" + name + "] ¡Terminé! Me iré a mi casita.");
                    sOfficeChair.release();

                    queue.remove(this);

                    break;
                }

            } catch (InterruptedException e) {
                System.out.println("Error en el estudiante: " + e.getMessage());
            }
        }
    }

    public String getStudentName() {
        return name;
    }

}
