package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import domain.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CarDataBase {

    private static final String CANNOT_OPEN_FILE_MESSAGE = "Nie można otworzyć pliku";

    private boolean checkIfFileExists(String fileWithExtension){
        return Files.exists(Paths.get(fileWithExtension));
    }


    private boolean ensureFileExistence(String fileWithExtension){
        if (checkIfFileExists(fileWithExtension)) {
            return true;
        }
        else {
            try {
                Files.createFile(Paths.get(fileWithExtension));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void saveCarList(List<Car> carsList, String filename) {
        String fileWithExtension = filename + ".json";
        if (ensureFileExistence(fileWithExtension)){
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            try {
                String carAsString = objectWriter.writeValueAsString(carsList);
                Path path = Paths.get(fileWithExtension);
                Files.write(path, carAsString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCarToList(Car car, String filename) {
        List<Car> carsList = readCarList(filename);
        carsList.add(car);
        saveCarList(carsList, filename);
    }

    public List<Car> readCarList(String filename) {
        String fileWithExtension = filename + ".json";
        List<Car> CarList = new ArrayList<>();
        if (checkIfFileExists(fileWithExtension)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Path path = Paths.get(fileWithExtension);
            try {
                byte[] myJson = Files.readAllBytes(path);
                CarList = objectMapper.readValue(myJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Car.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println(CANNOT_OPEN_FILE_MESSAGE);
        }
        return CarList;
    }
}
