import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RLECompression {

    // Creates a compressed version with fileName + ".rle.bw"
    public static void compress(String fileName) throws IOException {
        bwTransform(fileName);
        encode(fileName);
    }

    // Decompresses a .rle.bw file
    public static void decompress(String fileName) throws IOException {
        decode(fileName);
        invertBWTransform(fileName.substring(0, fileName.length() - 4));
    }

    // Encodes the contents of a file using the RLE compression scheme:
    // single characters are left alone, and runs of 2+ characters are encoded as
    // that letter twice, followed by the length of the run, cast as a char
    public static void encode(String fileName) throws IOException {
        PrintWriter pw;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            pw = new PrintWriter(fileName + ".rle");
            char previousChar = (char) br.read();
            int count = 1;
            while (br.ready()) {
                char c = (char) br.read();
                if (c != previousChar) {
                    if (count > 1)
                        pw.write("" + previousChar + previousChar + (char) count);
                    else
                        pw.write(previousChar);
                    previousChar = c;
                    count = 1;
                } else
                    count++;
            }   if (count > 1)
                pw.write("" + previousChar + previousChar + (char) count);
            else
                pw.write(previousChar);
        }
        pw.close();

    }

    // Decodes the above scheme
    public static void decode(String fileName) throws IOException {
        PrintWriter pw;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));
            char previousChar = (char) br.read();
            while (br.ready()) {
                char c = (char) br.read();
                if (previousChar == c) {
                    char nextChar = (char) br.read();
                    int runLength = (int) nextChar;
                    for (int i = 0; i < runLength; i++)
                        pw.write(previousChar);
                    previousChar = (char) br.read();
                } else {
                    pw.write(previousChar);
                    previousChar = c;
                }
            }   pw.write(previousChar);
        }
        pw.close();
    }

    public static void bwTransform(String fileName) throws IOException {
        StringBuilder originalText;
        // Add a null character at the beginning, as a
        // "beginning of file" character
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            originalText = new StringBuilder("" + '\0');
            while (br.ready()) {
                char c = (char) br.read();
                originalText.append(c);
            }
        }

        List<String> rotations = new ArrayList<>();
        rotations.add(originalText.toString());
        for (int i = 1; i < originalText.length(); i++)
            rotations.add(originalText.substring(i) + originalText.substring(0, i));

        Collections.sort(rotations);

        try (PrintWriter pw = new PrintWriter(fileName + ".bw")) {
            for (String rotation : rotations)
                pw.write(rotation.charAt(rotation.length() - 1));
        }
    }

    public static void invertBWTransform(String fileName) throws IOException {
        StringBuilder originalText;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            originalText = new StringBuilder();
            while (br.ready()) {
                char c = (char) br.read();
                originalText.append(c);
            }
        }

        StringBuilder[] recon = new StringBuilder[originalText.length()];
        for (int i = 0; i < recon.length; i++)
            recon[i] = new StringBuilder("" + originalText.charAt(i));

        for (int i = 1; i < originalText.length(); i++) {
            StringBuilder[] newRecon = new StringBuilder[originalText.length()];
            for (int j = 0; j < recon.length; j++)
                newRecon[j] = new StringBuilder(originalText.charAt(j) + recon[j].toString());

            recon = newRecon;
            List<String> reconstructionList = new ArrayList<>();
            for (StringBuilder sb : recon)
                reconstructionList.add(sb.toString());
            Collections.sort(reconstructionList);
            for (int j = 0; j < recon.length; j++)
                recon[j] = new StringBuilder(reconstructionList.get(j));
        }
        try (PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3))) {
            pw.write(recon[0].toString().substring(1));
        }
    }

    public static void main(String[] args) {
        String inputFileName = "test.txt";
        try {
            encode(inputFileName);
            System.out.println("Encoded successfully → test.txt.rle");

            decode(inputFileName + ".rle");
            System.out.println("Decoded successfully → test.txt");

            bwTransform("test.txt");
            invertBWTransform("test.txt.bw");
        } catch (IOException e) {
        }
    }
}
