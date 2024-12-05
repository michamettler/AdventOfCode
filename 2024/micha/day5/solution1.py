with open('2024/micha/day5/input.txt') as file:
    locations = file.readlines()

# part one

rules = {}
prints = []

for line in locations:
    line = line.strip()
    if ('|' in line):
        v = line.split('|')
        if v[0] not in rules:
            rules[v[0]] = [v[1]]
        else:
            rules[v[0]].append(v[1])
    elif ',' in line:
        prints.append(list(line.split(',')))

cntr = 0
for pr in prints:
    valid = True
    for i, page in enumerate(pr):
        if page in rules:
            for toCheck in rules[page]:
                if toCheck in pr and not pr.index(toCheck) > i:
                    valid = False
                    continue            
    if valid:
        cntr += int(pr[len(pr)//2])
print(cntr)