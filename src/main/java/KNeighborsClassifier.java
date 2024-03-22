import java.util.*;


public class KNeighborsClassifier {

    private final int n_neighbors;
    private final Metrics metric;
    private double[][] X_train;
    private int[] Y_train;


    public KNeighborsClassifier(int n_neighbors, Metrics metric) {
        this.n_neighbors = n_neighbors;
        this.metric = metric;
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

    public int[] predict(double[][] X_test) {
        int[] predictions = new int[X_test.length]; // Tablica na przewidywane efekty

        for (int i = 0; i < X_test.length; i++) {
            List<Pair> distances = new ArrayList<>();


            // Obliczanie odległości od wszystkich próbek treningowych
            for (int j = 0; j < X_train.length; j++) {
                double distance = calucalteDistance(X_test[i], X_train[j], metric);
                distances.add(new Pair(j, distance));
            }
            // Sortowanie listy odległośći ASCENDING
            distances.sort(Comparator.comparing(Pair::getValue));

            // Wybieranie k-najbliższych sąsiadów dla danej próbki testowej
            List<Integer> nearestLabels = new ArrayList<>();
            for (int k = 0; k < n_neighbors; k++) {
                int indexOfNearest = distances.get(k).getKey(); // get aby otrzymać z listy distances k-ty element, klucz z pary
                nearestLabels.add(Y_train[indexOfNearest]);
            }

            // Głosowanie na najczęstsza etykietę wśród k-najbliższych sąsiadów
            int predictedLabel = getMostCommonLabel(nearestLabels);
            predictions[i] = predictedLabel; // Przypisanie przewidzianej etykiety
        }

        return predictions;
    }

    public double calculate_accuracy(double[][] X_test, int[] Y_test) {
        int[] predictions = predict(X_test);
        int correctPredictions = 0;

        for (int i = 0; i < predictions.length; i++) {
            if (predictions[i] == Y_test[i]) { // Porównanie przewidywanej etykiety z rzeczywistością
                correctPredictions++;
            }
        }
        double accuracy = ((double) correctPredictions / X_test.length);
        System.out.println(correctPredictions);
        return accuracy; //Obliczenie i zwrócenie dokładności modelu
    }
    /*
    Dokładność obliczam jako stosunek poprawnych przewidywań do całkowitej liczby próbek, w tym przypadku przy dobrym wczytaniu
    danych (pomijanie nagłówków) długość tablicy to wszystkie próbki.
     */


    private double calucalteDistance(double[] testSample, double[] trainSample, Metrics metric) {
        double distance = 0.0;
        return switch (metric) {
            case EUCLIDEAN -> euclidianDistance(testSample, trainSample, distance);
            case MANHATTAN -> manhattanDistance(testSample, trainSample, distance);
        };
    }

    private double manhattanDistance(double[] testSample, double[] trainSample, double distance) {
        for (int i = 0; i < testSample.length; i++) {
            distance += Math.abs(testSample[i] - trainSample[i]);
        }
        return distance;
    }

    private double euclidianDistance(double[] testSample, double[] trainSample, double distance) {
        for (int i = 0; i < testSample.length; i++) {
            distance += Math.pow(testSample[i] - trainSample[i], 2);
        }
        return Math.sqrt(distance);
    }

    private int getMostCommonLabel(List<Integer> labels) {
        Map<Integer, Integer> labelCount = new HashMap<>(); // Kluczem jest etykieta, a wartością jej liczba wystąpień
        for (int label : labels) {
            labelCount.put(label, labelCount.getOrDefault(label, 0) + 1);
        }
        // getOrDeafult sprawdza czy etykieta jest w już w mapie, jeśli nie ma to ustawia na 0 i dodaje 1, jeśli jest to wtedy ta wartość + 1 (liczba wystąpień)

        int mostCommon = labels.get(0); // Zaczynamy od pierwszej etykiety
        for (Map.Entry<Integer, Integer> entry : labelCount.entrySet()) {
            if (entry.getValue() > labelCount.get(mostCommon)) { //mostCommon jest kluczem, więc użycie get(mostCommon) spowoduje dostanie wartości pod tym kluczem
                mostCommon = entry.getKey();
            }
        }
        return mostCommon;
    }
}




