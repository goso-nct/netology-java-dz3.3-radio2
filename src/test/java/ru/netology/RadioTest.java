package ru.netology;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RadioTest {

    Radio radio = new Radio();

    @Test
    void shouldInitFieldToMinValues() {
        assertEquals(Radio.MIN_STATION, radio.getStation());
        assertEquals(Radio.MIN_VOLUME, radio.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 9, 10})
    void setVolume_shouldSetCorrectVolume(int correctVolume) {
        radio.setVolume(correctVolume);
        assertEquals(correctVolume, radio.getVolume());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 11, 20})
    void setVolume_shouldDontSetIncorrectVolume(int incorrectVolume) {
        radio.setVolume(incorrectVolume);
        assertNotEquals(incorrectVolume, radio.getVolume());
    }

    @Test
    void setStation_shouldSetCorrectStation() {
        radio.setStation(Radio.MIN_STATION);
        assertEquals(Radio.MIN_STATION, radio.getStation());
        int middleStation = (Radio.MIN_STATION + Radio.MAX_STATION) / 2;
        radio.setStation(middleStation);
        assertEquals(middleStation, radio.getStation());
        radio.setStation(Radio.MAX_STATION);
        assertEquals(Radio.MAX_STATION, radio.getStation());
    }

    @ParameterizedTest
    @ValueSource(ints = {Radio.MIN_STATION - 10, Radio.MAX_STATION + 10})
    void setStation_shouldDontSetIncorrectStation(int incorrectStation) {
        radio.setStation(incorrectStation);
        assertNotEquals(incorrectStation, radio.getStation());
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
        value={ "0, 1", "1, 2", "5, 6", "9, 10", "10, 10" }
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