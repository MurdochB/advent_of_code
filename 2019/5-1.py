# 5-1 & 5-2 - needs to be cleaned up.
input = "input/input-5.txt"
input_file = open(input ,"r")

arr = []
inputVal = 0

# Parse into array
for line in input_file:
  arr = map(lambda x: int(x), line.split(","))

curOpIndex = 0
nextOpcode = arr[curOpIndex]

def doOpAndGetProgress(op):
  global curOpIndex
  # print(curOpIndex)
  # print(arr)
  opCode = str(op)[-2:]
  paramMode = str(op)[:-2]
  paramMode = paramMode[::-1] + "0000"

  if (opCode == "01" or opCode == "1"):
    # 'Add' next two params, store in third
    one = arr[curOpIndex + 1]
    two = arr[curOpIndex + 2]
    three = arr[curOpIndex + 3]
    print("OP: ADD | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
    if (paramMode[0] == "0"):
      one = arr[one]
    if (paramMode[1] == "0"):
      two = arr[two]
    if (paramMode[2] == "1"):
      print("oops, this shouldn't happen")
    
    print("OP: arr[%s] = %s + %s" % (three, one, two))
    arr[three] = one + two
    return 4
  elif (opCode == "02" or opCode == "2"):
    # 'Multiply' next two params, store in third
    one = arr[curOpIndex + 1]
    two = arr[curOpIndex + 2]
    three = arr[curOpIndex + 3]
    print("OP: MUL | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
    if (paramMode[0] == "0"):
      one = arr[one]
    if (paramMode[1] == "0"):
      two = arr[two]
    if (paramMode[2] == "1"):
      print("oops, this shouldn't happen")

    print("OP: arr[%s] = %s * %s" % (three, one, two))
    arr[three] = one * two
    return 4
  elif (opCode == "03" or opCode == "3"):
    # 'Input' a value, store in next param
    one = arr[curOpIndex + 1]
    print("OP: INP | %s" % (one))
    arr[one] = 5
    return 2
  elif (opCode == "04" or opCode == "4"):
    # 'Output' a value
    one = arr[curOpIndex + 1]
    if (paramMode[0] == "0"):
      one = arr[one]
    print("OP: OUT | %s" % (one))
    return 2
  elif (opCode == "05" or opCode == "5"):
    # Jump if true
    # if first param is not zero, set intruction pointer to second param

    one = arr[curOpIndex + 1]
    two = arr[curOpIndex + 2]
    print("OP: JMP-T | paramModes: %s | %s %s" % (paramMode, one, two))
    if (paramMode[0] == "0"):
      one = arr[one]
    if (paramMode[1] == "0"):
      two = arr[two]
    
    if (int(one) != int(0)):
      print("curOpIndex = %s" % two)
      curOpIndex = two
      return 0
    return 3

  elif (opCode == "06" or opCode == "6"):
    # Jump if false
    # if first param is zero, set intruction pointer to second param
    one = arr[curOpIndex + 1]
    two = arr[curOpIndex + 2]
    print("OP: JMP-F | paramModes: %s | %s %s" % (paramMode, one, two))
    if (paramMode[0] == "0"):
      one = arr[one]
    if (paramMode[1] == "0"):
      two = arr[two]
    
    if (int(one) == int(0)):
      print("curOpIndex = %s" % two)
      curOpIndex = two
      return 0
    return 3
  elif (opCode == "07" or opCode == "7"):
    # if the first param is less than the second, stores 1 in the 3rd param or else 0
    one = arr[curOpIndex + 1]
    two = arr[curOpIndex + 2]
    three = arr[curOpIndex + 3]
    print("OP: LESS | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
    if (paramMode[0] == "0"):
      one = arr[one]
    if (paramMode[1] == "0"):
      two = arr[two]

    if (int(one) < int(two)):
      print("arr[%s] = 1" % three)
      arr[three] = 1
    else:
      print("arr[%s] = 0" % three)
      arr[three] = 0
    
    return 4
  elif (opCode == "08" or opCode == "8"):
    # if the first param is equal to the second, stores 1 in the 3rd param or else 0
    one = arr[curOpIndex + 1]
    two = arr[curOpIndex + 2]
    three = arr[curOpIndex + 3]
    print("OP: EQL | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
    if (paramMode[0] == "0"):
      one = arr[one]
    if (paramMode[1] == "0"):
      two = arr[two]
    
    if (int(one) == int(two)):
      print("arr[%s] = 1" % three)
      arr[three] = 1
    else:
      print("arr[%s] = 0" % three)
      arr[three] = 0
    return 4

  elif(opCode == "99"):
    print("finished")
    return 0
  return 0

while(nextOpcode != 99):
  
  nextOpcode = arr[curOpIndex]
  progress = doOpAndGetProgress(nextOpcode)
  curOpIndex += progress
