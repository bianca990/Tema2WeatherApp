package ro.mta.se.lab.models.fileParsers;

import ro.mta.se.lab.models.exceptions.InvalidInputFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileParser {

    private List<FileEntry> confFile = new ArrayList<FileEntry>();

    public void readConfFile(String inputFile) throws Exception {

        /* citirea fisierului de intrare */
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\Tema2WeatherApp\\src\\main\\resources\\" + inputFile));
            String line = br.readLine();
            do {
                String[] split = line.split("\t");

                if (split.length != 5)
                    throw new InvalidInputFile("Fisier de intrare invalid!!!");

                confFile.add(new FileEntry(split[0], split[1], split[2], split[3], split[4]));
                line = br.readLine();
            } while (line != null);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<FileEntry> getConfFile() {
        return confFile;
    }

    public List<String> getCountryList() {
        List<String> countryList = new ArrayList<String>();
        for (int i = 0; i < confFile.size(); i++) {
            countryList.add(confFile.get(i).getCountryCode());
        }

        /* Unique List */
        Set<String> setString = new HashSet<String>(countryList);
        countryList.clear();
        countryList.addAll(setString);

        return countryList;
    }

    public List<String> getCityList(String countryCode) {

        ArrayList<String> cityList;
        HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();

        for (int i = 0; i < confFile.size(); i++) {
            if (hash.containsKey(confFile.get(i).getCountryCode())) {
                cityList = hash.get(confFile.get(i).getCountryCode());
                cityList.add(confFile.get(i).getCityName());
                hash.put(confFile.get(i).getCountryCode(), cityList);
            } else {
                cityList = new ArrayList<String>();
                cityList.add(confFile.get(i).getCityName());
                hash.put(confFile.get(i).getCountryCode(), cityList);
            }
        }
        return hash.get(countryCode);
    }

    public String getSpecificCityId(String cityName) {
        for (int i = 0; i < confFile.size(); i++) {
            if (cityName.equals(confFile.get(i).getCityName()))
                return confFile.get(i).getCityId();
        }
        return null;
    }
}
