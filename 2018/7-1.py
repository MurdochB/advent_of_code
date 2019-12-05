input = "7-input.txt";

input_file = open(input ,"r")

requirements = {
'A': [],
'B': [],
'C': [],
'D': [],
'E': [],
'F': [],
'G': [],
'H': [],
'I': [],
'J': [],
'K': [],
'L': [],
'M': [],
'N': [],
'O': [],
'P': [],
'Q': [],
'R': [],
'S': [],
'T': [],
'U': [],
'V': [],
'W': [],
'X': [],
'Y': [],
'Z': []}

# Create list of requirements
for line in input_file:
    preReq = line[line.index("Step ")+5:line.index(" must")]
    req = line[line.index("before step ")+12:line.index(" can")]
    if (req in requirements):
        requirements[req].append(preReq)
    else:
        requirements[req] = [preReq]


def getStepWithNoPreReq():
    steps = []
    for step in requirements:
        if (requirements[step] == []):
            steps.append(step)
    return sorted(steps)

joblist = ""
steps = []
while (len(joblist) < 26):
    steps = getStepWithNoPreReq()
    joblist = joblist + steps[0]
    requirements[steps[0]].append("-")
    for req in requirements:
        if (steps[0] in requirements[req]):
            requirements[req].remove(steps[0])
print joblist
