import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args)  {
        Task2 task2 = new Task2();
        Circle circle = task2.getCircle(new File(args[0]));
        List<Point> points = task2.getPoints(new File(args[1]));
        points.stream().forEach(point -> System.out.println(circle.result(point)));
    }

    public Circle getCircle(File file) {
        float x, y, radius;
        try (Scanner scanner = new Scanner(file)) {
            x = Float.parseFloat(scanner.next());
            y = Float.parseFloat(scanner.next());
            radius = Float.parseFloat(scanner.next());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException("Error file: " + file.getName());
        }
        return new Circle(x, y, radius);
    }

    public List<Point> getPoints(File file)  {
        String[] args;
        List<Point> points = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                args = scanner.nextLine().split(" ");
                points.add(new Point(Float.parseFloat(args[0]), Float.parseFloat(args[1])));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException("Error file: " + file.getName());
        }
        return points;
    }
}