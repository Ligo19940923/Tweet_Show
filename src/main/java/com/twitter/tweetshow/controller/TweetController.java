package com.twitter.tweetshow.controller;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.twitter.tweetshow.model.Tweet;
import com.twitter.tweetshow.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class TweetController {
    @Autowired
    private TweetService tweetService;


    @RequestMapping(value = "/", method = RequestMethod.GET)

    public String tweetList(Model model){
        List<Tweet> tweets = tweetService.findAllTweets();
        model.addAttribute("tweets",tweets);
        return "/index";
    }

    @RequestMapping(value = "/update/{keyword}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray updateTweet(Model model, @PathVariable("keyword") String keyword,@PathVariable("id") String id){
//        List<Tweet> tweets = tweetService.getLatestTweets(id);
        List<Tweet> tweets = tweetService.findTweetsByKeywords(keyword,id);

        JSONArray tweetJson = JSONArray.fromObject(tweets);
        return tweetJson;
    }

    @RequestMapping(value = "/changeKeywords", method = RequestMethod.POST)
    @ResponseBody
    public long changeKeywords(Model model,String keywords,String threadId){
        long tId = Long.parseLong(threadId);
        long newId = tweetService.changeKeywords(tId,keywords);

        return newId;
    }
}
