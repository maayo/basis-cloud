package com.basis.cloud.api;

import com.basis.cloud.dto.RestResponse;
import com.basis.cloud.dto.TestReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author jia.yang
 * @version 1.0
 * @date 2021/3/17 11:26
 */
@FeignClient("basis-cloud")
public interface DemoService {

    @PostMapping(value = "/queryFrontInfo")
    RestResponse<String> queryFrontInfo(@RequestBody @Valid TestReq request);

}
