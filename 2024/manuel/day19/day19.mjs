import fs from 'fs'


let input = fs.readFileSync('./day19.txt', 'utf8')


let lines = input.split('\n\n')

let towels = lines[0].split(', ')
let targets = lines[1].split('\n').filter(l => l.length > 0)

console.log(towels)
console.log(targets)

let count = 0

targets.forEach((t) => {
    if (countMatches(t, towels)) count++
})
console.log(count)

count = 0

targets.forEach((t) => {
    count += countMatches(t, towels)
})
console.log(count)


function countMatches(target, patterns, memo = {}) {
    let matches = 0;
    if (target in memo) return memo[target];
    if (target.length === 0) return 1;
    for (let pattern of patterns) {
        if (target.startsWith(pattern)) {
            matches += countMatches(target.slice(pattern.length), patterns, memo);
        }
    }
    memo[target] = matches;
    return matches;
}




