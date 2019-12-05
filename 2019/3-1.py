input = "input/input-3.txt"
input_file = open(input ,"r")

cables = [] # List of both cable commands [[R10, U10 ...], [R10, U10 ...]]

crossed = []

field_size = 15000
# Build big 2D array
panel_map = [[0] * field_size for i in range(field_size)]


def runCable(cable_name, cable_ops):
  print("running cable %s" % cable_name)
  current_loc = [750, 750]

  for op in cable_ops:
    command = op[0]
    distance = int(op[1:])
    while distance > 0:
      if (command == 'U'):
        current_loc[1] += 1
      if (command == 'D'):
        current_loc[1] -= 1
      if (command == 'L'):
        current_loc[0] -= 1
      if (command == 'R'):
        current_loc[0] += 1
      distance -= 1

      this_coord = panel_map[current_loc[0]][current_loc[1]]
      if (this_coord == 0 or this_coord == cable_name):
        panel_map[current_loc[0]][current_loc[1]] = cable_name
      else:
        crossed.append(abs(current_loc[0] - 750) + abs(current_loc[1] - 750))

for line in input_file:
  cables.append(map(lambda x: x, line.rstrip('\n').split(",")))

cable_num = 1
for cable in cables:
  runCable(cable_num, cable)
  cable_num += 1


print(crossed)
print min(crossed)