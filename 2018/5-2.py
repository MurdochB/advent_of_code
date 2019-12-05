input = "5-input.txt";

input_file = open(input ,"r")

poly = input_file.read()
input_file.close()

poly_reset = poly
new_poly = ""
skip_next_char = False
done = False

alpha = {
    'a': 0,
    'b': 0,
    'c': 0,
    'd': 0,
    'e': 0,
    'f': 0,
    'g': 0,
    'h': 0,
    'i': 0,
    'j': 0,
    'k': 0,
    'l': 0,
    'm': 0,
    'n': 0,
    'o': 0,
    'p': 0,
    'q': 0,
    'r': 0,
    's': 0,
    't': 0,
    'u': 0,
    'v': 0,
    'w': 0,
    'x': 0,
    'y': 0,
    'z':0
    }

for letter in alpha:
    while(not done):
        done = True
        length_of_poly = len(poly)
        for x in range(len(poly) - 1):
            if (skip_next_char):
                skip_next_char = False
            else:
                if ((poly[x].lower() == poly[x + 1].lower()) and (poly[x] != poly[x + 1])):
                    skip_next_char = True
                else:
                    if not (poly[x].lower() == letter):
                        new_poly = new_poly + poly[x]
        poly = new_poly + "\n"
        new_poly = ""
        length_of_poly_now = len(poly) + 1
        if (length_of_poly > length_of_poly_now):
            done = False
    print letter + ":" + str(len(poly) - 1)
    alpha[letter] = len(poly) - 1
    poly = poly_reset
    done = False

print alpha
