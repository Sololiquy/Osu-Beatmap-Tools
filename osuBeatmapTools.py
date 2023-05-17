import math

Redundant = 0

area = 'Default'
previousVolume = "100"
previousSample = "0"
previousType = "1"
previousInheret = "1"

beforeT = ""
currentT = ""
st = ""

File = ['','','','','','','','','']
File[0] = 'afterpy.osu'
File[1] = 'Sub/General.txt'
File[2] = 'Sub/Editor.txt'
File[3] = 'Sub/Metadata.txt'
File[4] = 'Sub/Difficulty.txt'
File[5] = 'Sub/Events.txt'
File[6] = 'Sub/TimingPoints.txt'
File[7] = 'Sub/Colours.txt'
File[8] = 'Sub/HitObjects.txt'

for x in range(9):
  open(File[x], 'w').close()

with open('before.osu', 'r', errors='ignore') as infile:
    for line in infile:
        line = line.strip()

        areas = {
        '[General]': 'General',
        '[Editor]': 'Editor',
        '[Metadata]': 'Metadata',
        '[Difficulty]': 'Difficulty',
        '[Events]': 'Events',
        '[TimingPoints]': 'TimingPoints',
        '[Colours]': 'Colours',
        '[HitObjects]': 'HitObjects'
        }

        if '[' in line:
            area = areas[line]
            # print(area)
        elif line == '':
            x = 0
        else:
            if area == 'Default':
                with open(File[0], 'a') as inFile:
                    inFile.write('osu file format v14 \n\n')
            elif area == 'General':
                with open(File[1], 'a') as inFile:
                    inFile.write(str(line)+'\n')
            elif area == 'Editor':
                with open(File[2], 'a') as inFile:
                    inFile.write(str(line)+'\n')
            elif area == 'Metadata':
                with open(File[3], 'a') as inFile:
                    inFile.write(str(line)+'\n')
            elif area == 'Difficulty':
                with open(File[4], 'a') as inFile:
                    inFile.write(str(line)+'\n')
            elif area == 'Events':
                with open(File[5], 'a') as inFile:
                    inFile.write(str(line)+'\n')
            elif area == 'TimingPoints':
                if line.strip():
                    lineTimingPoint = line.split(',')
                    rounded_number = math.floor(float(lineTimingPoint[0]))
                    lineTimingPoint[0] = str(rounded_number)

                    if lineTimingPoint[6] == '1':
                        previousVolume = lineTimingPoint[5]
                        previousSample = lineTimingPoint[4]
                        previousType = lineTimingPoint[3]
                        # newLineUnhiretion = ','.join(map(str, lineTimingPoint))
                        # with open(File[6], 'a') as inFile:
                        #     inFile.write(str(newLineUnhiretion)+'\n')
                        # lineTimingPoint[6] = 0
                        newLine = ','.join(map(str, lineTimingPoint))
                    elif lineTimingPoint[6] == '0':
                        if lineTimingPoint[3] == '1' and lineTimingPoint[4] == '0' and lineTimingPoint[5] == '100':
                            lineTimingPoint[5] = previousVolume
                            lineTimingPoint[4] = previousSample
                            lineTimingPoint[3] = previousType
                        previousVolume = lineTimingPoint[5]
                        previousSample = lineTimingPoint[4]
                        previousType = lineTimingPoint[3]
                        newLine = ','.join(map(str, lineTimingPoint))

                    # Redundant Check    
                    if beforeT == '':
                        with open(File[6], 'a') as inFile:
                            inFile.write(str(newLine)+'\n')
                        beforeT = newLine
                    elif beforeT != '':
                        beforeTD = beforeT.split(',')
                        currentTD = newLine.split(',')
                        if (beforeTD[1] == currentTD[1] and beforeTD[2] == currentTD[2] and beforeTD[3] == currentTD[3] and beforeTD[4] == currentTD[4] and beforeTD[5] == currentTD[5] and beforeTD[6] == currentTD[6] and beforeTD[7] == currentTD[7] ):
                            beforeT = newLine
                            Redundant = Redundant + 1
                        else:
                            beforeT = newLine
                            with open(File[6], 'a') as inFile:
                                inFile.write(str(newLine)+'\n')
            elif area == 'Colours':
                with open(File[7], 'a') as inFile:
                    inFile.write(str(line)+'\n')
            elif area == 'HitObjects':
                with open(File[8], 'a') as inFile:
                    inFile.write(str(line)+'\n')
print('Redundant = '+str(Redundant))

for y in range(1,9):
    strings = {
        1: '[General]\n',
        2: '[Editor]\n',
        3: '[Metadata]\n',
        4: '[Difficulty]\n',
        5: '[Events]\n',
        6: '[TimingPoints]\n',
        7: '[Colours]\n',
        8: '[HitObjects]\n',
    }

    with open(File[0], 'a') as inFile:
        inFile.write(strings[y])

    with open(File[y], 'r') as infile:
        for line in infile:
            line = line.strip()
            newLine = line

            with open(File[0], 'a') as inFile:
                inFile.write(str(newLine)+'\n')
    with open(File[0], 'a') as inFile:
                inFile.write('\n')
            