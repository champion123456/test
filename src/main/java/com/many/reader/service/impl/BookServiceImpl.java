package com.many.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.many.reader.entity.Book;
import com.many.reader.entity.Enjoy;
import com.many.reader.entity.Evaluation;
import com.many.reader.entity.MemberReadState;
import com.many.reader.mapper.BookMapper;
import com.many.reader.mapper.EnjoyMapper;
import com.many.reader.mapper.EvaluationMapper;
import com.many.reader.mapper.MemberReadStateMapper;
import com.many.reader.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("bookService")
public class BookServiceImpl implements BookService {
    /**
     * 分页查询图书
     *
     * @param page 分页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    @Resource
    private BookMapper bookMapper;

    @Resource
    private MemberReadStateMapper memberReadStateMapper;

    @Resource
    private EvaluationMapper evaluationMapper;

    @Resource
    private EnjoyMapper enjoyMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> p = new Page<>(page, rows);
        //条件构造器
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if(categoryId != null && categoryId != -1){
            queryWrapper.eq("category_id", categoryId);
        }
        if(order != null){
            if(order.equals("quantity")){
                //按评价数量降序排列
                queryWrapper.orderByDesc("evaluation_quantity");
            }else if(order.equals("score")){
                //按评分降序排列
                queryWrapper.orderByDesc("evaluation_score");
            }
        }
        Page<Book> pageObject = bookMapper.selectPage(p, queryWrapper);
        return pageObject;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Book selectById(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        return book;
    }

    /**
     * 更新图书评分/评价数量
     */
    @Transactional
    public void updateEvaluation() {
        bookMapper.updateEvaluation();
    }

    /**
     * 创建新的图书
     *
     * @param book
     * @return
     */
    @Transactional
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    /**
     * 更新图书
     *
     * @param book 图书信息表单
     * @return 新的图书数据
     */
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    /**
     * 删除图书信息
     *
     * @param bookId
     */
    public void deleteBook(Long bookId) {
        bookMapper.deleteById(bookId);
        /*//删除与该图书相关的阅读状态
        QueryWrapper<MemberReadState> mrsqueryWrapper = new QueryWrapper<MemberReadState>();
        mrsqueryWrapper.eq("book_id", bookId);
        memberReadStateMapper.deleteById(mrsqueryWrapper);*/
        QueryWrapper<MemberReadState> mrsQueryWrapper = new QueryWrapper<MemberReadState>();
        mrsQueryWrapper.eq("book_id", bookId);
        memberReadStateMapper.delete(mrsQueryWrapper);
        //删除该图书的评论及点赞
        QueryWrapper<Evaluation> evaqueryWrapper = new QueryWrapper<Evaluation>();
        evaqueryWrapper.eq("book_id", bookId);
        evaluationMapper.delete(evaqueryWrapper);
        QueryWrapper<Enjoy> equeryWrapper = new QueryWrapper<Enjoy>();
        equeryWrapper.eq("book_id", bookId);
        enjoyMapper.delete(equeryWrapper);
    }
}
