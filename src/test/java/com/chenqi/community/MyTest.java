package com.chenqi.community;

import com.chenqi.community.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {CommunityApplication.class})
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private UserService userService;


    @Test
    public void test(){
        userService.findAllUser().forEach(user -> {
            System.out.println(user);
        });
    }

}
