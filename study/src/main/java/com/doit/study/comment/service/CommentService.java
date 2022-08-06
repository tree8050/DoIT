package com.doit.study.comment.service;

import com.doit.study.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {
    public int getCount(int board_Id) throws Exception;
    public int remove(int comment_Id, int board_Id, String comment_Writer) throws Exception;
    public int removeAll(int board_Id) throws Exception;
    public int write(CommentDto commentDto) throws Exception;
    public List<CommentDto> getList(int board_Id) throws Exception;
    public CommentDto read(int comment_Id) throws Exception;
    public int modify(CommentDto commentDto) throws Exception;
}
