import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNeighboursClassifier {

    private int n_neighbours;
    private Metrics metric;
    private String filePath;
    private double[][] X_train;
    private int[] Y_train;

    public int getN_neighbours() {
        return n_neighbours;
    }

    public Metrics getMetric() {
        return metric;
    }

    public String getFilePath() {
        return filePath;
    }

    public KNeighboursClassifier(int n_neighbours, Metrics metric, String filePath) {
        this.n_neighbours = n_neighbours;
        this.metric = metric;
        this.filePath = filePath;
    }

    /**
     * Metoda fit odpowiedzialna za zdefiniowanie referencyjnej przestrzeni cech etykietowanych próbek,
     * w celu wykorzystania jej podczas predykcji.
     * Single Responsibility Principle.
     * @param X_train tablica2D typu double - typ double, dlatego, że, wiele cech takich jak wielkości fizycznie wymaga precyzynej
     *                reprezentacji.
     * @param Y_train tablica1D typu int - etykiety klas reprezentowane jako liczby całkowite - każda unikalna liczba reperezentuje
     *                kolejne klasy.
     */

    /* Zastanawiałem się, nad tym dlaczego nie można zawrzec takiej prostej metody w konstruktorze po prostu, natomiast zrozumiałem, że
        bez sensu za każdym razem tworzyć instancję tego samego obiektu, ale z innymi danymi. Zamiast tego, będzie można ponownie zastosować uczenie klasyfikatora dla nowych danych.
     */

    public void fit(double[][] X_train, int[] Y_train) {
        this.X_train = X_train;
        this.Y_train = Y_train;
    }

    public void predict(int[][] X_test) {
        int[] predictions = new int[X_test.length];
    }

    private double calucalteDistance(double[] testSample, double[] trainSample, Metrics metric) {
        double distance = 0.0;
        switch (metric) {
            case EUCLIDEAN -> {
                for (int i = 0; i < testSample.length; i++) {
                    distance += Math.pow(testSample[i] - trainSample[i], 2);
                }
                return Math.sqrt(distance);
            }
            case MANHATTAN -> {
                for (int i = 0; i < testSample.length; i++) {
                    distance += Math.abs(testSample[i] - trainSample[i]);
                }
                return distance;
            }
            default -> {
                throw new IllegalArgumentException("Nieznana metryka") // Musiałem użyć throw, bez tego, default musiało by coś zwracać, żeby nie było błędu
            }
        }
    }

    private int getMostCommonLabel(List<Integer> labels) {
        Map<Integer, Integer> labelCount = new HashMap<>(); // Kluczem jest etykieta, a wartością jej liczba wystąpień
        for (int label : labels) {
            labelCount.put(label, labelCount.getOrDefault(label, 0) + 1);
        }
        // getOrDeafult sprawdza czy etykieta jest w już w mapie, jeśli nie ma to ustawia na 0 i dodaje 1, jeśli jest to wtedy ta wartość + 1 (liczba wystąpień)

        int mostCommon = labels.get(0); // Zaczynamy od pierwszej etykiety
        for (Map.Entry<Integer, Integer> entry : labelCount.entrySet()) {
            if (entry.getValue() > labelCount.get(mostCommon)) {
                mostCommon = entry.getKey();
            }
        }
        return mostCommon;
    }



//    private int calculate_accuracy(int[][] X_test, int[] Y_test) {
//
//    }
}
