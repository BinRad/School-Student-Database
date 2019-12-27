package Project2;

import java.util.HashMap;
import java.util.Map;

public class HistogramAlphaBet {
    String s;
    public HistogramAlphaBet(){
       // System.out.println(emma.getString());
        ReadTextFile emma = new ReadTextFile();
        emma.openFile();
        s = emma.readFile();
        emma.closeFile();
    }
    public void count(PieChart pie){
        String w = s.replaceAll("[^a-zA-Z]","");
        Map<String, Integer> data = new HashMap<>();
        for(int i = 0; i <w.length(); i++){
            Character ch = w.charAt(i);
            incrementFrequency(data, ch.toString());
        }
        pie.makeProbability(data);
    }
    public static <k> void incrementFrequency(Map<k, Integer> M, k key){
        M.putIfAbsent((k) key, 0);
        M.put((k) key, M.get(key)+1);
    }
}