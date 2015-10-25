package com.lbadvisor.AgeGroup.bean;

/**
 * Created by likun on 15/10/22.
 */
public class GroupListInfo {

    //// TODO: 15/10/22 发现列表中的app名字和图标及是否免费是写死的，属性需替换成tools
    private int ratingbar;
    private String countdowntime;
    private String people;
    private String remainingAccount;
    private String remainingPeople;

    public int getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(int ratingbar) {
        this.ratingbar = ratingbar;
    }

    public String getCountdowntime() {
        return countdowntime;
    }

    public void setCountdowntime(String countdowntime) {
        this.countdowntime = countdowntime;
    }


    public GroupListInfo(int ratingbar, String people, String remainingAccount, String remainingPeople, String countdowntime) {
        this.ratingbar = ratingbar;
        this.people = people;
        this.remainingAccount = remainingAccount;
        this.remainingPeople = remainingPeople;
        this.countdowntime = countdowntime;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getRemainingAccount() {
        return remainingAccount;
    }

    public void setRemainingAccount(String remainingAccount) {
        this.remainingAccount = remainingAccount;
    }

    public String getRemainingPeople() {
        return remainingPeople;
    }

    public void setRemainingPeople(String remainingPeople) {
        this.remainingPeople = remainingPeople;
    }

}
