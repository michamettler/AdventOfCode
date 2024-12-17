import fs from 'fs'


let input = fs.readFileSync('./day17.txt', 'utf8')


let parts = input.split('\n\n')
let regs = parts[0].split('\n')
let regA = BigInt(parseInt(regs[0].split('Register A: ')[1]))
let regB = BigInt(parseInt(regs[1].split('Register B: ')[1]))
let regC = BigInt(parseInt(regs[2].split('Register C: ')[1]))

let instructions = parts[1].split('Program: ')[1].split(',').map(i => BigInt(parseInt(i.trim())))

function sim(regA, regB, regC, instructions) {
    let output = []
    let insPointer = 0n //increases by defautl +2 each ins, if it overflows i stops
    while (insPointer < instructions.length) {
        let operand = instructions[insPointer + 1n]
        switch (operand) {
            case 4n:
                operand = regA
                break;
            case 5n:
                operand = regB
                break;
            case 6n:
                operand = regC
                break;
            case 7n:
                console.error('operand 7: invalid program')
                break;
            default:
                operand = operand
                break;
        }
        //console.log('registers:', regA, regB, regC)
        //console.log('instructions:', instructions)
        //console.log('pointer:', insPointer, 'current:', instructions[insPointer], 'operand:', operand)
        //console.log('-----')

        switch (instructions[insPointer]) {
            case 0n:
                //adv
                regA = regA >> operand;
                break;
            case 1n:
                //bxl
                regB ^= instructions[insPointer + 1n]
                break;
            case 2n:
                //bst
                regB = operand & 0x7n
                break;
            case 3n:
                //jnz
                if (regA !== 0n) {
                    insPointer = operand - 2n
                }
                break;
            case 4n:
                //bxc
                regB = regB ^ regC
                break;
            case 5n:
                //out
                let s = operand & 0x7n
                output.push(s)
                break;
            case 6n:
                //bdv
                regB = regA >> operand
                break;
            case 7n:
                //cdv
                regC = regA >> operand
                break;
            default:
                throw new Error(`Unknown opcode: ${instructions[insPointer]}`);
        }

        insPointer += 2n
    }
    return output
}
function findValueOfA(a, b, c, inst) {
    let output = [];
    a = 1n;
    let continueLoop = true;

    while (continueLoop) {

        output = sim(a, b, c, inst);

        if (arraysEqual(output, inst)) {
            return a;
        }

        if (inst.length > output.length) {
            a *= 2n;
            continue;
        }

        if (inst.length === output.length) {
            for (let j = inst.length - 1; j >= 0; j--) {
                if (inst[j] !== output[j]) {
                    a += 8n ** BigInt(j);
                    break;
                }
            }
        }

        if (inst.length < output.length) {
            a = Math.floor(a / 2n);
        }
    }

    return a;
}

function arraysEqual(arr1, arr2) {
    if (arr1.length !== arr2.length) return false;
    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) return false;
    }
    return true;
}



console.log(sim(regA, regB, regC, instructions).join(','))
console.log(findValueOfA(regA, regB, regC, instructions))



