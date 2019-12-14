import itertools

input = "input/input-7.txt"
input_file = open(input ,"r")

amplifierControllerSoftware = []

# Parse into array
for line in input_file:
  amplifierControllerSoftware = map(lambda x: int(x), line.split(","))

class IntComputer(): 

  def __init__(self, ticker, inputs):
    self.ticker = ticker              # The software the computer will run
    self.inputs = inputs              # input list
    self.outputs = []                 # list to collect outputs
    self.currentInputIndex = 0        # index of next input in list
    self.currentOperationIndex = 0    # index of current operation

  def setInputs(self, inputs):
    self.inputs = inputs
    self.currentInputIndex = 0

  def getParamVal(self, inp, mode):
    if(int(mode) == 0):
      return self.ticker[inp]
    if(int(mode) == 1):
      return inp
    print("invalid param mode")
    return 0


  def doOpAndGetProgress(self, op):
    opCode = int(str(op)[-2:])
    paramMode = str(op)[:-2]  # strips opCode, leaving param mode from op
    paramMode = paramMode[::-1] + "0000"

    # print(str(self.currentOperationIndex) + " " + str(self.ticker))

    if(opCode == 1):
       # Add next two parameters, store in third parameter
       one, two, three = self.ticker[self.currentOperationIndex + 1], self.ticker[self.currentOperationIndex + 2], self.ticker[self.currentOperationIndex + 3]
       one = self.getParamVal(one, paramMode[0])
       two = self.getParamVal(two, paramMode[1])
       # print("OP: ADD | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
       self.ticker[three] = one + two
       return 4

    if(opCode == 2):
       # Multiply next two parameters, store in third parameter
       one, two, three = self.ticker[self.currentOperationIndex + 1], self.ticker[self.currentOperationIndex + 2], self.ticker[self.currentOperationIndex + 3]
       one = self.getParamVal(one, paramMode[0])
       two = self.getParamVal(two, paramMode[1])
       # print("OP: MUL | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
       self.ticker[three] = one * two
       return 4

    if(opCode == 3):
      # Input into next parameter
      inputVal = self.inputs[self.currentInputIndex]
      self.currentInputIndex += 1
      one = self.ticker[self.currentOperationIndex + 1]
      # print("OP: INP | index to insert into: %s | value: %s" % (one, inputVal))
      self.ticker[one] = inputVal
      return 2

    if(opCode == 4):
      # Output a value
      one = self.ticker[self.currentOperationIndex + 1]
      one = self.getParamVal(one, paramMode[0])
      self.outputs.append(one)
      # print("OP: OUT | %s" % (one))
      return 2
    
    if(opCode == 5):
      # Jump if true - if first param is not zero, set intruction pointer to second param
      one, two = self.ticker[self.currentOperationIndex + 1], self.ticker[self.currentOperationIndex + 2]
      one = self.getParamVal(one, paramMode[0])
      two = self.getParamVal(two, paramMode[1])
      # print("OP: JMPT | paramModes: %s | %s %s" % (paramMode, one, two))
      if (one != 0):
        self.currentOperationIndex = two
        return 0
      return 3

    if(opCode == 6):
      # Jump if false - if first param is zero, set intruction pointer to second param
      one, two = self.ticker[self.currentOperationIndex + 1], self.ticker[self.currentOperationIndex + 2]
      one = self.getParamVal(one, paramMode[0])
      two = self.getParamVal(two, paramMode[1])
      # print("OP: JMPF | paramModes: %s | %s %s" % (paramMode, one, two))
      if (one == 0):
        self.currentOperationIndex = two
        return 0
      return 3

    if(opCode == 7):
      # Less than - if the first param is less than the second, stores 1 in the third param or else 0
      one, two, three = self.ticker[self.currentOperationIndex + 1], self.ticker[self.currentOperationIndex + 2], self.ticker[self.currentOperationIndex + 3]
      one = self.getParamVal(one, paramMode[0])
      two = self.getParamVal(two, paramMode[1])
      # print("OP: LESS | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
      if (one < two):
        self.ticker[three] = 1
      else:
        self.ticker[three] = 0
      return 4

    if(opCode == 8):
      # Equal - if the first param is equal to the second, stores 1 in the third param or else 0
      one, two, three = self.ticker[self.currentOperationIndex + 1], self.ticker[self.currentOperationIndex + 2], self.ticker[self.currentOperationIndex + 3]
      one = self.getParamVal(one, paramMode[0])
      two = self.getParamVal(two, paramMode[1])
      # print("OP: EQL | paramModes: %s | %s %s %s" % (paramMode, one, two, three))
      if (one == two):
        self.ticker[three] = 1
      else:
        self.ticker[three] = 0
      return 4
    if(opCode == 99):
      # print("OP: FIN")
      return 1
    return 1


  def runComputer(self):
    self.currentOperationIndex = 0 
    nextOpcode = 0
    while(nextOpcode != 99):
      nextOpcode = self.ticker[self.currentOperationIndex]
      progress = self.doOpAndGetProgress(nextOpcode)
      self.currentOperationIndex += progress
    # print("processing: " + str(self.ticker) + " " + str(self.inputs))

  def getOutput(self):
    return self.outputs

bestPhaseSetting = (0,0,0,0,0)
thrusterSignal = 0

orderedPhaseSettings = list(itertools.permutations([0,1,2,3,4]))
for phaseSetting in orderedPhaseSettings:
  inA = [phaseSetting[0], 0]
  intCompA = IntComputer(amplifierControllerSoftware, inA)
  intCompA.runComputer()

  inB = [phaseSetting[1], intCompA.getOutput()[0]]
  intCompB = IntComputer(amplifierControllerSoftware, inB)
  intCompB.runComputer()
  
  inC = [phaseSetting[2], intCompB.getOutput()[0]]
  intCompC = IntComputer(amplifierControllerSoftware, inC)
  intCompC.runComputer()

  inD = [phaseSetting[3], intCompC.getOutput()[0]]
  intCompD = IntComputer(amplifierControllerSoftware, inD)
  intCompD.runComputer()

  inE = [phaseSetting[4], intCompD.getOutput()[0]]
  intCompE = IntComputer(amplifierControllerSoftware, inE)
  intCompE.runComputer()

  if (intCompE.getOutput()[0] > thrusterSignal):
    bestPhaseSetting = phaseSetting
    thrusterSignal = intCompE.getOutput()[0]

print(bestPhaseSetting)
print(thrusterSignal)
