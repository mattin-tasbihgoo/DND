public class HuffmanTester {
    public static void main(String[] args) {
        new HuffmanCodeGenerator("Unit 7/test.txt").makeCodeFile("Unit 7/codes.txt");
        new HuffmanEncoder("Unit 7/codes.txt").encodeFile("Unit 7/test.txt");
        new HuffmanDecoder("Unit 7/codes.txt").decodeFile("Unit 7/test.txt.huf");
        System.out.println("Done. Check test.txt.decoded.");
    }
}