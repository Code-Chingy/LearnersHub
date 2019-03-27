package com.techupstudio.Games;


import com.techupstudio.Base.*;
import com.techupstudio.Utils.MyFuncs;

import java.util.ArrayList;

import static com.techupstudio.Utils.MyFuncs.inputStr;
import static com.techupstudio.Utils.MyFuncs.println;

public class WordFight extends Game {

    int currentPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("######################################");
        System.out.println("##  Welcome to the Word Fight Game  ##");
        System.out.println("######################################\n");
    }

    public void onSave() {

    }

    @Override
    public void preparePlayers() {
        if (getPlayersCount() > 0){

            if (getPlayersCount() == 1){
                //add a computer player to play with user
                Player computer = new Player("ComputerPlayer");
                computer.setAsComputedPlayer();
                computer.setIncreaseHealthRate(1);
                computer.setIncreaseScoreRate(1);
                computer.setMaxHealth(100);
                addPlayer(computer);
            }
        }
        else{
            try {
                throw new GameException.NoPlayerException();
            } catch (GameException.NoPlayerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getPlayerPlayMove() {

        if (currentPlayer == 1) {
            play(getPlayer(0), () -> inputStr("Bernard Enter your word : "));
            currentPlayer = 2;
        }
        else {
            play(getPlayer(1), () -> inputStr("Chris Enter your word : "));
            currentPlayer = 1;
        }
    }


    @Override
    public PlayableGame gameEngine() {

        return new PlayableGame() {

            String[] wordList = {
                    "money", "house", "car", "school", "play", "blessed","superb", "diabolical", "stupendus", "high",
                    "cold", "head", "set", "sort","please", "kill", "love", "hope", "dreams", "death", "there", "these"
            };

            ArrayList<Integer>  usedWords = new ArrayList<>();

            String currentWord;

            @Override
            public PlayableGame gameRunner() {
                int index = MyFuncs.randint(0,wordList.length-1);

                while (usedWords.contains(index)) { index = MyFuncs.randint(0,wordList.length-1); }
                usedWords.add(index);
                currentWord = wordList[index];

                String temp = currentWord;
                String x = String.valueOf(temp.charAt(MyFuncs.randint(0,temp.length()-1)));
                temp = temp.replaceFirst(x,"_");

                System.out.println("Finish the word "+temp);

                return this;
            }

            @Override
            public Player runBeforeGameMove(Player player, GameMove gameMove){ return player; }

            @Override
            public Player playGameMove(Player player, GameMove gameMove) {

                String word = gameMove.makeMove().toString();

                if (word.toLowerCase().trim().equals(currentWord)) {
                    player.increaseScore();
                    System.out.println("you win");
                }
                else{ System.out.println("you loose"); }

                return player;
            }

            @Override
            public Player runAfterGameMove(Player player, GameMove gameMove) { return player; }

            @Override
            public Player playAsComputer(Player player) { return player; }

        };
    }

    @Override
    public void addPlayer(Player player) {
        player.setIncreaseHealthRate(1);
        player.setIncreaseScoreRate(1);
        player.setMaxHealth(100);
        super.addPlayer(player);
    }
}
