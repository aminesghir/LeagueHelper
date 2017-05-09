package com.aminesghir.leaguehelper.Data.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Amine on 06/05/2017.
 */

public class Game {

    private long id;
    private String region;
    private int queue;
    private int season;
    private long creationDate;
    private int duration;
    private int mapId;
    private String patch;
    private String mode;
    private String type;
    private List<Teammate> participants;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public int getDuration() {
        return duration;
    }

    public int getMapId() {
        return mapId;
    }

    public String getMode() {
        return mode;
    }

    public String getPatch() {
        return patch;
    }

    public String getType() {
        return type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Teammate> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Teammate> participants) {
        this.participants = participants;
    }

    public List<Teammate> getWinners(){
        List<Teammate> winners = new ArrayList<>(participants.size());
        for(int i=0; i<participants.size();i++){
            if(participants.get(i).isWinner()){
                winners.add(participants.get(i));
            }
        }
        return winners;
    }

    public List<Teammate> getLosers(){
        List<Teammate> losers = new ArrayList<>(participants.size());
        for(int i=0; i<participants.size();i++){
            if(!participants.get(i).isWinner()){
                losers.add(participants.get(i));
            }
        }
        return losers;
    }
}
