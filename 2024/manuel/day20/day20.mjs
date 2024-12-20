import fs from 'fs'


let input = fs.readFileSync('./day20.txt', 'utf8')


let map = input.split('\n').map(row => row.split('')).filter(r => r.length > 0)
let sy = map.findIndex(row => row.includes('S'))
let sx = map[sy].findIndex(f => f === 'S')
const start = { x: sx, y: sy }


let ey = map.findIndex(row => row.includes('E'))
let ex = map[ey].findIndex(f => f === 'E')
const end = { x: ex, y: ey }

let track = new Map()
track.set(`${start.y},${start.x}`, 0);
let cur = [start.y, start.x]
let curstep = 0;

while (cur[0] !== end.y || cur[1] !== end.x) {
    curstep += 1;
    const y = cur[0];
    const x = cur[1];

    const directions = [[-1, 0], [0, -1], [0, 1], [1, 0]];

    for (let d = 0; d < directions.length; d++) {
        const [dy, dx] = directions[d];
        const newy = y + dy;
        const newx = x + dx;
        const key = `${newy},${newx}`;

        if (!track.has(key) && 'SE.'.includes(map[newy][newx])) {
            cur = [newy, newx];
            track.set(key, curstep)
            break;
        }
    }
}


let over100 = 0

for (const [pos, count] of track.entries()) {
    const [y, x] = pos.split(',').map(n => parseInt(n))
    const offsets = [
        [2, 0],
        [-2, 0],
        [0, 2],
        [0, -2],
        [1, 1],
        [1, -1],
        [-1, 1],
        [-1, -1]
    ];

    offsets.forEach(([dy, dx]) => {
        const newY = y + dy;
        const newX = x + dx;
        if (track.has(`${newY},${newX}`)) {
            const distance = track.get(`${newY},${newX}`) - count - 2

            if (distance >= 100) {
                over100++;
            }
        }
    });
}
console.log(over100)

over100 = 0
const maxDistance = 20;
const offsets = [];

for (let x = -maxDistance; x <= maxDistance; x++) {
    for (let y = -maxDistance; y <= maxDistance; y++) {
        if (Math.abs(x) + Math.abs(y) <= maxDistance) {
            offsets.push([y, x]);
        }
    }
}


for (const [pos, count] of track.entries()) {
    const [y, x] = pos.split(',').map(n => parseInt(n))
    offsets.forEach(([dy, dx]) => {
        const newY = y + dy;
        const newX = x + dx;
        const key = `${newY},${newX}`;
        if (track.has(key)) {
            const distance = track.get(key) - count - (Math.abs(dy) + Math.abs(dx))
            if (distance >= 100) {
                //printGrid(map, { x: x, y: y }, { x: newX, y: newY })
                over100++;
            }
        }
    });
}



console.log(over100)

function printGrid(map, pointA, pointB) {
    map.forEach((row, y) => {
        let temp = [...row]
        if (y === pointA.y) {
            temp[pointA.x] = 'A'
        }
        if (y === pointB.y) {
            temp[pointB.x] = 'B'
        }
        console.log(temp.join(''));

    });
}




console.log(over100)
