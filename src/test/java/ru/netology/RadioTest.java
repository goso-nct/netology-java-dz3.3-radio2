package ru.netology;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RadioTest {

    Radio radio = new Radio();

    @Test
    void shouldCreateAllArgs() {
        Radio radioAllArgs = new Radio(25,40,50);
        assertEquals(25, radioAllArgs.getStation());
        assertEquals(40, radioAllArgs.getVolume());
        assertEquals(50, radioAllArgs.getNumStations());
    }

    @Test
    void mustHave20Stations() {
        Radio radio20Stations = new Radio(20);
        assertEquals(20, radio20Stations.getNumStations());
    }

    @Test
    void setStation_15() {
        Radio radio20Stations = new Radio(20);
        radio20Stations.setStation(15);
        assertEquals(15, radio20Stations.getStation());
    }

    @Test
    void shouldInitFieldToMinValues() {
        assertEquals(Radio.MIN_STATION, radio.getStation());
        assertEquals(Radio.MIN_VOLUME, radio.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 99, 100})
    void setVolume_shouldSetCorrect(int correct) {
        radio.setVolume(correct);
        assertEquals(correct, radio.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 101, 120})
    @Disabled // setVolume генерит lombok - нет проверок
    void setVolume_shouldDontSetIncorrect(int incorrect) {
        radio.setVolume(incorrect);
        assertNotEquals(incorrect, radio.getVolume());
    }

    @Test
    void setStation_shouldSetCorrect() {
        radio.setStation(Radio.MIN_STATION);
        assertEquals(Radio.MIN_STATION, radio.getStation());
        int middleStation = (Radio.MIN_STATION + radio.getNumStations()) / 2;
        radio.setStation(middleStation);
        assertEquals(middleStation, radio.getStation());
        radio.setStation(radio.getNumStations());
        assertEquals(radio.getNumStations(), radio.getStation());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 21, 40})
    @Disabled // setStation генерит lombok - нет проверок
    void setStation_shouldDontSetIncorrect(int incorrect) {
        Radio radio20Stations = new Radio(20);
        radio20Stations.setStation(incorrect);
        assertNotEquals(incorrect, radio20Stations.getVolume());
    }

    @ParameterizedTest
    @CsvSource(
        value={ "10, 1", "1, 2", "5, 6", "9, 10" }
    )
    void nextStation(int current, int expected) {
        radio.setStation(current);
        radio.nextStation();
        assertEquals(expected, radio.getStation());
    }

    @ParameterizedTest
    @CsvSource(
        value={ "10, 9", "9, 8", "5, 4", "2, 1", "1, 10" }
    )
    void prevStation(int current, int expected) {
        radio.setStation(current);
        radio.prevStation();
        assertEquals(expected, radio.getStation());
    }

    @ParameterizedTest
    @CsvSource(
        value={ "0, 1", "1, 2", "5, 6", "99, 100", "100, 100" }
    )
    void increaseVolume(int current, int expected) {
        radio.setVolume(current);
        radio.increaseVolume();
        assertEquals(expected, radio.getVolume());
    }

    @ParameterizedTest
    @CsvSource(
        value={ "100, 99", "99, 98", "8, 7", "6, 5", "1, 0", "0, 0" }
    )
    void decreaseVolume(int current, int expected) {
        radio.setVolume(current);
        radio.decreaseVolume();
        assertEquals(expected, radio.getVolume());
    }

}