package com.twitter.tweetshow.service.impl;

import com.twitter.tweetshow.mapper.TweetMapper;
import com.twitter.tweetshow.model.Tweet;
import com.twitter.tweetshow.service.TweetService;
import com.twitter.tweetshow.service.python.PythonRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tweetService")
public class TweetServiceImpl implements TweetService{
    @Autowired
    private TweetMapper tweetMapper;

    @Override
    public List<Tweet> findAllTweets(){
        return tweetMapper.selectAllTweets();
    }

    @Override
    public List<Tweet> getLatestTweets(String id) {return tweetMapper.findLatestTweets(id);}

    @Override
    public long changeKeywords(long threadId,String keywords){
        Thread tKeywords = null;
        for(Thread t : Thread.getAllStackTraces().keySet()){
            if(t.getId() == threadId){
                tKeywords = t;
            }
        }
        PythonRun newTKeywords = new PythonRun();
        newTKeywords.setKeyWords(keywords);
        long id = newTKeywords.getId();
        if(tKeywords != null) {
            tKeywords.stop();
        }

        newTKeywords.start();
        return id;

    }

    @Override
    public List<Tweet> findTweetsByKeywords(String keyword,String id){
        keyword = "%"+keyword+"%";
        return tweetMapper.findByKeywords(keyword,id);
    }
}
