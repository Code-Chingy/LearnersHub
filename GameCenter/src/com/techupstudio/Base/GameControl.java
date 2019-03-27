package com.techupstudio.Base;


interface GameControl {

    void onCreate();

    void onStart();

    void onPause();
    
    void onResume();

    void onSave();
    
    void onExit();

    void preparePlayers();

    void runGame();

    void runGameLoop();

    void play(Player player, GameMove gameMove);

    Game.PlayableGame gameEngine();

    Game.PlayableGame getPlayableGame();

    void getPlayerPlayMove();

    void addPlayer(Player playerName);

    void removePlayer(String playerName);

    Player getPlayer(String playerName);

    Player getPlayer(int playerIndex);

    int getPlayerIndex(Player player);

    int getPlayersCount();

    void setLevel(int level);

    int getLevel();
}
