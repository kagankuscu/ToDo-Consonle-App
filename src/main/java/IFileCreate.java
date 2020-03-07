import java.io.IOException;

public interface IFileCreate {
    boolean checkFileAlreadyCreated(String fileName) throws IOException;
}
