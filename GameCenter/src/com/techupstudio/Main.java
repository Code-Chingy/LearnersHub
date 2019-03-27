package com.techupstudio;

import com.techupstudio.Base.Player;
import com.techupstudio.Games.WordFight;

import static com.techupstudio.Utils.MyFuncs.inputStr;
import static com.techupstudio.Utils.MyFuncs.println;

public class Main {


    public static void main(String[] args) {

        WordFight wordFight = new WordFight();

        wordFight.addPlayer(new Player("bernard"));
        wordFight.addPlayer(new Player("chris"));

        wordFight.start();

    }

}


