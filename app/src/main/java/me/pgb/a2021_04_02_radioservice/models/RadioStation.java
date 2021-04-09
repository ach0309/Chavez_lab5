package me.pgb.a2021_04_02_radioservice.models;


public class RadioStation {

    private String radioStationURL;
    private String name;
    private String genre;
    private String location;

    public RadioStation(String radioStationURL, String name, String genre, String location){
        this.radioStationURL = radioStationURL;
        this.name = name;
        this.genre= genre;
        this.location = location;
    }

    public String getRadioStationURL(){ return radioStationURL; }
    public String getName(){return name; }
    public String getGenre(){return genre; }
    public String getLocation(){return location; }

}