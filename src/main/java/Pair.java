public class Pair {
    private final int key;
    private final double value;

    public Pair(int key, double value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() { return key; } //inline konwencja zapisywania jednolinijkowa

    public double getValue() { return value; }
}
