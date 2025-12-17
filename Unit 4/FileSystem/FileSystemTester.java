
/**
 * Small manual tester for the solution file-system implementation.
 *
 * This does NOT use JUnit. It just runs a few operations and prints out
 * what it's doing plus the observed behavior.or
 *
 * And also assumes (based on our design):
 * - FileSystemTree#getRoot() returns a non-null FolderNode
 * - FolderNode has addFolder(String) and addFile(String, int) that return
 * boolean
 * - FileSystemNode has getDepth(), getHeight(), getSize(), getTotalNodeCount()
 * - Navigator has processUserInputString(String) which prints results to
 * System.out
 */
public class FileSystemTester {

    public static void main(String[] args) {

        // 1. Construct a tree and check root
        FileSystemTree tree = new FileSystemTree();
        FolderNode root = tree.getRoot();

        root.addFolder("docs");
        root.addFolder("src");

        FolderNode docs = (FolderNode) root.getChildByName("docs");
        FolderNode src = (FolderNode) root.getChildByName("src");

        docs.addFile("notes.txt", 100);
        src.addFile("Main.java", 120);

        System.out.println("Direct method checks");
        System.out.println("root depth: " + root.getDepth()); // expect 0
        System.out.println("root height: " + root.getHeight()); // expect 2 (root->docs->notes.txt)
        System.out.println("root size: " + root.getSize()); // expect 220
        System.out.println("root total nodes: " + root.getTotalNodeCount()); // expect 5 (root, docs, src, notes, Main)

        System.out.println("\nNavigator command checks");
        Navigator nav = new Navigator(tree);

        nav.processUserInputString("pwd");
        System.out.println();
        nav.processUserInputString("ls");
        System.out.println();
        nav.processUserInputString("tree");
        System.out.println();

        nav.processUserInputString("cd docs");
        System.out.println();
        nav.processUserInputString("pwd");
        System.out.println();
        nav.processUserInputString("ls");
        System.out.println();
        nav.processUserInputString("size"); // expect 100
        System.out.println();
        nav.processUserInputString("count"); // expect 1 (notes.txt)
        System.out.println();

        nav.processUserInputString("cd /src");
        System.out.println();
        nav.processUserInputString("pwd");
        System.out.println();
        nav.processUserInputString("ls");
        System.out.println();
        nav.processUserInputString("size"); // expect 120
        System.out.println();
        nav.processUserInputString("count"); // expect 1 (Main.java)

        System.out.println("\n find checks (from /src)");
        nav.processUserInputString("find Main.java"); // should print the path to Main.java
        System.out.println();
        nav.processUserInputString("cd /");
        System.out.println();
        nav.processUserInputString("find notes.txt"); // should print the path to notes.txt
        System.out.println();

/*         Navigator nav = new Navigator(tree);
        nav.processUserInputString("");

        if (root == null) {
            System.out.println("[FAIL] Root is null. FileSystemTree.getRoot() must return a non-null root folder.");
            return;
        } else {
            System.out.println("[PASS] Root is non-null.");
        }

        System.out.println("Root toString(): " + root.toString());
        System.out.println("Expected at root: '/' (or equivalent)");

        // 2. Build a small structure under root
        System.out.println("\n=== Building tree structure under root ===");
        boolean addedDocs = root.addFolder("docs");
        boolean addedSrc = root.addFolder("src");
        boolean addedMainJava = root.addFile("main.java", 120);
        boolean addedReadme = root.addFile("README.md", 80);

        int depthRoot = root.getDepth();
        int heightRoot = root.getHeight();
        int sizeRoot = root.getSize();
        int totalNodesRoot = root.getTotalNodeCount(); */
    }
}