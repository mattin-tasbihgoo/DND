import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {
    private final HashMap<Character, String> codeMap = new HashMap<>();

    public HuffmanEncoder(String codeFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(codeFile))) {
            for (int i = 0; i < 128; i++) {
                String line = reader.readLine();
                if (line != null && !line.isEmpty())
                    codeMap.put((char) i, line);
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    public String encodeChar(char input) {
        if (codeMap.containsKey(input))
            return codeMap.get(input);
        else
            return "";

    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToCompress));
                BufferedWriter writer = new BufferedWriter(new FileWriter(encodedFile))) {

            StringBuilder buffer = new StringBuilder();
            char[] input = new char[4096];
            int charsRead;

            while ((charsRead = reader.read(input, 0, input.length)) != -1) {
                for (int i = 0; i < charsRead; i++) {
                    buffer.append(encodeChar(input[i]));
                    while (buffer.length() >= 8) {
                        writer.write(buffer.substring(0, 8));
                        buffer.delete(0, 8);
                    }
                }
            }

            buffer.append(encodeChar((char) 26));
            while (buffer.length() % 8 != 0)
                buffer.append('0');
            writer.write(buffer.toString());

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HuffmanEncoder encoder = new HuffmanEncoder(
                "C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 7\\codes.txt");
        encoder.encodeFileToHuffmanCodes(
                "C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 7\\test.txt",
                "C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 7\\test.txt.huf");
    }
}
