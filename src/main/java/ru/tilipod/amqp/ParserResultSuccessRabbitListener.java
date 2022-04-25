package ru.tilipod.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.ParserResultSuccessMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParserResultSuccessRabbitListener {

    @RabbitListener(queues = "${queues.parserResultSuccess}")
    @Transactional
    public void worker(ParserResultSuccessMessage message) {
        log.info("Пришло сообщение: {}", message);
    }

}
