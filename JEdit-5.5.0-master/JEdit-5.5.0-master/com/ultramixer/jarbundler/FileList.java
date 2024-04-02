import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileList {
    private List<File> files;

    public FileList() {
        this.files = new ArrayList<>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public List<File> getFiles() {
        return files;
    }
}
