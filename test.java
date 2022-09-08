import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        File Colours = new File("sub/Colours.txt");
        BufferedReader br = new BufferedReader(new FileReader(Colours));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
