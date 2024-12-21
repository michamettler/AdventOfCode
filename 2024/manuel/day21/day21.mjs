import fs from 'fs'


const bfsdir = {
    '^': { x: 0, y: -1 },
    '>': { x: 1, y: 0 },
    'v': { x: 0, y: 1 },
    '<': { x: -1, y: 0 }
};

const keypad = {
    7: { x: 0, y: 0 },
    8: { x: 1, y: 0 },
    9: { x: 2, y: 0 },
    4: { x: 0, y: 1 },
    5: { x: 1, y: 1 },
    6: { x: 2, y: 1 },
    1: { x: 0, y: 2 },
    2: { x: 1, y: 2 },
    3: { x: 2, y: 2 },
    X: { x: 0, y: 3 },
    0: { x: 1, y: 3 },
    A: { x: 2, y: 3 }
};

const dirs = {
    X: { x: 0, y: 0 },
    '^': { x: 1, y: 0 },
    A: { x: 2, y: 0 },
    '<': { x: 0, y: 1 },
    'v': { x: 1, y: 1 },
    '>': { x: 2, y: 1 },
};


let keycodes = fs.readFileSync('./day21.txt', 'utf8').trim().split('\n')

const memo = {};

const res = keycodes.reduce((sum, code) => {
    const numerical = parseInt(code.split('A')[0]);
    return sum + numerical * getKeyPresses(keypad, code, 2, memo);
}, 0);
console.log(res)

const res2 = keycodes.reduce((sum, code) => {
    const numerical = parseInt((code.split('').filter(character => character.match(/\d/)).join('')));
    return sum + numerical * getKeyPresses(keypad, code, 25, memo);
}, 0);

console.log(res2)

function getCommand(input, start, end) {
    const queue = [{ ...input[start], path: '' }];
    const distances = {};

    if (start === end) return ['A'];

    let allPaths = [];
    while (queue.length) {
        const current = queue.shift();
        if (current === undefined) break;

        if (current.x === input[end].x && current.y === input[end].y) allPaths.push(current.path + 'A');
        if (distances[`${current.x},${current.y}`] !== undefined && distances[`${current.x},${current.y}`] < current.path.length) continue;

        Object.entries(bfsdir).forEach(([direction, vector]) => {
            const position = { x: current.x + vector.x, y: current.y + vector.y };

            if (input.X.x === position.x && input.X.y === position.y) return;

            const button = Object.values(input).find(button => button.x === position.x && button.y === position.y);
            if (button !== undefined) {
                const newPath = current.path + direction;
                if (distances[`${position.x},${position.y}`] === undefined || distances[`${position.x},${position.y}`] >= newPath.length) {
                    queue.push({ ...position, path: newPath });
                    distances[`${position.x},${position.y}`] = newPath.length;
                }
            }
        });
    }

    return allPaths.sort((a, b) => a.length - b.length);
}


function getKeyPresses(input, code, robot, memo) {
    const key = `${code},${robot}`;
    if (memo[key] !== undefined) return memo[key];

    let current = 'A';
    let length = 0;
    for (let i = 0; i < code.length; i++) {
        const moves = getCommand(input, current, code[i]);
        if (robot === 0) {
            length += moves[0].length;
        } else {
            length += Math.min(...moves.map(move => getKeyPresses(dirs, move, robot - 1, memo)));
        }
        current = code[i];
    }

    memo[key] = length;
    return length;
}

