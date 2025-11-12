package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private static final DateTimeFormatter dtFormat =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public Path writeReceipt(String receiptText, LocalDateTime now) {

        try {
            // Create a folder path that points to src/main/resources/receipts
            Path folderDir = Paths.get("src/main/resources/receipts");

            String fileName = dtFormat.format(now) + ".txt";
            //makes the file name the date now with the format stored in dtFormat

            Path filePath = Paths.get(folderDir.toString(), fileName);
            // This combines folderDir and fileName into one path string(like "receipts/(numbers-numbers).txt")
            // then converts into a Path object using Paths.get()

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                writer.write(receiptText);
                //Creates a BufferedWriter that writes to the file represented by this Path
            }
            return filePath;

        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to write receipt file.", e);
        }
    }
}