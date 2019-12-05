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
    run[outputIndex] = run[paramOneIndex] + run[paramTwoIndex]
  elif (op == 2):
    run[outputIndex] = run[paramOneIndex] * run[paramTwoIndex]
  elif(op == 99):
    print("finish")


run = arr[:]
done = False
nextOpIndex = 0
nextOpcode = run[nextOpIndex]

for n in range(0, 100):
  for v in range(0, 100):
    if (not done):
      run[1] = n
      run[2] = v

      while(nextOpcode != 99):
        paramOne = run[nextOpIndex + 1]
        paramTwo = run[nextOpIndex + 2]
        output = run[nextOpIndex + 3]

        doOp(nextOpcode, paramOne, paramTwo, output)
        nextOpIndex += 4
        nextOpcode = run[nextOpIndex]

      if (run[0] == 19690720):
        out = n * 100 + v
        print("input for [19690720] is: %s" % str(out))
        done = True
      
      # Reset array to input
      run = arr[:]
      nextOpIndex = 0
      nextOpcode = run[nextOpIndex]

# input for [19690720] is: 5379