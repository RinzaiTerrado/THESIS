package preprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityTagger {
    protected String tag;
    protected String text;
    protected List<String> found;
    protected Map<String, Integer> map;
    protected HashMap<String, String> prev;
    private int index;

    public EntityTagger(String tag, String text){
        this.tag = tag;
        this.text = text;

        found = new ArrayList<String>();
        map = new HashMap<>();
        prev= new HashMap<String, String>();
        index = 0;
    }

    public EntityTagger hideTagged(){
        Pattern pattern = Pattern.compile("<([A-Za-z]+)>([a-zA-Z\\s.-]+)<\\/[A-Za-z]+>");
        Matcher matcher = pattern.matcher(text);
        index = 0;

        while(matcher.find()){
            prev.put(matcher.group(1) + " " + index, matcher.group(2));
            text = text.replaceAll(matcher.group(), "<<" + matcher.group(1) + "@" + index + ">>");
            index++;
        }

        return this;
    }

    public EntityTagger tagEntities(){
        for(String e : found){
            e = e.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
            e = e.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
            prev.put(tag + " " + index, e);
            text = text.replaceAll(e, "<<" + tag + "@" + index + ">>");
            index++;
        }

        return this;
    }

    public void printEntityFrequencyCount(){
        for(String i : map.keySet()){
            System.out.println("value: " + map.get(i) + "\tkey: " + i);
        }
    }

    public EntityTagger resolveHiddenEntities(){
        for(String e: prev.keySet()){
            String []tag = e.split(" ");
            text = text.replaceAll("<<" + tag[0] + "@" + tag[1] + ">>", "<" + tag[0] + ">" + prev.get(e) + "</" + tag[0] + ">");
        }
        return this;
    }

    public EntityTagger removeOverlappingTags() {
        // Remove inside tags - Left hand side
        Pattern pattern = Pattern.compile("([-\\w\\d\\.]+)<[\\w\\d]+>([\\w\\d\\s]+)<\\/[\\w\\d]+>");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            text = text.replaceAll(matcher.group(), matcher.group(1) + matcher.group(2));
        }

        // Remove inside tags - Right hand side
        pattern = Pattern.compile("<[\\w\\d]+>([\\w\\d\\s]+)<\\/[\\w\\d]+>([-\\w\\d]+)");
        matcher = pattern.matcher(text);
        while(matcher.find()) {
            text = text.replaceAll(matcher.group(), matcher.group(1) + matcher.group(2));
        }


        // Remove reference ex. </tag>23
        pattern = Pattern.compile("(<\\/\\w+>)\\d+");
        matcher = pattern.matcher(text);
        while(matcher.find()) {
            text = text.replaceAll(matcher.group(), matcher.group(1));
        }

        // Remove reference ex. </tag>,2,3
        pattern = Pattern.compile("(<\\/\\w+>,)(\\d+)+");
        matcher = pattern.matcher(text);
        while(matcher.find()) {
            text = text.replaceAll(matcher.group(), matcher.group(1));
        }

        return this;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getFound() {
        return found;
    }

    public void setFound(List<String> found) {
        this.found = found;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public HashMap<String, String> getPrev() {
        return prev;
    }

    public void setPrev(HashMap<String, String> prev) {
        this.prev = prev;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
