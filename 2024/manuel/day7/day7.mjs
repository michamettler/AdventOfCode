import { neighbors, readFile, readGrid, readInts, readLines } from '../util/load-csv.mjs';



(async () => {
    let input = await readFile('./day7.txt')
    const map = input.split("\n").map(line => line.split(":"));
    const valids = map.filter(check)
    let sum = 0
    valids.forEach((key) => {
        sum += parseInt(key[0])
    });
    console.log("res", sum)


    const valids2 = map.filter(check2)
    sum = 0
    valids2.forEach((key) => {
        sum += parseInt(key[0])
    });
    console.log("res2", sum)
})();


function check2(values) {
    if (values.length !== 2) return false
    const res = parseInt(values[0])
    const vars = values[1].trim().split(' ').map(elem => parseInt(elem, 10));


    function generateCombinations(length) {
        if (length === 0) return [[]];
        const smallerCombinations = generateCombinations(length - 1);
        return smallerCombinations.flatMap(comb => [['+'].concat(comb), ['|'].concat(comb), ['*'].concat(comb)]);
    }

    const combinations = generateCombinations(vars.length - 1);
    for (let combination of combinations) {
        if (evalComb2(vars, combination) === res) {
            console.log(`Match found with ${combination.join(' ')}`);
            return true; // Stop at the first valid combination
        }
    }
    return false
}

function evalComb2(vars, sym) {
    let res = vars[0]
    for (let i = 0; i < vars.length; i++) {
        const element = vars[i + 1];
        switch (sym[i]) {
            case '+':
                res += element
                break;
            case '*':
                res *= element
                break;
            case '|':
                const con = res.toString() + element.toString()
                res = parseInt(con)
                break;
            default:
                break;
        }
    }
    return res
}

function check(value, key) {
    if (value.length !== 2) return false
    const res = parseInt(value[0])
    const vars = value[1].trim().split(' ').map(elem => parseInt(elem, 10));

    function generateCombinations(length) {
        if (length === 0) return [[]];
        const smallerCombinations = generateCombinations(length - 1);
        return smallerCombinations.flatMap(comb => [['+'].concat(comb), ['*'].concat(comb)]);
    }

    const combinations = generateCombinations(vars.length - 1);
    for (let combination of combinations) {
        if (evalComb(vars, combination) === res) {
            return true; // Stop at the first valid combination
        }
    }

    return false;
}

function evalComb(vars, sym) {
    let res = vars[0]
    for (let i = 0; i < vars.length; i++) {
        const element = vars[i + 1];
        switch (sym[i]) {
            case '+':
                res += element
                break;
            case '*':
                res *= element
                break;
            default:
                break;
        }
    }
    return res
}
