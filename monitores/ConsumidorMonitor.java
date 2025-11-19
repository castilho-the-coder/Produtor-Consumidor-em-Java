import java.util.Random;

/**
 * Thread (Runnable) do Consumidor (para a versão Monitor).
 */
public class ConsumidorMonitor implements Runnable {
    private final BufferMonitor buffer;
    private final Random rand = new Random();

    public ConsumidorMonitor(BufferMonitor buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = buffer.consumir();
                Thread.sleep(rand.nextInt(1500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumidor (Monitor) interrompido.");
        }
    }
}
