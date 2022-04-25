package ru.tilipod.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import ru.tilipod.util.ExchangeNames;
import ru.tilipod.util.RoutingKeys;

@Configuration
@EnableRabbit
@EnableConfigurationProperties(RabbitProperties.class)
public class AmqpConfig implements RabbitListenerConfigurer {

    @Value("${queues.parserResultSuccess}")
    private String parserResultSuccessQueueName;

    @Value("${queues.parserResultError}")
    private String parserResultErrorQueueName;

    @Value("${queues.distributorResultSuccess}")
    private String distributorResultSuccessQueueName;

    @Value("${queues.distributorResultError}")
    private String distributorResultErrorQueueName;

    @Value("${queues.teacherResultSuccess}")
    private String teacherResultSuccessQueueName;

    @Value("${queues.teacherResultError}")
    private String teacherResultErrorQueueName;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(mappingJackson2MessageConverter());

        return factory;
    }

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMessagingTemplate messagingTemplate = new RabbitMessagingTemplate();
        messagingTemplate.setRabbitTemplate(rabbitTemplate);
        messagingTemplate.setMessageConverter(mappingJackson2MessageConverter());

        return messagingTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new Jackson2JsonMessageConverter(objectMapper);
    }

    private MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return converter;
    }

    @Bean
    public Exchange exchangeSuccess() {
        return new DirectExchange(ExchangeNames.SUCCESS);
    }

    @Bean
    public Exchange exchangeError() {
        return new DirectExchange(ExchangeNames.ERROR);
    }

    @Bean
    Queue parserResultSuccessQueue() {
        return new Queue(parserResultSuccessQueueName);
    }

    @Bean
    Queue parserResultErrorQueue() {
        return new Queue(parserResultErrorQueueName);
    }

    @Bean
    Queue distributorResultSuccessQueue() {
        return new Queue(distributorResultSuccessQueueName);
    }

    @Bean
    Queue distributorResultErrorQueue() {
        return new Queue(distributorResultErrorQueueName);
    }

    @Bean
    Queue teacherResultSuccessQueue() {
        return new Queue(teacherResultSuccessQueueName);
    }

    @Bean
    Queue teacherResultErrorQueue() {
        return new Queue(teacherResultErrorQueueName);
    }

    @Bean
    public Binding parserResultSuccessQueueToExchangeSuccessBinding() {
        return BindingBuilder.bind(parserResultSuccessQueue())
                .to(exchangeSuccess())
                .with(RoutingKeys.PARSER_RESULT_KEY)
                .noargs();
    }

    @Bean
    public Binding parserResultErrorQueueToExchangeErrorBinding() {
        return BindingBuilder.bind(parserResultErrorQueue())
                .to(exchangeError())
                .with(RoutingKeys.PARSER_RESULT_KEY)
                .noargs();
    }

    @Bean
    public Binding distributorResultSuccessQueueToExchangeDistributorBinding() {
        return BindingBuilder.bind(distributorResultSuccessQueue())
                .to(exchangeSuccess())
                .with(RoutingKeys.DISTRIBUTOR_RESULT_KEY)
                .noargs();
    }

    @Bean
    public Binding distributorResultErrorQueueToExchangeDistributorBinding() {
        return BindingBuilder.bind(distributorResultErrorQueue())
                .to(exchangeError())
                .with(RoutingKeys.DISTRIBUTOR_RESULT_KEY)
                .noargs();
    }

    @Bean
    public Binding teacherResultSuccessQueueToExchangeSuccessBinding() {
        return BindingBuilder.bind(teacherResultSuccessQueue())
                .to(exchangeSuccess())
                .with(RoutingKeys.TEACHER_RESULT_KEY)
                .noargs();
    }

    @Bean
    public Binding teacherResultErrorQueueToExchangeErrorBinding() {
        return BindingBuilder.bind(teacherResultErrorQueue())
                .to(exchangeError())
                .with(RoutingKeys.TEACHER_RESULT_KEY)
                .noargs();
    }

}
