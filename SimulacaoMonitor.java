/**
 * Classe principal para iniciar a simulação com Monitores.
 */
public class SimulacaoMonitor {

    public static void main(String[] args) {
        int TAMANHO_BUFFER = 5;
        
        BufferMonitor buffer = new BufferMonitor(TAMANHO_BUFFER);

        // Cria e inicia as threads
        Thread produtor1 = new Thread(new ProdutorMonitor(buffer), "Produtor-1");
        Thread consumidor1 = new Thread(new ConsumidorMonitor(buffer), "Consumidor-1");
        Thread consumidor2 = new Thread(new ConsumidorMonitor(buffer), "Consumidor-2");

        System.out.println("Iniciando simulação com Monitores (wait/notify)...");
        
        produtor1.start();
        consumidor1.start();
        consumidor2.start();
    }
}