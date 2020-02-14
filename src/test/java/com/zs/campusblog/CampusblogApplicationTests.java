package com.zs.campusblog;

import com.zs.campusblog.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampusblogApplicationTests {

    @Autowired
    TagService tagService;

    @Test
    public void test() {
        System.out.println("\n\n\n" + "hello Valentine's Day");

    }
}
