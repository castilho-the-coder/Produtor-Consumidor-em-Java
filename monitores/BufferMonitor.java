import java.util.LinkedList;
import java.util.Queue;

/**
 * O Buffer (recurso compartilhado) implementado como um Monitor.
 * A própria classe gerencia a sincronização.
 */
public class BufferMonitor {

    private final Queue<Integer> fila = new LinkedList<>();
    private final int tamanhoMaximo;

    public BufferMonitor(int tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
    }

    /**
     * Método a ser chamado pelo Produtor.
     * 'synchronized' garante que apenas uma thread execute por vez.
     */
    public synchronized void produzir(int item) throws InterruptedException {
        // 1. Se o buffer está cheio, espera.
        // (Usamos 'while' para re-verificar a condição se for acordado)
        while (fila.size() == tamanhoMaximo) {
            System.out.println("Buffer CHEIO. Produtor esperando...");
            wait(); // Libera o lock e espera
        }
        
        // 2. Adiciona o item
        fila.add(item);
        System.out.println("Produzido: " + item + " (Tamanho: " + fila.size() + ")");

        // 3. Notifica todos (acorda consumidores)
        notifyAll();
    }

    /**
     * Método a ser chamado pelo Consumidor.
     * 'synchronized' garante que apenas uma thread execute por vez.
     */
    public synchronized int consumir() throws InterruptedException {
        // 1. Se o buffer está vazio, espera.
        while (fila.isEmpty()) {
            System.out.println("Buffer VAZIO. Consumidor esperando...");
            wait(); // Libera o lock e espera
        }

        // 2. Remove o item
        int item = fila.poll();
        System.out.println("Consumido: " + item + " (Tamanho: " + fila.size() + ")");

        // 3. Notifica todos (acorda produtores)
        notifyAll();
        
        return item;
    }
}
