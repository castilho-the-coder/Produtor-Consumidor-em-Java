import java.util.concurrent.ThreadLocalRandom;

/**
 * Thread (Runnable) do Consumidor (correção).
 */
public class ConsumidorSemaforo implements Runnable {
    private final BufferSemaforo buffer;

    public ConsumidorSemaforo(BufferSemaforo buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Solicita um item do buffer (bloqueante)
                int item = buffer.consumir();

                // Processa o item (aqui apenas imprime)
                System.out.println(Thread.currentThread().getName() + " consumiu: " + item);

                // Simula o tempo de consumo
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrompido.");
        }
    }
}
