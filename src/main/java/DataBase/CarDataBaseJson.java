package DataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import domain.Car;
import util.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CarDataBaseJson implements CarDataBase{

@Override
    public void saveCarList(List<Car> carsList, String filename) {
        String fileWithExtension = filename + ".json";
        if (FileUtil.ensureFileExistence(fileWithExtension)){
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

@Override
    public List<Car> readCarList(String filename) {
        String fileWithExtension = filename + ".json";
        List<Car> carList = new ArrayList<>();
        if (FileUtil.checkIfFileExists(fileWithExtension)) {
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
}
