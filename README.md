# Clean-architecture example Backend

Projeto backend em Java utilizando Spring Boot, Gradle, Apache Kafka e Cassandra.

## Requisitos

- Java 17 ou superior
- Gradle 7+
- Docker e Docker Compose

## Configuração do Ambiente

1. Clone o repositório:
   ```
   git clone <URL_DO_REPOSITORIO>
   cd clean-architecture
   ```

2. Suba os serviços necessários (Kafka, Zookeeper, Cassandra):
   ```
   docker-compose up -d
   ```

3. Configure o arquivo `src/main/resources/application.properties` conforme necessário, especialmente as propriedades do Kafka e Cassandra. Exemplo:
   ```properties
   spring.kafka.bootstrap-servers=host.docker.internal:29092
   spring.data.cassandra.contact-points=host.docker.internal:9042
   ```

## Build e Execução

Para compilar o projeto:
```
./gradlew build
```

Para rodar a aplicação:
```
./gradlew bootRun
```

## Testes

Execute os testes automatizados:
```
./gradlew test
```

## Estrutura do Projeto

- `src/main/java/com/example` - Código fonte principal
- `src/test/java/com/example` - Testes automatizados
- `docker-compose.yml` - Orquestração dos serviços de infraestrutura
- `build.gradle` - Configuração do build

## Observações

- Certifique-se de que o Kafka e o Cassandra estão rodando e acessíveis nas portas configuradas.
- Em caso de erro de conexão, revise as configurações de rede e portas.
- Para desenvolvimento local, pode ser necessário ajustar o host para `host.docker.internal` ou `localhost` conforme o SO.

## Licença

Este projeto está sob a licença MIT.

