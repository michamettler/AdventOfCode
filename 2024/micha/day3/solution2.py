import re

with open('2024/micha/day3/input.txt') as file:
    locations = file.readlines()

memory = 'do()'
for line in locations:
    memory += line.strip()

instructions = re.findall(r"(do\(\))(.*?)(?=do\(\)|don't\(\)|$)", memory)

result = 0
for instr in instructions:
    muls = re.findall(r"mul\(\d{0,3}\,\d{0,3}\)", instr[1])
    for mul in muls:
        clean = mul.replace('mul(', '').replace(')', '')
        nums = clean.split(',')
        result += int(nums[0]) * int(nums[1])
print(result)