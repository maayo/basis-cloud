package com.basis.cloud.controller;

import com.basis.cloud.api.DemoService;
import com.basis.cloud.dto.TestReq;
import com.basis.cloud.service.DemoServices;
import lombok.extern.slf4j.Slf4j;
import net.easipay.support.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author jia.yang
 * @version 1.0
 * @date 2021/3/17 11:30
 */
@Slf4j
@RestController
public class DemoController implements DemoService {

    @Autowired
    private DemoServices demoServices;


    @Override
    public Response<String> queryFrontInfo(@Valid TestReq request) {
        demoServices.test(request);
        return null;
    }
}
