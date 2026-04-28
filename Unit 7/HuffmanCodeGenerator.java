import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    private final HashMap<Character, Integer> freqMap = new HashMap<>();
    private final HashMap<Character, String> codeMap = new HashMap<>();
    private final HuffmanNode root;

    public HuffmanCodeGenerator(String frequencyFile) {
        buildFrequencyDictionary(frequencyFile);
        root = minHeapify();
        binaryDictionary(root, "");
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

            if (freqMap.containsKey((char) 26))
                freqMap.put((char) 26, freqMap.get((char) 26) + 1);
            else
                freqMap.put((char) 26, 1);
            // total didn't forget eof...
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    private HuffmanNode minHeapify() {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet())
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));

        while (pq.size() > 1) {
            HuffmanNode lo = pq.poll();
            HuffmanNode hi = pq.poll();
            pq.offer(new HuffmanNode(lo.freq + hi.freq, lo, hi));
        }

        return pq.poll();
    }

    public void makeCodeFile(String codeFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(codeFile))) {
            for (int i = 0; i < 128; i++) {
                char c = (char) i;
                if (codeMap.containsKey(c))
                    writer.write(codeMap.get(c));
                else
                    writer.write("");

                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    private void binaryDictionary(HuffmanNode node, String binCode) {
        if (node.isLeaf()) {
            codeMap.put(node.ch, binCode);
            return;
        }
        binaryDictionary(node.left, binCode + "0");
        binaryDictionary(node.right, binCode + "1");
    }

    // assignment requires these two methods
    public int getFrequency(char c) {
        if (freqMap.containsKey(c))
            return freqMap.get(c);
        else
            return 0;
    }

    public String getCode(char c) {
        if (codeMap.containsKey(c))
            return codeMap.get(c);
        else
            return "";
    }

    private void printTree(HuffmanNode node, String binCode) {
        if (node.isLeaf()) {
            System.out.println(binCode + " - '" + node.ch + "' - " + node.freq);
            return;
        }
        printTree(node.left, binCode + "0");
        printTree(node.right, binCode + "1");
    }

    public static void main(String[] args) {
        HuffmanCodeGenerator huffin = new HuffmanCodeGenerator(
                "C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 7\\test.txt");

        System.out.println("Huffman Tree:");
        huffin.printTree(huffin.root, "");

        huffin.makeCodeFile("C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 7\\codes.txt");
    }
}