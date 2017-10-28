package FileHelper;

public class CorruptedFileException extends Exception {
    public CorruptedFileException(String message) {
        super(message);
    }
}
