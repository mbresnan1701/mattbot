package com.company;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mbresnan on 10/12/16.
 */

class Tweeter {
  private ArrayList<String> upcoming;
  private Twitter twitter;
  private String tweetDir;

  public Tweeter(Twitter t, String dir) {
    upcoming = new ArrayList<String>();
    twitter = t;
    tweetDir = dir;
  }

  public void startTweeting() throws TwitterException {
    try {
      System.out.println(tweetDir);
      File tweetfile = new File(tweetDir);

      Scanner input = new Scanner(tweetfile);

      while (input.hasNextLine()) {
        upcoming.add(input.nextLine());
      }
      input.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    int tweeted = 0;
    while (true) {
      Status status = twitter.updateStatus(upcoming.get(tweeted));
      //if tweet success
      System.out.println("Tweet successful");
      tweeted++;
      if (tweeted == upcoming.size()) {
        break;
      }
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
