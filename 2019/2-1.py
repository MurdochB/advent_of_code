input = "input/input-2.txt"
input_file = open(input ,"r")

arr = []

# Parse into array
for line in input_file:
  arr = map(lambda x: int(x), line.split(","))

print("Input array: ")
print(arr)
print("-----")


def doOp(op, paramOneIndex, paramTwoIndex, outputIndex):
  if (op == 1):
    arr[outputIndex] = arr[paramOneIndex] + arr[paramTwoIndex]
  elif (op == 2):
    arr[outputIndex] = arr[paramOneIndex] * arr[paramTwoIndex]
  elif(op == 99):
    print("finish")

nextOpIndex = 0
nextOpcode = arr[nextOpIndex]

while(nextOpcode != 99):
  paramOne = arr[nextOpIndex + 1]
  paramTwo = arr[nextOpIndex + 2]
  output = arr[nextOpIndex + 3]

  doOp(nextOpcode, paramOne, paramTwo, output)
  
  nextOpIndex += 4
  nextOpcode = arr[nextOpIndex]

print("Output array: ")
print(arr)
print("-----")

# 6087827