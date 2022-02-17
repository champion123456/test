package com.many.reader.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.many.reader.entity.Book;
import com.many.reader.service.BookService;
import com.many.reader.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest extends TestService {
    @Resource
    private BookService bookService;

    @Test
    public void paging() {
        IPage<Book> pageObject = bookService.paging(2l, "quantity", 2, 10);
        List<Book> records = pageObject.getRecords();
        for (Book b :
                records) {
            System.out.println(b.getBookId() + ":" + b.getBookName());
        }
    }
}