package com.basis.cloud.api;

import com.basis.cloud.dto.TestReq;
import net.easipay.support.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author jia.yang
 * @version 1.0
 * @date 2021/3/17 11:26
 */
@FeignClient("easipay-Cloud")
public interface DemoService {

    @PostMapping(value = "/queryFrontInfo")
    Response<String> queryFrontInfo(@RequestBody @Valid TestReq request);

}
