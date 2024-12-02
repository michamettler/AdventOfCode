with open('testInput.txt') as file:
    locations = file.readlines()


def isRangeValid(a, b):
    return 1 <= abs(a - b) <= 3


def isOrderValid(a, b, incr):
    return (a < b and incr) or (a > b and not incr)


cntr = 0
for line in locations:
    levels = list(map(int, line.strip().split(' ')))
    error = False
    dampened = False

    increasing = (levels[0] < levels[-1] and levels[0] < levels[1]) or (
                levels[1] < levels[-1] and levels[1] < levels[2])
    # a little messy - check if there are at least two increasing levels

    i = 0
    while i < len(levels) - 1:
        if not isRangeValid(levels[i], levels[i + 1]) or not isOrderValid(levels[i], levels[i + 1], increasing):
            if dampened:
                error = True
                break
            else:
                if i + 1 == len(levels) - 1:  # last element, break because one single bad level is allowed
                    break
                else:
                    if isRangeValid(levels[i], levels[i + 2]) and isOrderValid(levels[i], levels[i + 2], increasing):
                        levels.pop(i + 1)
                    elif isRangeValid(levels[i + 1], levels[i + 2]) and isOrderValid(levels[i + 1], levels[i + 2],
                                                                                     increasing):
                        levels.pop(i)
                    else:
                        error = True
                        break
                dampened = True
                i = 0
        else:
            i += 1

    if not error:
        cntr += 1

print(cntr)
