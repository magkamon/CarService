package org.example.DataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.example.domain.Customer;
import org.example.util.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataBaseJson implements CustomerDataBase {

    @Override
    public void saveCustomers(List<Customer> customers, String filename) {
        String fileWithJsonExtension = filename + ".json";
        if(FileUtil.ensureFileExistence(fileWithJsonExtension)){
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            try {
                String customerAsString = objectWriter.writeValueAsString(customers);
                Path path = Paths.get(fileWithJsonExtension);
                Files.write(path, customerAsString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Customer> readCustomers(String filename) {
        String fileWithJsonExtension = filename + ".json";
        List<Customer> customers = new ArrayList<>();
        if(FileUtil.checkIfFileExists(fileWithJsonExtension)){
            ObjectMapper objectMapper = new ObjectMapper();
            Path path = Paths.get(fileWithJsonExtension);

            try {
                byte[] myJson = Files.readAllBytes(path);
                customers = objectMapper.readValue(myJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Customer.class));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return customers;
    }

}
