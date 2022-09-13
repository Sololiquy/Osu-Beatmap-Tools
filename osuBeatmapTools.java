import java.lang.Math;
import java.io.*;

public class osuBeatmapTools {
    double Offset;
    int intOffset;
    String Area = "";
    String pt = "";
    String previousVolume = "100";
    String previousSample = "0";
    String previousType = "1";
    String previousInheret = "1";
    String beforeT = "";
    String currentT = "";

    public osuBeatmapTools() {
        try {
            // Step 1 : File Connection

            // CODE ARRAY:
            // General = 0
            // Editor = 1
            // Metadata = 2
            // Difficulty = 3
            // Events = 4
            // TimingPoints = 5
            // Colours = 6
            // HitObjects = 7
            File[] beatmap = new File[8];
            File input = new File("before.txt");
            beatmap[0] = new File("Sub/General.txt");
            beatmap[1] = new File("Sub/Editor.txt");
            beatmap[2] = new File("Sub/Metadata.txt");
            beatmap[3] = new File("Sub/Difficulty.txt");
            beatmap[4] = new File("Sub/Events.txt");
            beatmap[5] = new File("Sub/TimingPoints.txt");
            beatmap[6] = new File("Sub/Colours.txt");
            beatmap[7] = new File("Sub/HitObjects.txt");

            BufferedReader read = new BufferedReader(new FileReader(input));

            // STEP 1 : Separator
            String st;
            FileWriter[] write = new FileWriter[8];
            BufferedWriter[] write2 = new BufferedWriter[8];
            PrintWriter[] delete = new PrintWriter[8];
            for (int i = 0; i <= 7; i++) {
                write[i] = new FileWriter(beatmap[i], true);
                write2[i] = new BufferedWriter(write[i]);
                delete[i] = new PrintWriter(beatmap[i]);
                delete[i].close();
            }

            while ((st = read.readLine()) != null) {
                if (Area.equals("General")) {
                    write2[0].write(st);
                    write2[0].newLine();
                    write2[0].flush();
                }
                if (Area.equals("Editor")) {
                    write2[1].write(st);
                    write2[1].newLine();
                    write2[1].flush();
                }
                if (Area.equals("Metadata")) {
                    write2[2].write(st);
                    write2[2].newLine();
                    write2[2].flush();
                }
                if (Area.equals("Difficulty")) {
                    write2[3].write(st);
                    write2[3].newLine();
                    write2[3].flush();
                }
                if (Area.equals("Events")) {
                    write2[4].write(st);
                    write2[4].newLine();
                    write2[4].flush();
                }
                if (Area.equals("TimingPoints")) {
                    if (st.isEmpty() == false) {
                        String[] lineTimingPoint = st.split(",");
                        Offset = Double.parseDouble(lineTimingPoint[0]);
                        Offset = Math.floor(Offset);
                        intOffset = (int) Offset;
                        lineTimingPoint[0] = Integer.toString(intOffset);

                        if (lineTimingPoint[6].equals("1")) {
                            previousVolume = lineTimingPoint[5];
                            previousSample = lineTimingPoint[4];
                            previousType = lineTimingPoint[3];
                        }
                        if (lineTimingPoint[6].equals("0")) {
                            if (lineTimingPoint[3].equals("1") &&
                                    lineTimingPoint[4].equals("0") &&
                                    lineTimingPoint[5].equals("100")) {
                                lineTimingPoint[5] = previousVolume;
                                lineTimingPoint[4] = previousSample;
                                lineTimingPoint[3] = previousType;
                            }
                            previousVolume = lineTimingPoint[5];
                            previousSample = lineTimingPoint[4];
                            previousType = lineTimingPoint[3];
                        }

                        String temp = "";
                        for (int j = 0; j < 8; j++) {
                            temp = temp + lineTimingPoint[j];
                            if (j != 7) {
                                temp = temp + ",";
                            }
                        }

                        // REDUNDANT CHECK

                        String[] beforeTD = beforeT.split(",");
                        String[] currentTD = temp.split(",");
                        int a = Integer.parseInt(beforeTD[0]);
                        int b = Integer.parseInt(currentTD[0]);
                        if (a != b && a + 1 != b &&
                                !lineTimingPoint[1].equals(lineTimingPoint2[1]) ||
                                !lineTimingPoint[2].equals(lineTimingPoint2[2]) ||
                                !lineTimingPoint[3].equals(lineTimingPoint2[3]) ||
                                !lineTimingPoint[4].equals(lineTimingPoint2[4]) ||
                                !lineTimingPoint[5].equals(lineTimingPoint2[5]) ||
                                !lineTimingPoint[6].equals(lineTimingPoint2[6]) ||
                                !lineTimingPoint[7].equals(lineTimingPoint2[7])) {
                            System.out.print(i + " added ");
                            TimingPoint2[i] = TimingPoint[j];
                            i++;
                        }

                        beforeT = temp;
                        st = temp;
                    }

                    write2[5].write(st);
                    write2[5].newLine();
                    write2[5].flush();
                }
                if (Area.equals("Colours")) {
                    write2[6].write(st);
                    write2[6].newLine();
                    write2[6].flush();
                }
                if (Area.equals("HitObjects")) {
                    write2[7].write(st);
                    write2[7].newLine();
                    write2[7].flush();
                }

                if (st.equals("[General]")) {
                    Area = "General";
                    System.out.println(Area);
                }
                if (st.equals("[Editor]")) {
                    Area = "Editor";
                    System.out.println(Area);
                }
                if (st.equals("[Metadata]")) {
                    Area = "Metadata";
                    System.out.println(Area);
                }
                if (st.equals("[Difficulty]")) {
                    Area = "Difficulty";
                    System.out.println(Area);
                }
                if (st.equals("[Events]")) {
                    Area = "Events";
                    System.out.println(Area);
                }
                if (st.equals("[TimingPoints]")) {
                    Area = "TimingPoints";
                    System.out.println(Area);
                }
                if (st.equals("[Colours]")) {
                    Area = "Colours";
                    System.out.println(Area);
                }
                if (st.equals("[HitObjects]")) {
                    Area = "HitObjects";
                    System.out.println(Area);
                }
                if (st.isEmpty() == true) {
                    Area = "a";
                }

            }

            // STEP 2 : OUTPUT

            File output = new File("after.txt");
            FileWriter write3 = new FileWriter(output, true);
            BufferedWriter write4 = new BufferedWriter(write3);

            PrintWriter delete2 = new PrintWriter(output);
            delete2.close();

            BufferedReader[] read2 = new BufferedReader[8];
            write4.write("osu file format v14");
            write4.newLine();
            write4.write("");
            write4.newLine();
            for (int j = 0; j <= 7; j++) {
                read2[j] = new BufferedReader(new FileReader(beatmap[j]));
                while ((st = read2[j].readLine()) != null) {
                    write4.write(st);
                    write4.newLine();
                }
            }
            write4.flush();
            write3.close();
            write4.close();
        } catch (Exception e) {
            System.out.println("ERROR");
        }

    }

    public static void main(String[] args) {
        osuBeatmapTools x = new osuBeatmapTools();
    }
}
