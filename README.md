# executor-chain

Utilitário para realizar execução encadeadas

### Etapas de execução da chain

A execução da chain possui algumas etapas

- **beforeAll** - executa antes de todas as etapas apenas uma vez.
- **beforeEach** - executa antes de cada execução de chain.
- **afterEach** - executa após de cada execução de chain.
- **afterAll** - executa após de todas as etapas apenas uma vez.
- **chain** - executa a chain.
- **errorHandler** - etapa para fazer as tratativas de erros, apenas em caso de exceções.

A chain basicamente é implementação da interface **Executor** que utilizar como entrada/saída o mesmo objeto, no exemplo abaixo a classe **Notification**

### Exemplo

Exemplo de implementação de uma chain

```java
public class LoadNotification implements Executor<Notification> {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification execute(Notification input) {
        if (Objects.nonNull(input.getId())) {
            var notification = notificationRepository.findById(input.getId());
            input.setTitle(notification.getTitle());
            input.setContent(notification.getContent());
            input.setState(notification.getState());
            input.setAttempts(notification.getAttempts());
        }
        return input;
    }
}
```

Exemplo de utilização e execução encadeada

```java
public class SendNotificationImpl implements SendNotification {

    public void process(Notification notification) {
        var operation = ExecutorChain.<Notification>builder()
                .errorHandler(errorHandler::execute)
                .beforeAll(validateNotification::execute)
                .chain(checkToggleNotificationEnable)
                .chain(publishNotification)
                .afterAll(saveNotification::execute)
                .build();
        operation.execute(notification);
    }
}
```

### Benefícios

- Podemos dividir a implementação de uma funcionalidade em várias etapas diminuindo a complexidade do código.
- Se adicionarmos um estado de máquina em cada etapa podemos obter uma estrutura que permite retentativas baseado no estado anterior da execução.