/**
 * Classe principal para iniciar a simulação com Semáforos.
 */
public class SimulacaoSemaforo {

    public static void main(String[] args) {
        int TAMANHO_BUFFER = 5;
        
        BufferSemaforo buffer = new BufferSemaforo(TAMANHO_BUFFER);

        // Cria e inicia as threads
        Thread produtor1 = new Thread(new ProdutorSemaforo(buffer), "Produtor-1");
        Thread consumidor1 = new Thread(new ConsumidorSemaforo(buffer), "Consumidor-1");
        Thread consumidor2 = new Thread(new ConsumidorSemaforo(buffer), "Consumidor-2");

        System.out.println("Iniciando simulação com Semáforos...");
        
        produtor1.start();
        consumidor1.start();
        consumidor2.start();
    }
}