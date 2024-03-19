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

    }


//    private int calculate_accuracy(int[][] X_test, int[] Y_test) {
//
//    }
}
