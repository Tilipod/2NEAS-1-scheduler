package ru.tilipod.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.ParserResultMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParserResultRabbitListener {

    @RabbitListener(queues = "${queues.parserResult}")
    @Transactional
    public void worker(ParserResultMessage message) {
        log.debug("Пришло сообщение: {}", message);
    }

}
