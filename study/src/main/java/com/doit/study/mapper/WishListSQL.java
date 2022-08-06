package com.doit.study.mapper;

public class WishListSQL {


    public static final String checkHeart =
            "SELECT WISH_ID FROM WISH_TB WHERE STUDY_ID = #{study_id} AND USER_ID = #{user_id}";
}
