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
    int i = 1;
    int a, b;
    int countcheck = 0;

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
                    write2[0].flush();
                    write2[0].newLine();
                }
                if (Area.equals("Editor")) {
                    write2[1].write(st);
                    write2[1].flush();
                    write2[1].newLine();
                }
                if (Area.equals("Metadata")) {
                    write2[2].write(st);
                    write2[2].flush();
                    write2[2].newLine();
                }
                if (Area.equals("Difficulty")) {
                    write2[3].write(st);
                    write2[3].flush();
                    write2[3].newLine();

                }
                if (Area.equals("Events")) {
                    write2[4].write(st);
                    write2[4].flush();
                    write2[4].newLine();
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

                        if (beforeT.isEmpty() == false) {
                            String[] beforeTD = beforeT.split(",");
                            a = Integer.parseInt(beforeTD[0]);

                            String[] currentTD = temp.split(",");
                            b = Integer.parseInt(currentTD[0]);
                            if (beforeTD[1].equals(currentTD[1]) &&
                                    beforeTD[2].equals(currentTD[2]) &&
                                    beforeTD[3].equals(currentTD[3]) &&
                                    beforeTD[4].equals(currentTD[4]) &&
                                    beforeTD[5].equals(currentTD[5]) &&
                                    beforeTD[6].equals(currentTD[6]) &&
                                    beforeTD[7].equals(currentTD[7]) && a == b || a + 1 == b) {
                                st = temp;
                                beforeT = temp;
                                System.out.print(countcheck + " ");
                                countcheck++;

                            } else {

                                st = temp;
                                beforeT = temp;
                                write2[5].write(st);
                                write2[5].newLine();
                                write2[5].flush();
                            }
                        } else {

                            beforeT = st;
                            write2[5].write(st);
                            write2[5].newLine();
                            write2[5].flush();
                        }

                        i++;
                    }
                }
                if (Area.equals("Colours")) {
                    write2[6].write(st);
                    write2[6].flush();
                    write2[6].newLine();
                }
                if (Area.equals("HitObjects")) {
                    write2[7].write(st);
                    write2[7].flush();
                    write2[7].newLine();
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
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        System.out.println(countcheck);
    }
}
