input = "input/input-1.txt"
input_file = open(input ,"r")

total_fuel = 0

def calculateFuelForFuel(fuel):
  fuel_for_fuel = (fuel / 3) - 2
  if (fuel_for_fuel <= 0):
    return 0

  return (fuel_for_fuel + calculateFuelForFuel(fuel_for_fuel))


for line in input_file:
  module_mass = int(line)
  fuel_for_module = (module_mass / 3) - 2
  fuel_for_fuel = calculateFuelForFuel(fuel_for_module)
  fuel_for_module += fuel_for_fuel

  total_fuel += fuel_for_module

print "Total fuel needed: " + str(total_fuel)

# Total fuel needed: 5128195