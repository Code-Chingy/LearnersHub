package com.techupstudio.Base;

import java.util.ArrayList;
import java.util.List;

public abstract class Game implements GameControl {

    private int LEVEL;
    private String GAMESTATE;
    private int PLAYERINDEX = 0;
    private PlayableGame PLAYABLEGAME;
    private List<Player> PLAYERS = new ArrayList<>();

    public Game(){ onCreate(); }

    public static abstract class WaitAction extends Thread{
        @Override
        public void run() { runAction(); }

        public abstract void runAction();
    }

    public static abstract class PlayableGame {
        public abstract PlayableGame gameRunner();
        public abstract Player runBeforeGameMove(Player player, GameMove gameMove);
        public abstract Player playGameMove(Player player, GameMove gameMove);
        public abstract Player runAfterGameMove(Player player, GameMove gameMove);
        public abstract Player playAsComputer(Player computerPlayer);
    }

    @Override
    public void onCreate() { if(stateIsNull()) { PLAYABLEGAME = gameEngine(); setStateCreated(); } }

    @Override
    public void onStart() { if (isCreated()){ setStateStarted(); preparePlayers(); runGameLoop(); } }

    @Override
    public void onPause() { if (isPlaying()) { setStatePaused(); } }

    @Override
    public void onResume() { if (isPaused()) { setStateResumed(); } }

    @Override
    public abstract void onSave();

    @Override
    public void onExit() { setStateExited(); }

    @Override
    public abstract void preparePlayers();

    @Override
    public void runGame() {
        PLAYABLEGAME = gameEngine().gameRunner();
        getPlayerPlayMove();
    }

    @Override
    public void runGameLoop() {
        if (isStarted() || isPlaying() ){ runGame();runGameLoop(); }
        else if (isPaused()) { onPause();runGameLoop(); }
        else if (isResumed()){ onResume();runGame();runGameLoop(); }
        else if (isExited()) { onExit(); }
    }

    @Override
    public abstract void getPlayerPlayMove();

    @Override
    public void play(Player player, GameMove gameMove) {
        if (validatePlayer(player) && !(isPaused() || isExited())) {
            setStatePlaying();
            if (player.isHuman()) {
                player = getPlayableGame().runBeforeGameMove(player, gameMove);
                player = getPlayableGame().playGameMove(player, gameMove);
                player = getPlayableGame().runAfterGameMove(player, gameMove);
            }
            else{
                player = getPlayableGame().playAsComputer(player);
            }
            System.out.println(player.getScore());
            PLAYERS.remove(player.getIndex());
            PLAYERS.add(player.getIndex(), player);
        } else {
            try {
                throw new GameException.PlayerNotInGameException();
            } catch (GameException.PlayerNotInGameException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public abstract PlayableGame gameEngine();

    @Override
    public PlayableGame getPlayableGame() { return PLAYABLEGAME; }

    @Override
    public void addPlayer(Player player) {
        if (isCreated()) {
            if (!PLAYERS.contains(player)) {
                player.setIndex(PLAYERINDEX);
                PLAYERINDEX++;
                PLAYERS.add(player);
            }
        }
    }

    @Override
    public void removePlayer(String playerName) {
        for (Player player : PLAYERS){
            if (player.getName().equals(playerName)){ PLAYERS.remove(player); }
        }
    }

    @Override
    public Player getPlayer(String playerName) {
       for (Player player : PLAYERS){
           if (player.getName().equals(playerName)){ return player; }
       }
       return null;
    }

    @Override
    public Player getPlayer(int playerIndex) { return PLAYERS.get(playerIndex); }

    @Override
    public int getPlayerIndex(Player player) { return PLAYERS.indexOf(player);  }

    @Override
    public int getPlayersCount() { return PLAYERS.size(); }

    @Override
    public void setLevel(int level) { LEVEL = level; }

    @Override
    public int getLevel() { return LEVEL; }


    private boolean validatePlayer(Player player){ return PLAYERS.contains(player); }

    private void setStateCreated(){ GAMESTATE = "creating"; }
    private void setStateStarted(){ GAMESTATE = "started"; }
    private void setStatePaused(){ GAMESTATE = "paused"; }
    private void setStateResumed(){ GAMESTATE = "resumed"; }
    private void setStatePlaying(){ GAMESTATE = "playing"; }
    private void setStateExited(){ GAMESTATE = "exited"; }

    private boolean stateIsNull(){ return GAMESTATE == null; }
    private boolean isCreated(){ return GAMESTATE.equals("creating"); }
    private boolean isStarted(){ return GAMESTATE.equals("started"); }
    private boolean isPaused(){ return GAMESTATE.equals("paused"); }
    private boolean isResumed(){ return GAMESTATE.equals("resumed"); }
    private boolean isPlaying(){ return GAMESTATE.equals("playing"); }
    private boolean isExited(){ return GAMESTATE.equals("exited"); }

    public void start(){ onStart();  }
    public void pause(){ onPause(); }
    public void resume(){ onResume();  }
    public void save(){ onSave();  }
    public void exit(){ onExit(); }

}
