package ro.mta.se.lab.models.fileParsers;

import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    @org.junit.jupiter.api.Test
    @Timeout(value = 600, unit = TimeUnit.MILLISECONDS)
    void getSpecificCityId() throws Exception {
        FileParser fileParser = new FileParser();
        fileParser.readConfFile("files/inputRO.txt");

        assertEquals("524649", fileParser.getSpecificCityId("Mstikhino"), "Good Test");
        assertEquals("579838", fileParser.getSpecificCityId("Babynino"), "Good Test");
    }
}