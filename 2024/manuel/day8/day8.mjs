import { in_bounds, neighbors, readFile, readGrid, readInts, readLines } from '../util/load-csv.mjs';

function compareAntennas(dic, fn) {
    for (const antennas of dic.values()) {
        for (const antenna of antennas) {
            for (const other of antennas) {
                if (antenna !== other) {
                    fn(antenna, antenna.x - other.x, antenna.y - other.y);
                }
            }
        }
    }
}


(async () => {
    let input = await readFile('./day8.txt')
    let map = input.split("\n").map(line => line.split(""));
    let antennas = new Map()

    for (let y = 0; y < map.length; y++) {
        for (let x = 0; x < map[y].length; x++) {
            const sym = map[y][x]
            if (sym !== '.') {
                if (antennas.has(sym)) {
                    const others = antennas.get(sym)
                    antennas.set(sym, [{ x: x, y: y }].concat(others))
                } else {
                    antennas.set(sym, [{ x: x, y: y }])
                }
            }
        }
    }
    console.log(antennas)

    compareAntennas(antennas, ({ x, y }, dirx, diry) => {
        if (map[y - diry * 2]?.[x - dirx * 2]) map[y - diry * 2][x - dirx * 2] = "#";
    });
    console.log(map.reduce((sum, line) => sum + line.filter(c => c === "#").length, 0))

    input = await readFile('./day8.txt')
    map = input.split("\n").map(line => line.split(""));
    antennas = new Map()

    for (let y = 0; y < map.length; y++) {
        for (let x = 0; x < map[y].length; x++) {
            const sym = map[y][x]
            if (sym !== '.') {
                if (antennas.has(sym)) {
                    const others = antennas.get(sym)
                    antennas.set(sym, [{ x: x, y: y }].concat(others))
                } else {
                    antennas.set(sym, [{ x: x, y: y }])
                }
            }
        }
    }
    compareAntennas(antennas, ({ x, y }, dirx, diry) => {
        while (map[y - diry]?.[x - dirx]) map[y -= diry][x -= dirx] = "#";
    });
    console.log(map.reduce((sum, line) => sum + line.filter(c => c === "#").length, 0))



})();


