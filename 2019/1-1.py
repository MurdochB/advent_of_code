input = "input/input-1.txt"
input_file = open(input ,"r")

total_fuel = 0

for line in input_file:
  module_mass = int(line)
  fuel_for_module = (module_mass / 3) - 2
  total_fuel += fuel_for_module

print "Total fuel needed: " + str(total_fuel)

# Total fuel needed: 3420719 