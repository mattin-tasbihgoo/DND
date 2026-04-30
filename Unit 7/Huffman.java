import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    private final HashMap<Character, Integer> freqMap = new HashMap<>();
    private final HuffmanNode root;

    public Huffman(String fileName) {
        buildFrequencyDictionary(fileName);
        root = minHeapify();
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

    private void binaryDictionary(HuffmanNode node, String binCode, HashMap<Character, String> dict) {
        if (node.isLeaf()) {
            dict.put(node.ch, binCode);
            return;
        }
        binaryDictionary(node.left, binCode + "0", dict);
        binaryDictionary(node.right, binCode + "1", dict);
    }

    @SuppressWarnings("unused")
    private HashMap<Character, String> getBinaryDictionary() {
        HashMap<Character, String> dict = new HashMap<>();
        binaryDictionary(root, "", dict);
        return dict;
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
        Huffman huffman = new Huffman("C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 7\\test.txt");
        // System.out.println("Character frequencies:");

        // for (HashMap.Entry<Character, Integer> entry : huffman.freqMap.entrySet())
        // System.out.println("'" + entry.getKey() + "': " + entry.getValue());

        // System.out.println("Binary codes:");
        // for (HashMap.Entry<Character, String> entry :
        // huffman.getBinaryDictionary().entrySet())
        // System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        System.out.println("Huffman Tree:");
        huffman.printTree(huffman.root, "");
    }
}