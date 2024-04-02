import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileSet {
    private Set<File> files;

    public FileSet() {
        this.files = new HashSet<>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public Set<File> getFiles() {
        return files;
    }
}
