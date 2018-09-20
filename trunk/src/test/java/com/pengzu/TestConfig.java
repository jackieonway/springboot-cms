package com.pengzu;

import com.pengzu.dao.PengzuConfigDao;
import com.pengzu.entity.PengzuConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestConfig {

    @Autowired
    private PengzuConfigDao configDao;

    @Test
    public void testConfig(){
        PengzuConfig pengzuConfig = new PengzuConfig();
        pengzuConfig = configDao.selectByPrimaryKey("logo");
        System.out.println(pengzuConfig.toString());


    }
}
