package com.doit.study.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WishListMapper {

    @Select(WishListSQL.checkHeart)
    Integer checkHeart(@Param("study_id") String study_id,
                      @Param("user_id") String user_id);
}
