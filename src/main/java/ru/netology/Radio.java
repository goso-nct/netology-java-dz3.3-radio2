package ru.netology;

public class Radio {

    static final int MIN_STATION = 0;
    static final int MIN_VOLUME  = 0;
    static final int MAX_VOLUME  = 100;

    private int station = MIN_STATION;
    private int volume  = MIN_VOLUME;

    private int numStation = 10; // количество станций по умолчанию [0..9]
    private int maxStation;

    public Radio() {
        setMaxStation();
    }

    public Radio(int numStation) {
        this.numStation = numStation;
        setMaxStation();
    }

    private void setMaxStation() {
        maxStation = numStation - 1;  // т.к. от 0
    }

    public void nextStation() {
        station = (station != maxStation) ? station + 1 : MIN_STATION ;
    }

    public void prevStation() {
        station = station != MIN_STATION ? station - 1 : maxStation ;
    }

    public void increaseVolume() {
        volume = volume != MAX_VOLUME ? volume + 1 : MAX_VOLUME ;
    }

    public void decreaseVolume() {
        volume = volume != MIN_VOLUME ? volume - 1 : MIN_VOLUME ;
    }

    public int getNumStation() {
        return numStation;
    }

    int getMaxStation() {
        return maxStation;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        if (station >= MIN_STATION && station <= maxStation) {
            this.station = station;
        }
    }

    public int getVolume() {
        return volume;
    }

    void setVolume(int volume) {
        if (volume >= MIN_VOLUME && volume <= MAX_VOLUME)
            this.volume = volume;
    }
}
