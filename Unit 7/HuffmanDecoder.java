import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanDecoder {
    private final HashMap<String, Character> codeMap = new HashMap<>();

    public HuffmanDecoder(String codeFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(codeFile))) {
            for (int i = 0; i < 128; i++) {
                String line = reader.readLine();
                if (line != null && !line.isEmpty())
                    codeMap.put(line, (char) i);
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    public boolean isCode(String binary) {
        return codeMap.containsKey(binary);
    }

    public char decodeChar(String binary) {
        return codeMap.get(binary);
    }

    public void decodeFileFromHuffmanCodes(String encodedFile, String decodedFile) {
        // if (!encodedFile.endsWith(".huf"))
        //     throw new IllegalArgumentException("Encoded file must have .huf extension");
        try (BufferedReader reader = new BufferedReader(new FileReader(encodedFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(decodedFile))) {

            StringBuilder buffer = new StringBuilder();
            int c;

            while ((c = reader.read()) != -1) {
                buffer.append((char) c);
                if (isCode(buffer.toString())) {
                    char decoded = decodeChar(buffer.toString());
                    if (decoded == 26)
                        break;
                    writer.write(decoded);
                    buffer.setLength(0);
                }
            }

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    // Day 6
    public void decodeFile(String encodedFile) {
        if (!encodedFile.endsWith(".huf"))
            throw new IllegalArgumentException("Encoded file must have .huf extension");

        String decodedFile = encodedFile.substring(0, encodedFile.length() - 4);

        try (BufferedReader reader = new BufferedReader(new FileReader(encodedFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(decodedFile))) {

            StringBuilder Buffer = new StringBuilder();
            boolean done = false;
            int byteVal;

            while (!done && (byteVal = reader.read()) != -1) {
                String eightBits = String.format("%8s", Integer.toBinaryString(byteVal)).replace(' ', '0');

                for (int i = 0; i < eightBits.length() && !done; i++) {
                    Buffer.append(eightBits.charAt(i));
                    if (isCode(Buffer.toString())) {
                        char decoded = decodeChar(Buffer.toString());
                        if (decoded == 26)
                            done = true;
                        else
                            writer.write(decoded);
                        Buffer.setLength(0);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HuffmanDecoder decoder = new HuffmanDecoder("Unit 7/codes.txt");
        // decoder.decodeFileFromHuffmanCodes("Unit 7/test.txt.huf", "Unit
        // 7/test.txt.decoded");
        decoder.decodeFile("Unit 7/test.txt.huf");
        // this doesn't work
    }
}
