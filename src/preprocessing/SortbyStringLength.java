package preprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SortbyStringLength {
    private List<String> list;

    public SortbyStringLength (String fileName) throws IOException{
        List<String> list = new ArrayList<String>();

        BufferedReader reader;

        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        while(line != null){
            if(!line.equals("")){
                list.add(line);
            }

            line = reader.readLine();
        }

        reader.close();

        String[] words = list.toArray(new String[0]);

        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        Arrays.sort(words, (x,y) -> Integer.compare(y.length(), x.length()));

        for(String word: words){
            writer.println(word);
        }

        writer.close();
    }

    public SortbyStringLength (List<String> list) throws IOException {
        this.list = list;

        String[] words = list.toArray(new String[0]);

        //Sort the array by supplying the lamba expression to compare two strings in the array
        Arrays.sort(words, (x,y) -> Integer.compare(y.length(), x.length()));

        list = Arrays.asList(words);

        //Printing out the sorted array
        //System.out.println(Arrays.deepToString(words));
    }

    public List<String> getList(){
        return list;
    }

    public void setList(List<String> list){
        this.list = list;
    }
}
