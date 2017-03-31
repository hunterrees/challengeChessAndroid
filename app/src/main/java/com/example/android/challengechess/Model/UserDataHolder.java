package com.example.android.challengechess.Model;

/**
 * Created by seth on 3/8/2017.
 */

public class UserDataHolder {
    private static UserDataHolder instance;
    String username;
    String password;

    public int getChosenColor() {
        return chosenColor;
    }

    public void setChosenColor(int chosenColor) {
        this.chosenColor = chosenColor;
    }

    public int getChosenType() {
        return chosenType;
    }

    public void setChosenType(int chosenType) {
        this.chosenType = chosenType;
        System.out.print("The player has chosen " + chosenType);
    }

    int chosenColor;
    int chosenType = 3;
    public static UserDataHolder getInstance()
    {
        if (instance == null)
        {
            instance = new UserDataHolder();
        }
        return instance;
    }
    private UserDataHolder()
    {}

    public void setLoginInfo(String username, String password)
    {
        this.username = username;
        this.password = password;
    }




}
