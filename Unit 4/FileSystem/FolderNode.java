import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree.
 * A directory can contain other directories and files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(String name, FolderNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Returns a list view of the children contained directly inside this directory.
     * Modifying the returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        return children;
    }

    /**
     * Searches the children of this directory for a node whose name matches the
     * input.
     * Only direct children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        for (FileSystemNode fileSystemNode : children) {
            if (fileSystemNode.getName().equals(childName)) {
                return fileSystemNode;
            }
        }
        return null;
    }

    /**
     * Creates a new file directly inside this directory with the given name and
     * size.
     * If a child with the same name already exists, no file is created and false is
     * returned.
     * Otherwise the new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        if (getChildByName(fileName) != null)
            return false;
        children.add(new FileNode(this, fileName, size));
        return true;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given
     * name.
     * If a child with the same name already exists, no folder is created and false
     * is returned.
     * Otherwise the new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        if (getChildByName(folderName) != null)
            return false;
        children.add(new FolderNode(folderName, this));
        return true;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name
     * matches the input.
     * When a match is found, its full path can be printed by the caller using
     * toString().
     */
    public boolean containsNameRecursive(String searchName) {
        if (children.size() == 0)
            return false;
        for (FileSystemNode child : children) {
            if (getChildByName(searchName) != null)
                return true;
            if (child.isFolder()) {
                FolderNode temp = (FolderNode) child;
                temp.containsNameRecursive(searchName);
            }
        }
        return false;
    }

    @Override
    public int getHeight() {
        int height = 0;
        for (FileSystemNode child : children) {
            if (child.getHeight() > height) {
                height = child.getHeight();
            }
        }

        // TODO: compute the maximum height among children; empty folders have value 0
    }

    public int helper(FileSystemNode temp) {
        if (temp.isFolder()) {
            return 0;
        }
        int max = 0;

    }

    @Override
    public int getSize() {
        // TODO: sum the sizes of all files contained in this directory and its
        // descendants
        return 0;
    }

    @Override
    public int getTotalNodeCount() {
        // TODO: count this directory plus all descendant files and folders
        return 0;
    }
}
