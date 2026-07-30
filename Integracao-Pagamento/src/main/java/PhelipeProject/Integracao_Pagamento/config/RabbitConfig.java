package PhelipeProject.Integracao_Pagamento.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${EMAIL_EXCHANGE}")
    private String EMAIL_EXCHANGE;

    @Value("${EMAIL_QUEUE_TRANSACTIONS}")
    public String EMAIL_QUEUE_TRANSACTIONS;

    @Value("${EMAIL_QUEUE_ACTIVE_ACCOUNT}")
    public String EMAIL_QUEUE_ACTIVE_ACCOUNT;

    @Value("${ROUTING_KEY_TRANSACTIONS}")
    private String ROUTING_KEY_TRANSACTIONS;

    @Value("${ROUTING_KEY_ACTIVE_ACCOUNT}")
    private String ROUTING_KEY_ACTIVE_ACCOUNT;

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(EMAIL_EXCHANGE);
    }

    @Bean
    public Queue queue_transactions() {
        return new Queue(EMAIL_QUEUE_TRANSACTIONS,true);
    }

    @Bean
    public Queue queue_active_account() {
        return new Queue(EMAIL_QUEUE_ACTIVE_ACCOUNT,true);
    }

    @Bean
    public Binding bindingTransactions(Queue queue_transactions,DirectExchange emailExchange) {
        return BindingBuilder.bind(queue_transactions)
                .to(emailExchange)
                .with(ROUTING_KEY_TRANSACTIONS);
    }

    @Bean
    public Binding bindingActive_account(Queue queue_active_account, DirectExchange emailExchange) {
        return BindingBuilder.bind(queue_active_account)
                .to(emailExchange)
                .with(ROUTING_KEY_ACTIVE_ACCOUNT);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);

        template.setMessageConverter(messageConverter);

        return template;
    }
}
