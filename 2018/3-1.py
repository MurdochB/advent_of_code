input = "3-input.txt";

input_file = open(input ,"r")
x = 1000
y = 1000
# create big 2D array
map = [[0] * y for i in range(x)]

for line in input_file:
    id = line[line.index("#")+1:line.index(" ")]
    X = int(line[line.index(" @ ")+3:line.index(",")])
    Y = int(line[line.index(",")+1:line.index(":")])
    W = int(line[line.index(": ")+2:line.index("x")])
    T = int(line[line.index("x")+1:line.index("\n")])

    # Add cuts to 2D array
    for Xs in range(X, X + W):
        for Ys in range(Y, Y + T):
            pos = map[Xs]
            pos[Ys] += 1

# Count clashes
clashes = 0
for final_x in range(x):
    for final_y in range(y):
        pos = map[final_x]
        if (pos[final_y] > 1):
            clashes += 1
print clashes
