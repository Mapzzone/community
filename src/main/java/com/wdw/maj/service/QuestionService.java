package com.wdw.maj.service;


import com.wdw.maj.dto.PaginationDto;
import com.wdw.maj.dto.QuestionDto;
import com.wdw.maj.mapper.QuestionMapper;
import com.wdw.maj.mapper.UserMapper;
import com.wdw.maj.model.Question;
import com.wdw.maj.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDto getQuestionList(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = questionMapper.count();

        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page<1){
            page = 1;
        }

        if (page > totalPage){
            page = totalPage;
        }
        if (page<1){
            page = 1;
        }

        if (page > totalPage){
            page = totalPage;
        }
        paginationDto.setPagination(totalPage,page);

        Integer offset = size * (page-1);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> questionList = questionMapper.getQuestionList(offset,size);

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);

        }
        paginationDto.setQuestionDtos(questionDtoList);
        return paginationDto;
    }

    public PaginationDto getQuestionList(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();

        Integer totalPage;

        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page<1){
            page = 1;
        }

        if (page > totalPage){
            page = totalPage;
        }
        paginationDto.setPagination(totalPage,page);

        Integer offset = size * (page-1);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> questionList = questionMapper.getQuestionListById(userId,offset,size);

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestionDtos(questionDtoList);
        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertQuestion(question);
        }else{
            //更新
            question.setGmtModified(question.getGmtModified());
            questionMapper.update(question);
        }
    }
}
