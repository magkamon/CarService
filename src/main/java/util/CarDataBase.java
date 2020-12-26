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

    private static final String CANNOT_CREATE_FILE_MESSAGE = "Nie można stworzyć nowego pliku";

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

    public List<Car> readCarList(String filename) {
        String fileWithExtension = filename + ".json";
        List<Car> carList = new ArrayList<>();
        if (checkIfFileExists(fileWithExtension)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Path path = Paths.get(fileWithExtension);
            try {
                byte[] myJson = Files.readAllBytes(path);
                carList = objectMapper.readValue(myJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Car.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return carList;
    }

    private boolean checkIfFileExists(String fileWithExtension){
        return Files.exists(Paths.get(fileWithExtension));
    }

    private boolean ensureFileExistence(String fileWithExtension){
        if (checkIfFileExists(fileWithExtension)) {
            return true;
        }

        try {
            Files.createFile(Paths.get(fileWithExtension));
        } catch (IOException e) {
            System.out.println(CANNOT_CREATE_FILE_MESSAGE);
            return false;
        }

        return true;
    }
}
