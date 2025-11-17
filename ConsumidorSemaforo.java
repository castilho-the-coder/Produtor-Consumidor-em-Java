import java.util.concurrent.ThreadLocalRandom;

/**
 * Thread (Runnable) do Consumidor.
 */
public class ConsumidorSemaforo implements Runnable {
    private final BufferSemaforo buffer;
    private final Random rand = new Random();

    public ConsumidorSemaforo(BufferSemaforo buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int item = ThreadLocalRandom.current().nextInt(100);
                // 'item' é consumido (neste caso, apenas impresso pelo buffer)
                
                // Simula o tempo de consumo
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumidor interrompido.");
        }
    }
}