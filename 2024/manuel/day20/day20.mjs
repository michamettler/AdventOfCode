import fs from 'fs'


let input = fs.readFileSync('./day20.tst', 'utf8')


let map = input.split('\n').map(row => row.split('')).filter(r => r.length > 0)
let sy = map.findIndex(row => row.includes('S'))
let sx = map[sy].findIndex(f => f === 'S')
const start = { x: sx, y: sy }


let ey = map.findIndex(row => row.includes('E'))
let ex = map[ey].findIndex(f => f === 'E')
const end = { x: ex, y: ey }


console.log(map)
console.log(start, end)

let track = new Map()
track.set(`${start.x},${start.y}`, 0);

let cur = [start.y, start.x]
let curstep = 0;

while (cur[0] !== end[0] || cur[1] !== end[1]) {
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
            console.log('adding key')
            break;
        }
    }
}

console.log(track)
