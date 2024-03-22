import java.io.FileWriter;
import java.io.IOException;

/*
Użycie FileWriter, ponieważ FileWriter automatycznie zarządza zasobami, w tym przypadku plikiem. Oznacza to, że automatycznie zamknie plik.
 */
public class DataWriter {

    public static void clearFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            // Samo otwarcie pliku w trybie nadpisywania, aby go wyczyścić
        } catch (IOException e) {
            System.err.println("Nie udało się wyczyścić pliku " + e.getMessage());
        }
    }
    public static void saveResults(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // Parametr 'true' oznacza, że dane będa zapisywane do pliku
            writer.write(data + "\n");
        } catch (IOException e) {
            System.err.println("Wystąpił błąd przy zapisie do pliku " + e.getMessage());
        }
    }
}
