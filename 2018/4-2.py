input = "4-input.txt";

input_file = open(input ,"r")

# Sort file
sorted_lines = sorted(input_file.readlines())

sleep_dicts = {}
sleep_mins = {}

guard = ""
sleep_start = ""
sleep_end = ""
# add each period of sleep for each guard
for line in sorted_lines:
    if("Guard" in line):
        guard = line[line.index("Guard #")+7:line.index(" begins")]
    if("falls asleep" in line):
        sleep_start = line[line.index(":")+1:line.index("]")]
    if("wakes up" in line):
        sleep_end = line[line.index(":")+1:line.index("]")]
        if (guard not in sleep_dicts):
            sleep_dicts[guard] = []
            sleep_mins[guard] = [0 for i in range(60)]
        sleep_dicts[guard].append("From " + sleep_start + " To " + sleep_end)

highest_consistency = 0
for guard in sleep_dicts:
    for sleep in sleep_dicts[guard]:
        start_time = int(sleep[sleep.index("From ")+5:sleep.index(" To")])
        end_time = int(sleep[sleep.index("To ")+3:])
        for x in range(start_time, end_time):
            sleep_mins[guard][x] = sleep_mins[guard][x] + 1
        most_consistent_sleep_time = sleep_mins[guard].index(max(sleep_mins[guard]))
        consistency = max(sleep_mins[guard])
        most_consistent_sleep_time = sleep_mins[guard].index(consistency)
    if (consistency > highest_consistency):
        highest_consistency = consistency
        print "guard: " + str(guard) + " consistency: " + str(consistency) + " time: " + str(most_consistent_sleep_time)
