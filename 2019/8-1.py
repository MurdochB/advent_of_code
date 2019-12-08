input = "input/input-8.txt"
input_file = open(input ,"r")

# Settings
wide = 25
tall = 6
layer_size = wide * tall

inputStr = ""
layers = []

for line in input_file:
  inputStr = line.rstrip("\n")

# Split into separate layers
cur_layer = 0
inputLength = len(inputStr)
while(cur_layer < inputLength):
  layers.append(inputStr[cur_layer:cur_layer+layer_size])
  cur_layer += layer_size

lowest_zeros = layers[0].count("0")
layer_with_lowest = 0
for i in range(0, len(layers)):
  this_layer = layers[i]
  if this_layer.count("0") < lowest_zeros:
    lowest_zeros = this_layer.count("0")
    layer_with_lowest = i

print("layer with lowest zeros: %s | %s zeros" % (layer_with_lowest, lowest_zeros))

num_ones = layers[layer_with_lowest].count("1")
num_twos = layers[layer_with_lowest].count("2")

print("layer has %s ones & %s twos - output is: %s" % (num_ones, num_twos, num_ones * num_twos))