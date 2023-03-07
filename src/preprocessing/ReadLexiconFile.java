package preprocessing;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadLexiconFile {
    private List<String> contents;
    private String filename;

    public ReadLexiconFile (String filename) throws IOException{
        this.filename = filename;

        System.out.println("Reading " + filename + "...");
        long startTime, endTime;
        startTime = System.nanoTime();

        BufferedReader reader;

        contents = new ArrayList<>();

        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while(line != null){
            line = line.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
            line = line.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");

            if(!line.equals("")){
                contents.add(line);
            }

            line = reader.readLine();
        }

        reader.close();

        endTime = System.nanoTime();
        System.err.println("[Read " + filename + "] Duration: " + ((double)(endTime = startTime)) / 1000000 + " ms");
    }

    public List<String> getContents(){
        return (ArrayList<String>) contents;
    }

    public HashSet<String> getContentsInHash(){
        Set<String> hSet = new HashSet<String> (contents);
        return (HashSet<String>) hSet;
    }

    public void setContents(List<String> contents){
        this.contents = contents;
    }
}
