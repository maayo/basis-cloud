package com.basis.cloud.service.impl;

import com.basis.cloud.dto.TestReq;
import com.basis.cloud.service.DemoServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jia.yang
 * @version 1.0
 * @date 2021/3/17 11:32
 */
@Slf4j
@Service
public class DemoServicesImpl implements DemoServices {

    @Override
    public void test(TestReq req) {
       log.info("1");
    }
}
