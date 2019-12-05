input = "8-input.txt";

input_file = open(input ,"r")

allNodes = input_file.read().strip("\n").split(' ')
print allNodes
node = {
    'header': [],
    'data': []
}
def parseNode(nodes):
    # read header from data
    print "Reading header from node: "
    children = int(nodes[0])
    meta_count = int(nodes[1])
    data = nodes[2:]
    print "Children is : " + str(children)
    print "Meta count is : " + str(meta_count)
    print "data is : " + str(data)

    if (children == 0):
        # get the meta total
        print "SUM:" + str(sum(int(x) for x in data[2:2+meta_count]))
    if (children > 0):
        parseNode(data)


parseNode(allNodes)

#
# 2 3
# 	1 1
#       0 1
#           99
#       2
#   0 3
#       10 11 12 
# 1 1 2
