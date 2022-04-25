package ru.tilipod.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.tilipod.feign.api.ParserApi;

@FeignClient(value = "${feign.client.config.parser.name}")
public interface ParserFeignClient extends ParserApi {
}
