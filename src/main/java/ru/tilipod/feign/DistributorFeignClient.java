package ru.tilipod.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.tilipod.feign.api.DistributorApi;

@FeignClient(value = "${feign.client.config.distributor.name}")
public interface DistributorFeignClient extends DistributorApi {
}
