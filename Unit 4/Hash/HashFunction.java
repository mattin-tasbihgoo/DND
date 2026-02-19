public class HashFunction {
    private static final int N = 500;
    private final int norm = 3;
    private static final int P = 1_000_003;
    private final String[] hash = new String[N];

    public HashFunction(String[] names) {
        if (names.length > N)
            throw new IllegalArgumentException("Too many names bucko");

        for (String name : names) {
            int i = hashIndex(name, norm);
            if (hash[i] != null && !hash[i].equals(name))
                throw new IllegalStateException("Collision detected for " + name + " and " + hash[i]);
            hash[i] = name;
        }
    }

    private static int hashIndex(String name, int norm) {
        int mod = 0;
        for (int i = 0; i < name.length(); i++) {
            int normalized = (name.charAt(i) + norm); // % 128 to normalize to asci range?
            mod = (mod * 1000 + normalized) % P;
        }
        return mod % N;
    }

    public int getNorm() {
        return norm;
    }

    public int id(String name) {
        int i = hashIndex(name, norm);
        return name.equals(hash[i]) ? i : -1;
    }

    public static void main(String[] args) {
        String[] names = {
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
                "Carter Tsao",
                "Rose Ananda",
                "Autrin Anousheh",
                "Joshua Bie",
                "Elsa Cheng",
                "Isabel Erlic",
                "Jojo Gott",
                "Connor Jun",
                "Jordan Kay",
                "James Klarin",
                "Judy Law",
                "Grayden Lichtman",
                "Runshi Liu",
                "Juan Lopez",
                "Henry Margolis",
                "Garret Morberg-Nguyen",
                "Kai Nantamanasikarn",
                "Remi O'Dell",
                "Emil Palmer",
                "Jaden Park",
                "Ryder Rufi",
                "Alice Shao",
                "Marco Silvera",
                "Samuel Tabib",
                "Andrew Theiss",
                "Shriya Vishwas",
                "Nick Waller",
                "Vikram Wright",
                "Alex Yang",
                "Ethan You",
                "Lucas Yu",
                "Jack Yuan",
                "Jayden Zepeda",
                "Lawrence Zhao",
                "Michael Zhao",
                "Olivia Zhu"
        };

        HashFunction hf = new HashFunction(names);
        System.out.println("norm = " + hf.getNorm());

        for (String n : names) {
            System.out.printf("%3d  %s%n", hf.id(n), n);
        }

        System.out.println("John Doe: " + hf.id("John Doe")); // -1 (not in set)
    }
}