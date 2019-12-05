input = "6-input.txt";

input_file = open(input ,"r")

# Get all points:
count = 0
points = []
for line in input_file.readlines():
    X = int(line[0:line.index(",")])
    Y = int(line[line.index(", ")+2:])
    points.append({"ID": count, "X": X, "Y": Y, "M" : 0, "Exclude": 0})
    count = count + 1


field_size = 550
# Build big 2D array
map = [[0] * field_size for i in range(field_size)]
for p in points:
    map[p['X']][p['Y']] = 1

for x in range(field_size):
    for y in range(field_size):
        shortest_dist = 1000
        shortest_id = -1
        for p in points:
            # Manhattan for current point:
            manhat = abs(x - p['X']) + abs(y - p['Y'])
            if (manhat == shortest_dist):
                # two ids have the same distance
                shortest_dist = shortest_dist
                shortest_id = -1
            if (manhat < shortest_dist):
                shortest_dist = manhat
                shortest_id = p['ID']
        if (shortest_id != -1):
            for p in points:
                if (p['ID'] == shortest_id):
                    p['M'] = p['M'] + 1
                    if (x == 0) or (y == 0) or (x == field_size-1) or (y == field_size-1):
                        p['Exclude'] = 1

for p in points:
    print p
