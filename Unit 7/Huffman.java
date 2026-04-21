import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Huffman {
    private final HashMap<Character, Integer> freqMap = new HashMap<>();

    public Huffman(String fileName) {
        buildFrequencyDictionary(fileName);
    }

    private void buildFrequencyDictionary(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            char[] buffer = new char[4096];
            int chars;

            while ((chars = reader.read(buffer, 0, buffer.length)) != -1) {
                for (int i = 0; i < chars; i++) {
                    char c = buffer[i];
                    if (freqMap.containsKey(c))
                        freqMap.put(c, freqMap.get(c) + 1);
                    else
                        freqMap.put(c, 1);

                }
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman("C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 7\\test.txt");
        System.out.println("Character frequencies:");
        for (HashMap.Entry<Character, Integer> entry : huffman.freqMap.entrySet()) 
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        
    }
}
