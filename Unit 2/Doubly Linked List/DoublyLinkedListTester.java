public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        System.out.println(list + " size=" + list.size() + " empty=" + list.isEmpty());

        // add(E)
        list.add(Nucleotide.A); list.add(Nucleotide.G); list.add(Nucleotide.T); list.add(Nucleotide.C);
        System.out.println(list);

        // add(index, E)
        list.add(1, Nucleotide.C);
        System.out.println(list);

        // get / set
        System.out.println("get(2)=" + list.get(2));
        System.out.println("set(2,A) old=" + list.set(2, Nucleotide.A));
        System.out.println(list);

        // contains / indexOf
        System.out.println("contains G? " + list.contains(Nucleotide.G));
        System.out.println("indexOf T = " + list.indexOf(Nucleotide.T));

        // remove(value) / remove(index)
        System.out.println("remove(G) -> " + list.remove(Nucleotide.G));
        System.out.println(list);
        System.out.println("remove(0) -> " + list.remove(0));
        System.out.println(list);

        // array constructor + addSegmentToEnd
        DoublyLinkedList seg = new DoublyLinkedList(new Nucleotide[]{ Nucleotide.A, Nucleotide.A, Nucleotide.C });
        list.addSegmentToEnd(seg);
        System.out.println("after addSegmentToEnd: " + list);

        // deleteSegment
        System.out.println("deleteSegment(seg) -> " + list.deleteSegment(seg));
        System.out.println(list);

        // deleteLastThree
        System.out.println("deleteLastThree -> " + list.deleteLastThree());
        System.out.println(list);

        // replaceEveryAWithTAC (ensure some As exist first)
        list.add(Nucleotide.A); list.add(Nucleotide.T); list.add(Nucleotide.A);
        System.out.println("seeded: " + list);
        list.replaceEveryAWithTAC();
        System.out.println("after replaceEveryAWithTAC: " + list);

        // removeCCCCCCCCGGGGGGGG
        DoublyLinkedList pattern = new DoublyLinkedList();
        pattern.add(Nucleotide.A);
        for (int i = 0; i < 8; i++) pattern.add(Nucleotide.C);
        for (int i = 0; i < 8; i++) pattern.add(Nucleotide.G);
        pattern.add(Nucleotide.T);
        System.out.println("pattern before: " + pattern);

        // nodeBefore = node whose next is the first C
        ListNode2<Nucleotide> nodeBefore = pattern.getHead();
        while (nodeBefore.getNext() != pattern.getSentinel() &&
               nodeBefore.getNext().getValue() != Nucleotide.C) {
            nodeBefore = nodeBefore.getNext();
        }
        pattern.removeCCCCCCCCGGGGGGGG(nodeBefore);
        System.out.println("pattern after: " + pattern);

        System.out.println("done.");
    }
}
