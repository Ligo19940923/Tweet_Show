package com.twitter.tweetshow.mapper;

import com.twitter.tweetshow.model.Tweet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TweetMapper {
    int deleteByPrimaryKey(String id);

    int insert(Tweet record);

    int insertSelective(Tweet record);

    Tweet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tweet record);

    int updateByPrimaryKey(Tweet record);

    List<Tweet> selectAllTweets();

    List<Tweet> findLatestTweets(String id);

    List<Tweet> findByKeywords(@Param("keyword") String keyword, @Param("id") String id);
}