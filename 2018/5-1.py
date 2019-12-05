input = "5-input.txt";

input_file = open(input ,"r")

poly = input_file.read()
input_file.close()

new_poly = ""
skip_next_char = False
done = False
counter = 0

while(not done):
    counter = counter + 1
    done = True
    length_of_poly = len(poly)
    for x in range(len(poly) - 1):
        if (skip_next_char):
            skip_next_char = False
        else:
            if ((poly[x].lower() == poly[x + 1].lower()) and (poly[x] != poly[x + 1])):
                skip_next_char = True
            else:
                new_poly = new_poly + poly[x]
    poly = new_poly + "\n"
    new_poly = ""
    length_of_poly_now = len(poly) + 1
    if (length_of_poly > length_of_poly_now):
        done = False
print poly
print counter
