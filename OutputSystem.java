import java.lang.Math;
import java.io.*;

public class OutputSystem extends osu {
    public OutputSystem() {
        try {
            File[] beatmap = new File[8];
            beatmap[0] = new File("Sub/General.txt");
            beatmap[1] = new File("Sub/Editor.txt");
            beatmap[2] = new File("Sub/Metadata.txt");
            beatmap[3] = new File("Sub/Difficulty.txt");
            beatmap[4] = new File("Sub/Events.txt");
            beatmap[5] = new File("Sub/TimingPoints.txt");
            beatmap[6] = new File("Sub/Colours.txt");
            beatmap[7] = new File("Sub/HitObjects.txt");

            File output = new File("after.osu");
            FileWriter write3 = new FileWriter(output, true);
            BufferedWriter write4 = new BufferedWriter(write3);

            PrintWriter delete2 = new PrintWriter(output);
            delete2.close();

            BufferedReader[] read2 = new BufferedReader[8];
            write4.write("osu file format v14");
            write4.newLine();
            write4.newLine();
            write4.write("[General]");
            write4.newLine();
            for (int j = 0; j <= 7; j++) {
                read2[j] = new BufferedReader(new FileReader(beatmap[j]));
                while ((st = read2[j].readLine()) != null) {
                    write4.write(st);
                    write4.newLine();
                }
                write4.newLine();
                switch (j) {
                    case 0:
                        write4.write("[Editor]");
                        break;
                    case 1:
                        write4.write("[Metadata]");
                        break;
                    case 2:
                        write4.write("[Difficulty]");
                        break;
                    case 3:
                        write4.write("[Events]");
                        break;
                    case 4:
                        write4.write("[TimingPoints]");
                        break;
                    case 5:
                        write4.write("[Colours]");
                        break;
                    case 6:
                        write4.write("[HitObjects]");
                        break;
                }
                write4.newLine();
            }
            write4.flush();
            write3.close();
            write4.close();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
