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

final_image = "2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2"
final_image = final_image.split(" ")

for l in layers:
  for c in range(len(l)):
    if final_image[c] == "2":
      final_image[c] = l[c]

print(final_image)
# [
# 	 XX  XXXX X  X X  X  XX  ,
# 	X  X X    X X  X  X X  X ,
# 	X    XXX  XX   X  X X  X ,
# 	X    X    X X  X  X XXXX ,
# 	X  X X    X X  X  X X  X ,
# 	 XX  XXXX X  X  XX  X  X 
# ]