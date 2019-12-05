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
        for p in points:
            # Manhattan for current point:
            manhat = abs(x - p['X']) + abs(y - p['Y'])


count = 0
for x in range(field_size):
    for y in range(field_size):
        total_manhat = 0
        for p in points:
            # Manhattan for current point:
            total_manhat = total_manhat + (abs(x - p['X']) + abs(y - p['Y']))
        if (total_manhat < 10000):
            count = count + 1

print count
