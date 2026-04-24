     class HuffmanNode {
        char ch;
        int freq;
        HuffmanNode left, right;

        // Leaf node
        HuffmanNode(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        // Internal node
        HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
            this.ch = '\0';
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }
    }