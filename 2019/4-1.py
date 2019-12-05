input = "input/input-4.txt"
input_file = open(input ,"r")

acceptable_passwords = []

mini = 0
maxi = 0

def twoAdjacentSame(value):
  val = str(value)
  if(int(val[0]) == int(val[1])):
    return True
  elif(int(val[1]) == int(val[2])):
    return True
  elif(int(val[2]) == int(val[3])):
    return True
  elif(int(val[3]) == int(val[4])):
    return True
  elif(int(val[4]) == int(val[5])):
    return True
  else:
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


for i in range(mini, maxi):
  if (twoAdjacentSame(i) and incrementallyGetsBigger(i)):
    acceptable_passwords.append(i)

print acceptable_passwords
print len(acceptable_passwords)