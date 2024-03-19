import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    public static ArrayList<double[]> readFeatures(String filepath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<double[]> featuresList = new ArrayList<>();

        // Opcjonalnie pomijamy nagłówek, jeśli istnieje
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (filepath.equals("test_data_cancer.txt") || filepath.equals("train_data_cancer.txt")) {
                double[] features = new double[parts.length - 2];
                for (int i = 0; i < parts.length - 2; i++) {
                    try {
                        features[i] = Double.parseDouble(parts[i + 2]);
                    } catch (NumberFormatException e) {
                        System.err.println("Błąd podczas konwersji ciągu na liczbę CANCER: " + parts[i]);
                    }
                }
                featuresList.add(features);
            } else {
                double[] features = new double[parts.length - 1]; // Ostatni element to etykieta, więc jest pomijany
                for (int i = 0; i < parts.length - 1; i++) {
                    try {
                        features[i] = Double.parseDouble(parts[i]);
                    } catch (NumberFormatException e) {
                        System.err.println("Błąd podczas konwersji ciągu na liczbę: " + parts[i]);
                    }
                }
                featuresList.add(features);
            }


        }
        scanner.close();
        return featuresList;
    }

    /*
    Notatka: Kolekcje z pakietu java.util takie jak np. ArrayList<> nie mogą przechowywać typów prymitywnych jako elementów w kolekcjach
    generycznych. Jednak tablice typów prymitywnych (double[]) tak jak we wcześnieszej metodzie ArrayList są już obiektami.
    W poniższej metodzie korzystam z opakowanego typu Integer (wrapperClass)
     */

    public static ArrayList<Integer> readLabels(String filepath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> labelList = new ArrayList<>();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }



        if (filepath.equals("test_data_cancer.txt") || filepath.equals("train_data_cancer.txt")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                try {
                    Integer label = Integer.parseInt(parts[1]);
                    labelList.add(label);
                } catch (NumberFormatException e) {
                    System.err.println("Błąd podczas konwersji na liczbe INT (plik Cancer).");
                }
            }
        } else {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                try {
                    Integer label = Integer.parseInt(parts[parts.length-1]);
                    labelList.add(label);
                } catch (NumberFormatException e) {
                    System.err.println("Błąd podczas konwersji na liczbę INT (plik Iris) " + parts[parts.length - 1]);
                }
            }
        }
        scanner.close();
        return labelList;
    }
}

