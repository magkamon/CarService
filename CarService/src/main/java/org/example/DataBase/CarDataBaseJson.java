package org.example.DataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.example.domain.Car;
import org.example.util.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CarDataBaseJson implements CarDataBase{

@Override
    public void saveCarList(List<Car> cars, String filename) {
        String fileWithExtension = filename + ".json";
        if (FileUtil.ensureFileExistence(fileWithExtension)){
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            try {
                String carAsString = objectWriter.writeValueAsString(cars);
                Path path = Paths.get(fileWithExtension);
                Files.write(path, carAsString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

@Override
    public List<Car> readCarList(String filename) {
        String fileWithExtension = filename + ".json";
        List<Car> cars = new ArrayList<>();
        if (FileUtil.checkIfFileExists(fileWithExtension)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Path path = Paths.get(fileWithExtension);
            try {
                byte[] myJson = Files.readAllBytes(path);
                cars = objectMapper.readValue(myJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Car.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cars;
    }
}
