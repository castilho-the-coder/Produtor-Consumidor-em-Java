# Produtor-Consumidor em Java

Projeto didático que implementa o problema Produtor-Consumidor em Java por duas abordagens:

- Monitores (wait/notify) — código em `monitores/`
- Semáforos (`Semaphore`) — código em `semaforos/`

**Estrutura atual**

- `monitores/`
  - `BufferMonitor.java`
  - `ProdutorMonitor.java`
  - `ConsumidorMonitor.java`
  - `SimulacaoMonitor.java`
- `semaforos/`
  - `BufferSemaforo.java`
  - `ProdutorSemaforo.java`
  - `ConsumidorSemaforo.java`
  - `SimulacaoSemaforo.java`

## Compilação (Windows PowerShell)

1. Compile todas as fontes:

```powershell
cd c:\Users\marce\Produtor-Consumidor-em-Java-1
javac monitores\*.java semaforos\*.java
```

2. Executar simulação com Monitores (exemplo: capacidade=5, 1 produtor, 2 consumidores, 10s):

```powershell
java -cp monitores SimulacaoMonitor 5 1 2 10
```

3. Executar simulação com Semáforos (exemplo similar):

```powershell
java -cp semaforos SimulacaoSemaforo 5 1 2 10
```

## Comandos úteis

- Compilar tudo:

```powershell
javac monitores\*.java semaforos\*.java
```

- Rodar Monitores (ex.: 10 segundos):

```powershell
java -cp monitores SimulacaoMonitor 5 1 2 10
```

- Rodar Semáforos (ex.: 10 segundos):

```powershell
java -cp semaforos SimulacaoSemaforo 5 1 2 10
```