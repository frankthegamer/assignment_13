import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CountWords {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter filename: ");  // input filename
        
        Scanner scanner = new Scanner(System.in); 
        

        String inputFile = scanner.next();
        scanner.close();
        String outputFile = "output.txt";


        // hashmap to store word count
        HashMap<String, Integer> wordCount = new HashMap<>();

        // read input file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String word = line.trim().toLowerCase();
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }      
        }catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        
        // sortable list from hashmap
        ArrayList<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordCount.entrySet());
        
        
        // sorts by word count
        wordList.sort(Comparator.comparing(Map.Entry::getValue));

        
        // write output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Map.Entry<String, Integer> entry : wordList) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Words counted - results saved to " + outputFile);
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
        }
    }

}

