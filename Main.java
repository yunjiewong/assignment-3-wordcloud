import java.io.*;
import java.util.*;
import java.util.Map.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
		
		//read in txt file
		wordCounts = readInLyrics();  

        //sort HashMap by value
        HashMap<String, Integer> sortedMap = sortByValue(wordCounts);

        // write frequency into file
        writeInFile(sortedMap);
        
		
	}
	
	// readInLyrics() reads in lyrics.txt and then saves words into a HashMap
	public static HashMap readInLyrics() throws FileNotFoundException {
	    File file = new File("lyrics.txt");
	    Scanner scanner = new Scanner(file);
	    
	    HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
        while (scanner.hasNext()) {
            String next = scanner.next(); 
            //reduce all punctuation
            String clean = next.replaceAll("\\p{Punct}", ""); 

            //words counting
            if (!wordCounts.containsKey(clean)) 
                wordCounts.put(clean, 1);
             else 
                wordCounts.put(clean, wordCounts.get(clean) + 1);
        }
        scanner.close();
        return wordCounts;
	}
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {

      // Create a list from elements of HashMap
      List<Entry<String, Integer>> list = new LinkedList(hm.entrySet());

	  Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String,Integer>>() {
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return (e2.getValue()).compareTo(e1.getValue());
            }
        };
        
      // Sort the list
      Collections.sort(list, valueComparator);

      // put data from sorted list to hashmap
      HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
      for (Entry<String, Integer> aa : list) {
        temp.put(aa.getKey(), aa.getValue());
      }
      return temp;
    }
    
    
    
    public static void writeInFile(HashMap sortedMap)throws IOException{
        File file = new File("output.txt");
        PrintWriter ptr = new PrintWriter(file);
        for (Object word : sortedMap.keySet()) {
            int count = (int)sortedMap.get(word);
            ptr.println(count + ": " + word);
        }
        ptr.close();
    }
}
