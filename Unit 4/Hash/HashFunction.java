import java.util.ArrayList;

public class HashFunction {
    private static String[] str = {
            "Mateo Atluri",
            "Asher Butan",
            "Xander Cheuk",
            "Taj Clement",
            "Camille Condren",
            "Evan Daneshrad",
            "Felicia Duan",
            "Jake Effress",
            "Zachary Figlin",
            "James Graczyk",
            "David Hadi",
            "Quinn Harris",
            "Jackson Hubbard",
            "Siona Kirschner",
            "Dylan Martin",
            "Morgan Maynard",
            "Yari Milakin",
            "Waller Morton",
            "Andrew Stout",
            "Mattin Tasbihgoo",
            "Andrew Theiss",
            "Carter Tsao"
    };

    public static void main(String[] args) {
        int i = helper(str[0]);
        System.out.println(normalization(i));
    }

    public HashFunction(String[] str) {
        this.str = str;
    }

    public static int helper(String str) {
        char[] chars = str.toCharArray();
        String sum = "";
        for (char c : chars) {
            sum += c;
        }

        return Integer.parseInt(sum);
    }

    public static int normalization(int i) {
        double max = 1111111111111111111111.0;
        double normalized = (max - i) / max * 500;
        return (int) normalized;
    }
}
