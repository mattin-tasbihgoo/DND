public class ChocolateHashMapTester {
    public static void main(String[] args) {

        ChocolateHashMap<String, ChocolateBatch> i = new ChocolateHashMap<>(5, 0.75);

        System.out.println("isEmpty(): " + i.isEmpty());
        System.out.println("size(): " + i.size());
        System.out.println("getBuckets().length: " + i.getBuckets().length);
        System.out.println("currentLoadFactor(): " + i.currentLoadFactor());
        System.out.println("containsKey(SKU-A): " + i.containsKey("SKU-A"));
        System.out.println("containsValue(Truffle...): " +
                i.containsValue(new ChocolateBatch("Truffle Box", 72, "Ecuador", 120)));

        ChocolateBatch a = new ChocolateBatch("Truffle Box", 72, "Ecuador", 120);
        boolean addedA = i.put("SKU-A", a);
        System.out.println("put(SKU-A, batch): " + addedA);
        boolean addedADup = i.put("SKU-A", new ChocolateBatch("Other", 50, "Ghana", 1));
        System.out.println("put duplicate key SKU-A: " + addedADup);
        System.out.println("containsKey(SKU-A): " + i.containsKey("SKU-A"));
        System.out.println("containsValue(a): " + i.containsValue(a));
        System.out.println("get(SKU-A): " + i.get("SKU-A"));
        System.out.println("remove(SKU-A): " + i.remove("SKU-A"));
        System.out.println("remove(SKU-A) again: " + i.remove("SKU-A"));
        System.out.println("toString(): " + i.toString());

        i.put("SKU-B", new ChocolateBatch("70% Dark Bar", 70, "Ghana", 500));
        i.put("SKU-C", new ChocolateBatch("White Bar", 30, "Peru", 250));

        System.out.println("Before rehash toString(): " + i.toString());
        i.rehash(20);
        System.out.println("After rehash toString(): " + i.toString());
        System.out.println("currentLoadFactor() after rehash: " + i.currentLoadFactor());
    }
}