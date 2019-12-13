package com.winterchen;

import com.winterchen.tools.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot2MybatisDemoApplicationTests {

	@Test
	public void contextLoads() {
		String pw = "123";// 202cb962ac59075b964b07152d234b70
		String md5String = MD5.getMD5String(pw);
		System.out.println(md5String);
	}

}
