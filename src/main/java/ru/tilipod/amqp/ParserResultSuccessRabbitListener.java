package ru.tilipod.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.amqp.message.ParserResultSuccessMessage;
import ru.tilipod.jpa.entity.nneas.NeuronNetwork;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;
import ru.tilipod.service.NeuronNetworkService;
import ru.tilipod.service.TaskService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParserResultSuccessRabbitListener {

    private final ObjectMapper objectMapper;

    private final NeuronNetworkService neuronNetworkService;

    private final TaskService taskService;

    @RabbitListener(queues = "${queues.parserResultSuccess}")
    @Transactional
    public void worker(ParserResultSuccessMessage message) {
        log.info("Получено сообщение об успешном анализе задачи {}", message.getTaskId());

        Task task = taskService.findById(message.getTaskId());
        if (task == null) {
            log.warn("Задача с id = {} не найдена в БД. Игнорируем сообщение об успешном анализе", message.getTaskId());
            return;
        }

        if (!TaskStatusEnum.ANALYSIS.equals(task.getStatus())) {
            log.warn("Задача с id = {} находится в статусе {}. Игнорируем сообщение об успешном анализе", task.getId(), task.getStatus());
            return;
        }

        NeuronNetwork nn = neuronNetworkService.findByTaskId(message.getTaskId());
        if (message.getConf() != null) {
            try {
                nn.setJsonRlConfStructure(objectMapper.writeValueAsString(message.getConf()));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
                return;
            }
            neuronNetworkService.updateNetwork(nn);
        }

        taskService.changeStatus(task, TaskStatusEnum.ANALYZED, "Ожидает выгрузки датасетов");
    }

}
