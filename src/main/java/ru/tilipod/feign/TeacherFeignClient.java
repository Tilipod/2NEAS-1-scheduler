package ru.tilipod.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.tilipod.feign.api.TeacherApi;

@FeignClient(value = "${feign.client.config.teacher.name}")
public interface TeacherFeignClient extends TeacherApi {
}
