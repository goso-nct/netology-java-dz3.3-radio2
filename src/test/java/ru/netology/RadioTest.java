package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RadioTest {

    Radio radio = new Radio();
    Radio radio20Stations = new Radio(20);

    @Test
    void mustHave20Stations() {
        assertEquals(20, radio20Stations.getNumStation());
    }

    @Test
    void setStation_15() {
        radio20Stations.setStation(15);
        assertEquals(15, radio20Stations.getStation());
    }

    @Test
    void shouldInitFieldToMinValues() {
        assertEquals(Radio.MIN_STATION, radio.getStation());
        assertEquals(Radio.MIN_VOLUME, radio.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 9, 10})
    void setVolume_shouldSetCorrect(int correct) {
        radio.setVolume(correct);
        assertEquals(correct, radio.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 101, 120})
    void setVolume_shouldDontSetIncorrect(int incorrect) {
        radio.setVolume(incorrect);
        assertNotEquals(incorrect, radio.getVolume());
    }

    @Test
    void setStation_shouldSetCorrect() {
        radio.setStation(Radio.MIN_STATION);
        assertEquals(Radio.MIN_STATION, radio.getStation());
        int middleStation = (Radio.MIN_STATION + radio.getMaxStation()) / 2;
        radio.setStation(middleStation);
        assertEquals(middleStation, radio.getStation());
        radio.setStation(radio.getMaxStation());
        assertEquals(radio.getMaxStation(), radio.getStation());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 21, 40})
    void setStation_shouldDontSetIncorrect(int incorrect) {
        radio20Stations.setStation(incorrect);
        assertNotEquals(incorrect, radio20Stations.getVolume());
    }

    @ParameterizedTest
    @CsvSource(
        value={ "0, 1", "1, 2", "5, 6", "8, 9", "9, 0" }
    )
    void nextStation(int current, int expected) {
        radio.setStation(current);
        radio.nextStation();
        assertEquals(expected, radio.getStation());
    }

    @ParameterizedTest
    @CsvSource(
        value={ "9, 8", "8, 7", "5, 4", "1, 0", "0, 9" }
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
        value={ "10, 9", "9, 8", "8, 7", "6, 5", "1, 0", "0, 0" }
    )
    void decreaseVolume(int current, int expected) {
        radio.setVolume(current);
        radio.decreaseVolume();
        assertEquals(expected, radio.getVolume());
    }

}