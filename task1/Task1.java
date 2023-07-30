public class Task1 {

    public static String getPath(int length, int internal) {
        StringBuffer path = new StringBuffer();
        int value = 0;
         do {
            path.append(value + 1);
            value = (value + internal - 1) % length;
        } while (value != 0);
        return path.toString();
    }
    public static void main(String[] args) {
        System.out.print(getPath(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}