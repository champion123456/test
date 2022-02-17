package com.many.reader.service.impl;

import com.many.reader.entity.Category;
import com.many.reader.service.CategoryService;
import com.many.reader.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CategoryServiceImplTest extends TestService {
    @Resource
    private CategoryService categoryService;

    /**
     * 查看所有图书分类
     */
    @Test
    public void selectAll() {
        List<Category> list = categoryService.selectAll();
        System.out.println(list);
    }
}