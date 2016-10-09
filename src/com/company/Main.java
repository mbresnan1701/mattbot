package com.company;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

  public static void main(String... args) throws TwitterException {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
            .setOAuthConsumerKey("rUzpUEF8Yp7YBp4hJ7Gh6xxky")
            .setOAuthConsumerSecret(System.getenv("consumer_secret"))
            .setOAuthAccessToken("785150961002041344-jn45ghk2LHgEzRKuigpPoJWh9eBVP13")
            .setOAuthAccessTokenSecret(System.getenv("token_secret"));
    TwitterFactory tf = new TwitterFactory(cb.build());
    Twitter twitter = tf.getInstance();


//    Scanner sc = new Scanner(new File("tweets.txt"));
    ArrayList<String> upcoming = new ArrayList<String>();
    try {
      File tweetfile = new File("/Users/mbresnan/Development/Java/tweets.txt");

      Scanner input = new Scanner(tweetfile);

      while (input.hasNextLine()) {
        upcoming.add(input.nextLine());
      }
      input.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    int tweeted = 0;
    while(tweeted < upcoming.size()) {
      Status status = twitter.updateStatus(upcoming.get(tweeted));
      //if tweet success
      System.out.println("Tweet successful");
      tweeted++;
      try {
        System.out.println("Sleeping...");
        Thread.sleep(1000 * 60 * 15);
        System.out.println("Done sleeping, no interrupt.");
      } catch (InterruptedException e) {
        System.out.println("I was interrupted!");
        e.printStackTrace();
      }
    }

    //print a message so we know when it finishes
    System.out.println("All messages have been tweeted. Time to add more!");
  }
}
