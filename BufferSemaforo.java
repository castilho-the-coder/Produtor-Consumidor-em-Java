import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * O Buffer (recurso compartilhado) implementado com Semáforos.
 */
public class BufferSemaforo {

    private final Queue<Integer> fila = new LinkedList<>();
    private final int tamanhoMaximo;
    
    // Semáforo para exclusão mútua (lock)
    private final Semaphore mutex; 
    
    // Semáforo 'empty' conta os espaços vazios (inicializado com o tamanho total)
    private final Semaphore empty;
    
    // Semáforo 'full' conta os espaços ocupados (inicializado com 0)
    private final Semaphore full;

    public BufferSemaforo(int tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
        this.mutex = new Semaphore(1, true); // Lock binário
        this.empty = new Semaphore(tamanhoMaximo); // Começa com N espaços vazios
        this.full = new Semaphore(0); // Começa com 0 itens
    }

    /**
     * Método a ser chamado pelo Produtor.
     */
    public void produzir(int item) throws InterruptedException {
        // 1. Espera por um espaço vazio (decrementa 'empty')
        empty.acquire(); // Se 'empty' == 0, bloqueia
        
        // 2. Entra na região crítica (trava o mutex)
        mutex.acquire();
        try {
            fila.add(item);
            System.out.println("Produzido: " + item + " (Tamanho: " + fila.size() + ")");
        } finally {
            // 3. Sai da região crítica (libera o mutex)
            mutex.release();
        }
        
        // 4. Sinaliza que há um novo item (incrementa 'full')
        full.release();
    }

    /**
     * Método a ser chamado pelo Consumidor.
     */
    public int consumir() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        try {
            int item = fila.poll();
            System.out.println("Consumido: " + item + " (Tamanho: " + fila.size() + ")");
            // sinaliza espaço vazio antes de sair
            empty.release();
            return item;
        } finally {
            mutex.release();
        }
    }
}