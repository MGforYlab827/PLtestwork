import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task4 {

    public static void main(String[] args) throws IOException {
        Task4 task4 = new Task4();
        List<Long> sortedList = task4.getSortedList(new File(args[0]));
        System.out.print(task4.getStepCount(sortedList));
    }

    public long getStepCount(List<Long> list) {
        long step = 0;
        long median;
        if(list.size() % 2 == 1) {
            median = list.get(list.size() / 2);
        } else {
            median = (long)(0.5 * (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)));
        }
        for (long value : list) {
            step += Math.abs(value - median);
        }
        return step;
    }

    public List<Long> getSortedList(File file) throws IOException {
        List<Long> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                list.add(Long.parseLong(scanner.next()));
            }
        }
        return list.stream().sorted().collect(Collectors.toList());
    }
}
