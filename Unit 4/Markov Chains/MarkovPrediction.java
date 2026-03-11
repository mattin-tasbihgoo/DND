import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public final class MarkovPrediction {

    private final HashMap<String, HashMap<String, Integer>> hashMap = new HashMap<>();

    // Please define your own variables and data structures
    //

    public MarkovPrediction(String filePath) {
        ArrayList<String[]> data = readData(filePath);
        for (String[] str : data) {
            String curSt = str[0].trim();
            String nxSt = str[1].trim();

            if (!hashMap.containsKey(curSt)) {
                hashMap.put(curSt, new HashMap<>());
            }
            HashMap<String, Integer> nextStates = hashMap.get(curSt);
            if (nextStates.containsKey(nxSt)) {
                nextStates.put(nxSt, nextStates.get(nxSt) + 1);
            } else {
                nextStates.put(nxSt, 1);
            }
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public ArrayList<String[]> readData(String filePath) {

        ArrayList<String[]> temp = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                temp.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        HashMap<String, Integer> nxSt = hashMap.get(currentState);
        if (nxSt == null || nxSt.isEmpty()) {
            return null;
        }

        int total = 0;
        for (int count : nxSt.values()) {
            total += count;
        }
        int rand = (int) (Math.random() * total);
        int sum = 0;
        for (HashMap.Entry<String, Integer> entry : nxSt.entrySet()) {
            sum += entry.getValue();
            if (rand < sum) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("--- Weather Predictions ---");
        MarkovPrediction markPre = new MarkovPrediction("weather.csv");
        String curSt = "Sunny";
        for (int i = 0; i < 10; i++) {
            String nxSt = markPre.predictNextState(curSt);
            System.out.println("Prediction " + (i + 1) + ": " + nxSt);

            curSt = nxSt;
        }

        System.out.println("\n--- Activity Predictions ---");
        MarkovPrediction markPre2 = new MarkovPrediction("activites.csv");
        String curSt2 = "Sleeping";
        for (int i = 0; i < 10; i++) {
            String nxSt = markPre2.predictNextState(curSt2);
            System.out.println("Prediction " + (i + 1) + ": " + nxSt);

            curSt2 = nxSt;
        }
    }
}