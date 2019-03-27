package com.techupstudio.Base;

public class Player{
    private String name;
    private int index;
    private int level;
    private float score;
    private float health;
    private boolean is_human;
    private float maxHealth;
    private float maxScore;
    private float increaseScoreRate = 0;
    private float increaseHealthRate = 0;
    private float decreaseScoreRate = 0;
    private float decreaseHealthRate = 0;

    public Player(String playerName){
        setName(playerName);
        setPlayerLevel(0);
        setScore(0);
        setMaxScore(100);
        setMaxHealth(100);
        is_human = true;
    }

    private void setName(String name){ this.name = name; }
    public String getName() { return name; }

    public int getIndex(){ return index; }
    public float getScore() { return score; }
    public float getHealth() { return health; }
    public int getPlayerLevel() { return level; }

    public void setHealth(float value){ health = value; }
    public void setScore(float value){ score = value; }
    public void setIndex(int index){ this.index=index; }
    public void setPlayerLevel(int level){ this.level = level; }

    public void setMaxHealth(float value) { maxHealth = value;health=value; }
    public void setMaxScore(float value) { maxScore = value; }

    public float getMaxHealth() { return health; }
    public float getMaxScore() { return score; }

    public void setIncreaseScoreRate(float value) { increaseScoreRate = value; }
    public void setIncreaseHealthRate(float value) { increaseHealthRate = value; }
    public void setDecreaseScoreRate(float value) { decreaseScoreRate = value; }
    public void setDecreaseHealthRate(float value) { decreaseHealthRate = value; }

    public float getDecreaseHealthRate() { return decreaseHealthRate; }
    public float getIncreaseHealthRate() { return increaseHealthRate; }
    public float getDecreaseScoreRate() { return decreaseScoreRate; }
    public float getIncreaseScoreRate() { return increaseScoreRate; }

    public void increaseHealth(){
        if (increaseHealthRate == 0.0) {
            try {
                throw new GameException.HealthIncreaseRateNotSetException();
            } catch (GameException.HealthIncreaseRateNotSetException e) {
                e.printStackTrace();
            }
        }else{
            if ((health + increaseHealthRate) < maxHealth){ health+=increaseHealthRate; }
        }
    }

    public void increaseScore(){
        if (increaseScoreRate == 0.0) {
            try {
                throw new GameException.ScoreIncreaseRateNotSetException();
            } catch (GameException.ScoreIncreaseRateNotSetException e) {
                e.printStackTrace();
            }
        }else{ if ((score + increaseScoreRate) < maxScore){ score+=increaseScoreRate; } }
    }

    public void decreaseHealth(){
        if (decreaseHealthRate == 0) {
            try {
                throw new GameException.HealthDecreaseRateNotSetException();
            } catch (GameException.HealthDecreaseRateNotSetException e) {
                e.printStackTrace();
            }
        }else{ if ((health - increaseHealthRate) > 0){ health -= increaseHealthRate; } }
    }

    public void decreaseScore(){
        if (decreaseScoreRate == 0) {
            try {
                throw new GameException.ScoreDecreaseRateNotSetException();
            } catch (GameException.ScoreDecreaseRateNotSetException e) {
                e.printStackTrace();
            }
        }else{ if ((score - decreaseScoreRate) > 0){ score -= decreaseScoreRate; } }
    }

    public boolean isHuman(){ return is_human;}

    public void setAsComputedPlayer() { is_human = false; }
}
