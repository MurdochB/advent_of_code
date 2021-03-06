input = "input/input-6.txt"
input_file = open(input ,"r")

class LandingPoint(): 
  def __init__(self, name, parent):
    self.name = name
    self.parent = parent
    self.child_orbits = []

  def get_num_orbits(self):
    if (self.parent == None):
      return 0

    return 1 + self.parent.get_num_orbits()

  def print_child_orbits(self):
    print(str(self.child_orbits))
  
  def __str__(self):
    return self.name 

  def __repr__(self):
    return self.name 


direct_orbits = []
known_landings = []

# Parse into array
for line in input_file:
  direct_orbits.append(map(lambda x: x, line.rstrip("\n").split(")")))

# Add all landings
for dir_o in direct_orbits:
  base_name, orbitter_name = dir_o

  base_exists = False
  orbitter_exists = False
  base = None
  orbitter = None

  for l in known_landings:
    if (l.name == base_name):
      base_exists = True
      base = l
    if (l.name == orbitter_name):
      orbitter_exists = True
      orbitter = l
  
  print("dir_o = %s base seen: %s | orbitter seen: %s" % (str(dir_o) ,str(base_exists), str(orbitter_exists)))
  if (not base_exists) and (not orbitter_exists):
    # Both don't exist, so both need to be created
    base = LandingPoint(base_name, None)
    orbitter = LandingPoint(orbitter_name, base)
    base.child_orbits.append(orbitter)
    known_landings.append(base)
    known_landings.append(orbitter)
  if (base_exists) and (orbitter_exists):
    # both exist - so set the parent + add the child
    orbitter.parent = base
    base.child_orbits.append(orbitter)
  if (not base_exists) and (orbitter_exists):
    # New base, with existing orbitter
    base = LandingPoint(base_name, None)
    orbitter.parent = base
    base.child_orbits.append(orbitter)
    known_landings.append(base)
  if (base_exists) and (not orbitter_exists):
    # new child orbitter for existing base
    orbitter = LandingPoint(orbitter_name, base)
    base.child_orbits.append(orbitter)
    known_landings.append(orbitter)

# Part two

# Get the path to COM for both YOU and SAN
path_to_com_YOU = []
path_to_com_SAN = []
for l in known_landings:
  if (l.name == "YOU"):
    com_found = False
    while(not com_found):
      l = l.parent
      if (l.name == "COM"):
        com_found = True
      path_to_com_YOU.append(l.name)

  if (l.name == "SAN"):
    com_found = False
    while(not com_found):
      l = l.parent
      if (l.name == "COM"):
        com_found = True
      path_to_com_SAN.append(l.name)

count_in_both = 0
for l in path_to_com_YOU:
  if l in path_to_com_SAN:
    count_in_both += 1
count_in_YOU = len(path_to_com_YOU)
count_in_SAN = len(path_to_com_SAN)
print("ans: %s + %s - %s" % (count_in_YOU, count_in_SAN, count_in_both * 2))