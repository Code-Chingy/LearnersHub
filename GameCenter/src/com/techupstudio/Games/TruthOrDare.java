package com.techupstudio.Games;

import com.techupstudio.Base.Game;
import com.techupstudio.Base.GameException;
import com.techupstudio.Base.GameMove;
import com.techupstudio.Base.Player;

public class TruthOrDare extends Game {

    @Override
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

    }


    @Override
    public PlayableGame gameEngine() {
        return new PlayableGame() {
            @Override
            public PlayableGame gameRunner() {
                return null;
            }

            @Override
            public Player runBeforeGameMove(Player player, GameMove gameMove) {
                return player;
            }

            @Override
            public Player playGameMove(Player player, GameMove gameMove) {
                return player;
            }

            @Override
            public Player runAfterGameMove(Player player, GameMove gameMove) {
                return player;
            }

            @Override
            public Player playAsComputer(Player computerPlayer) {
                return computerPlayer;
            }
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
