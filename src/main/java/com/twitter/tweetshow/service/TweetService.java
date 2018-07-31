package com.twitter.tweetshow.service;

import com.twitter.tweetshow.model.Tweet;

import java.util.List;

public interface TweetService {

    List<Tweet> findAllTweets();

    List<Tweet> getLatestTweets(String id);

    long changeKeywords(long threadId,String keyWords);

    List<Tweet> findTweetsByKeywords(String keyword,String id);

}
