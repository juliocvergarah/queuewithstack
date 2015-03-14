Implementations of Queues using Stack
- Make sure you have a Java 7 compiler installed
- Run simply to build and install
  - mvn clean install

- Three implementations are implemented:
  - CostOnDeQueueQueue - fast
  - DirectAccessQueue - fast
  - CostOnEnQueueQueue - slow

- Additionally, a thread-safe wrapper is provided for using in a multi-threaded environment:
  - SynchronizedGenericQueueWrapper  