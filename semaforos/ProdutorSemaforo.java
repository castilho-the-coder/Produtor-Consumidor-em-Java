import java.util.concurrent.ThreadLocalRandom;

/**
 * Thread (Runnable) do Produtor.
 */
public class ProdutorSemaforo implements Runnable {
    private final BufferSemaforo buffer;

    public ProdutorSemaforo(BufferSemaforo buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int item = ThreadLocalRandom.current().nextInt(100); // Produz um item (um nº aleatório)
                buffer.produzir(item);
                
                // Simula o tempo de produção
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // logger.info("Produtor interrompido.");
        }
    }
}
