input = "input/input-4.txt"
input_file = open(input ,"r")

acceptable_passwords = []

mini = 0
maxi = 0

def twoAdjacentSame(value):
  val = str(value)    
  if(int(val[0]) == int(val[1])):
    if((val.count(val[0])) <= 2):
      return True
  if(int(val[1]) == int(val[2])):
    if((val.count(val[1])) <= 2):
      return True
  if(int(val[2]) == int(val[3])):
    if((val.count(val[2])) <= 2):
      return True
  if(int(val[3]) == int(val[4])):
    if((val.count(val[3])) <= 2):
      return True
  if(int(val[4]) == int(val[5])):
    if((val.count(val[4])) <= 2):
      return True
  return False

def incrementallyGetsBigger(value):
  val = str(value)
  if(int(val[0]) <= int(val[1])):
    if(int(val[1]) <= int(val[2])):
      if(int(val[2]) <= int(val[3])):
        if(int(val[3]) <= int(val[4])):
          if(int(val[4]) <= int(val[5])):
            return True
  return False


for line in input_file:
  values = map(lambda x: int(x), line.rstrip('\n').split("-"))

mini = values[0]
maxi = values[1]




print(acceptable_passwords)
print len(acceptable_passwords)