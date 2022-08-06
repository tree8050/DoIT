package com.doit.study.wishlist.service;

import com.doit.study.mapper.WishListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{

    private final WishListMapper wishListMapper;

    @Override
    public Integer getWishId(String study_id, String user_id) {
        return wishListMapper.checkHeart(study_id, user_id);
    }
}
