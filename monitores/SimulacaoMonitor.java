import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe principal para iniciar a simulação com Monitores.
 * Aceita argumentos opcionais: <capacidade> <numProdutores> <numConsumidores> <duracaoSegundos>
 */
public class SimulacaoMonitor {

    public static void main(String[] args) throws InterruptedException {
        int capacidade = 5;
        int numProdutores = 1;
        int numConsumidores = 2;
        int duracaoSegundos = 0; // 0 => roda indefinidamente

        if (args.length >= 1) capacidade = Integer.parseInt(args[0]);
        if (args.length >= 2) numProdutores = Integer.parseInt(args[1]);
        if (args.length >= 3) numConsumidores = Integer.parseInt(args[2]);
        if (args.length >= 4) duracaoSegundos = Integer.parseInt(args[3]);

        BufferMonitor buffer = new BufferMonitor(capacidade);

        ExecutorService exec = Executors.newFixedThreadPool(numProdutores + numConsumidores);

        for (int i = 1; i <= numProdutores; i++) {
            exec.submit(new ProdutorMonitor(buffer));
        }
        for (int i = 1; i <= numConsumidores; i++) {
            exec.submit(new ConsumidorMonitor(buffer));
        }

        System.out.println("Iniciando simulação com Monitores (wait/notify)...");

        if (duracaoSegundos > 0) {
            final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
            final AtomicInteger remaining = new AtomicInteger(duracaoSegundos);

            // Imprime tempo restante a cada segundo
            timer.scheduleAtFixedRate(() -> {
                int r = remaining.getAndDecrement();
                if (r > 0) {
                    System.out.println("Tempo restante: " + r + "s");
                } else {
                    timer.shutdown();
                }
            }, 0, 1, TimeUnit.SECONDS);

            Thread.sleep(duracaoSegundos * 1000L);
            System.out.println("Tempo esgotado: solicitando shutdown das threads...");
            exec.shutdownNow();
            exec.awaitTermination(5, TimeUnit.SECONDS);
            timer.shutdownNow();
            System.out.println("Simulação finalizada.");
        }
    }
}
