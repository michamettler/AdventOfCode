import re

with open('2024/micha/day3/input.txt') as file:
    locations = file.readlines()

memory = ''
for line in locations:
    memory += line.strip()

instructions = re.findall(r"mul\(\d{1,3}\,\d{1,3}\)", memory)

result = 0
for instr in instructions:
    clean = instr.replace('mul(', '').replace(')', '')
    nums = clean.split(',')
    result += int(nums[0]) * int(nums[1])
print(result)