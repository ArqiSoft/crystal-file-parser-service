package sds.crystalfileparser.domain.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CifParser implements Iterable<Record> {

    private String path;

    public CifParser(InputStream stream) throws IOException {
        
        File tempFile = File.createTempFile("temp-crystal-", ".tmp");
        path = tempFile.getCanonicalPath();
        
        Files.copy(stream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public Iterable<String> Extensions() {
        return Arrays.asList( ".RDF", ".RXN" );
    }

    @Override
    public Iterator<Record> iterator() {
        try {
            return new CifRecordsIterator(path);
        } catch (Exception ex) {
            Logger.getLogger(CifParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
