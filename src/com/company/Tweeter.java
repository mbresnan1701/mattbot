package com.company;

/**
 * Created by mbresnan on 10/12/16.
 */

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.File;
import java.util.*;



class Tweeter implements Runnable {
  private ArrayList<String> upcoming;
  private Twitter twitter;
  private String tweetDir;
  private int sleepTime;

  Tweeter(Twitter t, String dir, int min) {
    upcoming = new ArrayList<String>();
    twitter = t;
    tweetDir = dir;
    sleepTime = min;
  }

  @Override
  public void run() {
    try {
      startTweeting();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void startTweeting() throws TwitterException {
    try {
      System.out.println(tweetDir);
      File tweetfile = new File(tweetDir);

      Scanner input = new Scanner(tweetfile);

      while (input.hasNextLine()) {
        upcoming.add(input.nextLine());
      }
      input.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    int tweeted = 0;
    while (true) {
      Status status = twitter.updateStatus(upcoming.get(tweeted));
      //if tweet success
      tweeted++;
      System.out.printf("Tweet %d/%d successful\n", tweeted, upcoming.size());

      if (tweeted == upcoming.size()) {
        break;
      }
      try {
        System.out.println("Tweeter sleeping...");
        Thread.sleep(1000 * 60 * sleepTime);
        System.out.println("Tweeter woke up. Back to work. ");
      } catch (InterruptedException e) {
        System.out.println("I was interrupted!");
        e.printStackTrace();
      }
    }

    System.out.println("All messages have been tweeted. Time to add more!");
  }


}
