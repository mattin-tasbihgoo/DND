import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class MiniGPT {

	private final HashMap<String, HashMap<String, Integer>> hashMap = new HashMap<>();

	public MiniGPT(String fileName, int chainOrder) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			char[] buffer = new char[4096];
			int chars;
			String tail = "";

			while ((chars = reader.read(buffer, 0, buffer.length)) != -1) {
				String chunk = tail + new String(buffer, 0, chars);

				for (int i = 0; i + chainOrder < chunk.length(); i++) {
					String key = chunk.substring(i, i + chainOrder);
					String nextChar = String.valueOf(chunk.charAt(i + chainOrder));

					if (!hashMap.containsKey(key))
						hashMap.put(key, new HashMap<>());

					HashMap<String, Integer> nextCharsMap = hashMap.get(key);
					nextCharsMap.put(nextChar, nextCharsMap.getOrDefault(nextChar, 0) + 1);
				}

				tail = chunk.substring(Math.max(0, chunk.length() - chainOrder));
			}
		} catch (IOException e) {
			System.err.println("An I/O error occured: " + e.getMessage());
		}
	}

	public void generateText(String outputFileName, int numChars) {
		if (hashMap.isEmpty() || numChars <= 0)
			return;

		String[] keys = hashMap.keySet().toArray(new String[0]);
		String curr = keys[(int) (Math.random() * keys.length)];

		StringBuilder output = new StringBuilder(curr);

		while (output.length() < numChars) {
			HashMap<String, Integer> nextChars = hashMap.get(curr);
			if (nextChars == null || nextChars.isEmpty())
				break;

			String[] possibleNextChars = nextChars.keySet().toArray(new String[0]);
			String nextChar = possibleNextChars[(int) (Math.random() * possibleNextChars.length)];

			output.append(nextChar);
			curr = curr.substring(1) + nextChar;
		}
		try (FileWriter writer = new FileWriter(outputFileName)) {
			writer.write(output.substring(0, Math.min(numChars, output.length())));
		} catch (IOException e) {
			System.err.println("An I/O error occured: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		String inputFileName = "C:\\Users\\Matti\\OneDrive\\Documents\\GitHub\\DND\\Unit 6\\MiniGPT\\thegreatgatsby.txt";
		String outputFileName = "output2.txt";

		MiniGPT miniGPT = new MiniGPT(inputFileName, 10);
		miniGPT.generateText(outputFileName, 100000);
	}
}