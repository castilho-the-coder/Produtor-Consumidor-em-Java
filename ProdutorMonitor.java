import java.util.Random;

/**
 * Thread (Runnable) do Produtor (para a versão Monitor).
 */
public class ProdutorMonitor implements Runnable {
    private final BufferMonitor buffer;
    private final Random rand = new Random();

    public ProdutorMonitor(BufferMonitor buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = rand.nextInt(100);
                buffer.produzir(item);
                Thread.sleep(rand.nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Produtor (Monitor) interrompido.");
        }
    }
}