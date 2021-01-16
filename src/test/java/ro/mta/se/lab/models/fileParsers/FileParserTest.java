package ro.mta.se.lab.models.fileParsers;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    @org.junit.jupiter.api.Test
    void getSpecificCityId() throws Exception {
        FileParser fileParser = new FileParser();
        fileParser.readConfFile("files/inputRO.txt");

        assertEquals("524649", fileParser.getSpecificCityId("Mstikhino"), "Good Test");
        assertEquals("579838", fileParser.getSpecificCityId("Babynino"), "Good Test");
    }
}