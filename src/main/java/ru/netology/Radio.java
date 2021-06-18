package ru.netology;

// для Lombok считаю станции от 1 до numStation

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Radio {

    static final int MIN_STATION = 1;
    static final int MIN_VOLUME = 0;
    static final int MAX_VOLUME = 100;

    private int station = MIN_STATION;
    private int volume = MIN_VOLUME;
    private int numStations = 10; // количество станций по умолчанию [1..10]

    public Radio(int numStation) {
        this.numStations = numStation;
    }

    public void nextStation() {
        station = (station != numStations) ? station + 1 : MIN_STATION;
    }

    public void prevStation() {
        station = station != MIN_STATION ? station - 1 : numStations;
    }

    public void increaseVolume() {
        volume = volume != MAX_VOLUME ? volume + 1 : MAX_VOLUME;
    }

    public void decreaseVolume() {
        volume = volume != MIN_VOLUME ? volume - 1 : MIN_VOLUME;
    }

}
