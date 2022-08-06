package com.doit.study.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int comment_Id;
    private int board_Id;
    private int parentComment_Id;
    @NotEmpty
    private String  comment_Content;
    private String  comment_Writer;
    private Date    reg_Date;
    private Date    update_Date;
}
