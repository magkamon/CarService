package DataBase;

import domain.Car;
import util.FileUtil;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CarDataBaseXml implements CarDataBase{

    @Override
    public void saveCarList(List<Car> cars, String filename) {
        String fileWithExtension = filename + ".xml";
        if (FileUtil.ensureFileExistence(fileWithExtension)){
            try {
                CarsXml carsXml = new CarsXml();
                carsXml.setCars(cars);
                JAXBContext context = JAXBContext.newInstance(CarsXml.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                StringWriter stringWriter = new StringWriter();
                marshaller.marshal(carsXml, stringWriter);
                Path path = Paths.get(fileWithExtension);
                Files.write(path, stringWriter.toString().getBytes());
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Car> readCarList(String filename) {
        String fileWithExtension = filename + ".xml";
        List<Car> cars = new ArrayList<>();
        if (FileUtil.checkIfFileExists(fileWithExtension)) {
            try {
                Path path = Paths.get(fileWithExtension);
                File file = new File(String.valueOf(path));
                JAXBContext context = JAXBContext.newInstance(CarsXml.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                CarsXml xmlCar = (CarsXml) unmarshaller.unmarshal(file);
                cars = xmlCar.getCars();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return cars;
    }
}
