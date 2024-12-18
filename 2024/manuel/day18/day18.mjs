import fs from 'fs'


let input = fs.readFileSync('./day18.txt', 'utf8')


let lines = input.split('\n')
let coordinates = lines.map(line => line.split(',').map(n => parseInt(n))).filter(c => c.length === 2)
console.log(coordinates)
function printGrid(map) {
    map.forEach(row => {
        console.log(row.join(''));
    });
}

const rows = 71;
const cols = 71;
const map = Array.from({ length: rows }, () => Array(cols).fill('.'));


for (let i = 0; i < 3036; i++) {
    const [x, y] = coordinates[i]
    map[y][x] = '#'
}

printGrid(map)
const res = walkGraph(map, { x: 0, y: 0 }, { x: 70, y: 70 })
console.log(res)
const max = 3036//found through short manual binary search
console.log(coordinates[max])


function walkGraph(map, start, end) {
    const directions = [
        { dx: 1, dy: 0, name: "E" },
        { dx: 0, dy: 1, name: "S" },
        { dx: -1, dy: 0, name: "W" },
        { dx: 0, dy: -1, name: "N" },
    ];
    let queue = []
    let visited = new Set()


    queue.push({ cost: 0, x: start.x, y: start.y, dir: 0 });
    while (queue.length) {
        const { cost, x, y, dir } = queue.shift();
        const key = `${x},${y},${dir}`;

        if (end.x === x && end.y === y) return cost;

        if (visited.has(key)) continue;
        visited.add(key)

        directions.forEach((newDir, newDirIndex) => {
            const newX = x + newDir.dx;
            const newY = y + newDir.dy;

            if (newX < 0 || newY < 0 || newX >= map[0].length || newY >= map.length) return;
            if (map[newY][newX] === "#") return;

            const moveCost = 1;
            const totalCost = cost + moveCost;

            queue.push({ cost: totalCost, x: newX, y: newY, dir: newDirIndex });
            queue.sort((a, b) => a.cost - b.cost);
        });
    }
    return Infinity;
}



