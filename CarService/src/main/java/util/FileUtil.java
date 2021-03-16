package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    private static final String CANNOT_CREATE_FILE_MESSAGE = "Nie można stworzyć nowego pliku";

    public static boolean checkIfFileExists(String fileWithExtension){
        return Files.exists(Paths.get(fileWithExtension));
    }

    public static boolean ensureFileExistence(String fileWithExtension){
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
