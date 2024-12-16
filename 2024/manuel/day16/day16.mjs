import { neighbors } from '../util/load-csv.mjs';
import fs from 'fs'



let input = fs.readFileSync('./day16.txt', 'utf8')


let map = input.split('\n').map(l => l.split(''))
const startY = map.findIndex(line => line.includes("S"));
const startX = map[startY].indexOf("S");
const start = { y: startY, x: startX }

const endy = map.findIndex(line => line.includes("E"));
const endx = map[endy].indexOf("E");
const end = { y: endy, x: endx }
let score = walkGraph(map, start, end)
console.log(score)
score = walkGraphAndMark(map, start, end, score)
console.log(score)


function walkGraphAndMark(map, start, end, minscore) {
    const directions = [
        { dx: 1, dy: 0, name: "E" },
        { dx: 0, dy: 1, name: "S" },
        { dx: -1, dy: 0, name: "W" },
        { dx: 0, dy: -1, name: "N" },
    ];

    const costs = new Map()
    const optimalPaths = new Set()
    const paths = new Map()

    let queue = []
    queue.push({ cost: 0, x: start.x, y: start.y, dir: 0, path: [`${start.x},${start.y}`] });
    while (queue.length) {
        const { cost, x, y, dir, path } = queue.shift();
        const key = `${x},${y},${dir}`;

        if (cost > minscore) continue;

        if (costs.has(key) && costs.get(key) < cost) continue;
        costs.set(key, cost);
        paths.set(key, path);

        if (end.x === x && end.y === y) {
            path.forEach((pos) => optimalPaths.add(pos));
            continue
        }


        directions.forEach((newDir, newDirIndex) => {
            const newX = x + newDir.dx;
            const newY = y + newDir.dy;

            if (newX < 0 || newY < 0 || newX >= map[0].length || newY >= map.length) return;
            if (map[newY][newX] === "#") return;

            const moveCost = 1;
            const turnCost = getTurnCost(dir, newDirIndex);

            const totalCost = cost + moveCost + turnCost;
            const newPos = `${newX},${newY}`;

            const newPath = [...path, newPos];
            const insertIndex = queue.findIndex(({ cost }) => cost > totalCost);
            if (insertIndex === -1) {
                queue.push({
                    cost: totalCost,
                    x: newX,
                    y: newY,
                    dir: newDirIndex,
                    path: newPath,
                });
            } else {
                queue.splice(insertIndex, 0, {
                    cost: totalCost,
                    x: newX,
                    y: newY,

                    dir: newDirIndex,
                    path: newPath,
                });
            }
        });
    }
    return optimalPaths.size;
}

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
            const turnCost = getTurnCost(dir, newDirIndex);
            const totalCost = cost + moveCost + turnCost;

            queue.push({ cost: totalCost, x: newX, y: newY, dir: newDirIndex });
            queue.sort((a, b) => a.cost - b.cost);
        });
    }
    return Infinity;
}

function getTurnCost(from, to) {
    const diff = Math.abs(from - to);
    const turns = Math.min(diff, 4 - diff);
    return turns * 1000;
}

