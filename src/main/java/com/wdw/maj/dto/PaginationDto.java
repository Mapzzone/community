package com.wdw.maj.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {
    private List<QuestionDto> questionDtos; //questiondto问题列表
    private boolean showPrevious;  //是否展示向前翻页
    private boolean showFirstpage;  //是否展示首页
    private boolean showEndPage;  //是否展示尾页
    private boolean showNext;  //是否展示向后翻页
    private Integer page;  //当前页码
    private List<Integer> pages = new ArrayList<>();  //可展示的一组页码
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否展示第一页
        if (pages.contains(1)) {
            showFirstpage = false;
        } else {
            showFirstpage = true;
        }

        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
