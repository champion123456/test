package com.many.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.many.reader.entity.Book;

/**
 * 图书服务
 */
public interface BookService {
    /**
     * 分页查询图书
     * @param page 分页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows);

    /**
     * 根据图书编号查询图书对象
     * @param bookId 图书编号
     * @return 图书对象
     */
    public Book selectById(Long bookId);

    /**
     * 更新图书评分/评价数量
     */
    public void updateEvaluation();

    /**
     *  创建新的图书
     * @param book
     * @return
     */
    public Book createBook(Book book);

    /**
     *  更新图书
     * @param book 图书信息表单
     * @return 新的图书数据
     */
    public Book updateBook(Book book);

    /**
     *  删除图书信息
     * @param bookId
     */
    public void deleteBook(Long bookId);
}
