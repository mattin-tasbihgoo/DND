import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree.
 * A directory can contain other directories and files as children.
 */
public class FolderNode extends FileSystemNode {

    private final List<FileSystemNode> children;

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
        if (childName == null)
            return null;
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
        if (getChildByName(fileName) != null || fileName == null || fileName.trim().isEmpty() || size < 0)
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
        if (getChildByName(folderName) != null || folderName == null || folderName.trim().isEmpty())
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
        for (FileSystemNode child : children) {
            if (child.getName().equals(searchName))
                return true;
            if (child.isFolder()) {
                if (((FolderNode) child).containsNameRecursive(searchName))
                    return true;
            }
        }
        return false;
    }

    @Override
    public int getHeight() {
        int max = 0;
        for (FileSystemNode child : children) {
            int count = 1 + child.getHeight();
            if (count > max)
                max = count;
        }
        return max;
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemNode child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }

    @Override
    public int getTotalNodeCount() {
        int totalCount = 1; // count this folder
        for (FileSystemNode child : children) {
            totalCount += child.getTotalNodeCount(); // recurse into the folder
        }
        return totalCount;
    }
}
