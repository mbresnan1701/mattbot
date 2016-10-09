package com.company;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

  public static void main(String... args) throws TwitterException{
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
            .setOAuthConsumerKey("rUzpUEF8Yp7YBp4hJ7Gh6xxky")
            .setOAuthConsumerSecret(System.getenv("consumer_secret"))
            .setOAuthAccessToken("785150961002041344-jn45ghk2LHgEzRKuigpPoJWh9eBVP13")
            .setOAuthAccessTokenSecret(System.getenv("token_secret"));
    TwitterFactory tf = new TwitterFactory(cb.build());
    Twitter twitter = tf.getInstance();

    //send a tweet
    Status status = twitter.updateStatus("Have you ever been so that you accidentally the whole thing?");

    //print a message so we know when it finishes
    System.out.println("Done.");
  }
}
