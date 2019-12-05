import copy
input = "7-input.txt";


input_file = open(input ,"r")

requirements = {
'A': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 61},
'B': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 62},
'C': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 63},
'D': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 64},
'E': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 65},
'F': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 66},
'G': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 67},
'H': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 68},
'I': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 69},
'J': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 70},
'K': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 71},
'L': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 72},
'M': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 73},
'N': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 74},
'O': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 75},
'P': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 76},
'Q': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 77},
'R': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 78},
'S': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 79},
'T': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 80},
'U': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 81},
'V': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 82},
'W': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 83},
'X': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 84},
'Y': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 85},
'Z': {'pre-req': [], 'in-progress': False, 'done': False, 'time-max': 86}}

workers = {
  '1': {'step': '-', 'time': '0'},
  '2': {'step': '-', 'time': '0'},
  '3': {'step': '-', 'time': '0'},
  '4': {'step': '-', 'time': '0'},
  '5': {'step': '-', 'time': '0'}
}

# Create list of requirements
for line in input_file:
    preReq = line[line.index("Step ")+5:line.index(" must")]
    req = line[line.index("before step ")+12:line.index(" can")]
    if (req in requirements):
        requirements[req]['pre-req'].append(preReq)
    else:
        requirements[req]['pre-req'] = [preReq]

def print_requirements():
    for _ in sorted(requirements):
        print _ + ": " + str(requirements[_])

def getStepWithNoPreReq():
    steps = []
    for step in requirements:
        if (requirements[step]['pre-req'] == []) and (not requirements[step]['in-progress']) and (not requirements[step]['done']):
            steps.append(step)
    return sorted(steps)

def getFreeWorker():
    for _ in workers:
        if (workers[_]['step'] == '-'):
            return _
    return -1

steps = getStepWithNoPreReq()
for s in steps:
    aw = getFreeWorker()
    if (aw > 0):
        workers[aw]['step'] = s
        workers[aw]['time'] = requirements[s]['time-max']
print workers


print getFreeWorker()
#
#
# steps = getStepWithNoPreReq()
#     # for each step with no pre-req
# for s in steps:
#     for w in sorted(workers):
#         if (workers[w]['step'] == '-'):
#             workers[w]['step'] = s
#             workers[w]['time'] = requirements[s]['time-max']
#             requirements
#
# joblist = ""
# steps = []
# while (len(joblist) < 26):
#     steps = getStepWithNoPreReq()
#     joblist = joblist + steps[0]
#     requirements[steps[0]].append("-")
#     for req in requirements:
#         if (steps[0] in requirements[req]):
#             requirements[req].remove(steps[0])
# print joblist
