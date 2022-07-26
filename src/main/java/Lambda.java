import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Lambda {

    private static String getFileSizeMegaBytes(File file) {
        return (double) file.length()/(1024*1024)+" mb";
    }


    public static void main(String[] args) {
        File file = new File("1.txt");

        try (Reader reader = new InputStreamReader(new FileInputStream(file))){

            char[] array = new char[1024];
            int count = reader.read(array);
            StringBuilder result = new StringBuilder();
            while (count > 0) {
                result.append(new String(array,0, count));
                count = reader.read(array);
            }

            System.out.println(getFileSizeMegaBytes(file));
//            System.out.println("Number of characters in text: " + result.length());

            Scanner scanner = new Scanner(file);
            int words = 0;
            while (scanner.hasNextLine()) {
                String[] array1 = scanner.nextLine().split(" ");
                words = words + array1.length;
            }
            System.out.println("Number of words: " + words + "\n");
            scanner.close();


            String[] names = result.toString().split(" ");
            Arrays.stream(names)
                    .filter(name -> name.length() > 5)
                    .map(name -> name.replaceAll("[.|,|!]", ""))
                    .sorted()
                    .forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
