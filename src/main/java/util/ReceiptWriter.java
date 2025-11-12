package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private static final DateTimeFormatter dtFormat =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public Path writeReceipt(String receiptText, LocalDateTime now) { //will give path object when done
        try {
            Path folderDir = Paths.get("receipts");
            Files.createDirectories(folderDir); // File is a utility class in JAva

            String fileName = dtFormat.format(now) + ".txt"; //makess the file name the date now with the format stored in dtFormat

            // created a filePath of type Path (dont want to confuse it with the Files UTILITY class)
            Path filePath = Paths.get(folderDir.toString() + "/" + fileName); // combines folderDir and fileName into one path string(like "receipts/(numbers-numbers).txt")
                                                                               // then converts into a Path object using Paths.get()
            // Buffered writer
            try (BufferedWriter bWriter =
                         new BufferedWriter(new FileWriter(filePath.toFile()))) { //Creates a BufferedWriter that writes to the file represented by this Path
                bWriter.write(receiptText);
            }
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Error, receipt writing failed.", e);
        }
    }
}
