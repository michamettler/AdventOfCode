import csv from 'csv-parser';
import fs from 'fs';

export function readCsv(filePath) {
    return new Promise((resolve, reject) => {
        const results = [];
        fs.createReadStream(filePath)
            .pipe(csv())
            .on('data', (data) => results.push(data))
            .on('end', () => resolve(results))
            .on('error', (error) => reject(error));
    });
}

export function readLines(filePath) {
    return new Promise((resolve, reject) => {
        fs.readFile(filePath, (err, data) => {
            if (err) reject(err);
            resolve(data.toString().split("\n"))
        });
    });
}

export function readFile(filePath) {
    return new Promise((resolve, reject) => {
        fs.readFile(filePath, (err, data) => {
            if (err) reject(err);
            resolve(data.toString())
        });
    });
}
export function readInts(string, seperator) {
    return string.split(seperator).map((item) => parseInt(item));
}

export function readBlocks(string, seperator) {
    return string.split(seperator)
}

export async function readGrid(filepath) {
    const lines = await readLines(filepath)
    let result = [];

    lines.forEach((str) => {
        result.push(str.split(""))
    });

    return result;
}

export function print_grid(grid) {
    if (!Array.isArray(grid) || grid.length === 0) {
        console.log("Empty or invalid grid.");
        return;
    }

    grid.forEach(row => {
        console.log(row.join(" "));
    });
}

export function to_1d(x, y, width) {
    return x * width + y;
}

export function to_2d(index, width) {
    const x = Math.floor(index / width); // Row index
    const y = index % width;            // Column index
    return [x, y];
}


export function in_bounds(grid, x, y) {
    const width = grid.length;
    const height = grid[0]?.length || 0;
    return x >= 0 && x < width && y >= 0 && y < height;
}


export function neighbors(grid, x, y, diagonals = false) {
    const directions = [
        [0, -1], // Up
        [-1, 0], // Left
        [1, 0],  // Right
        [0, 1],  // Down
    ];

    if (diagonals) {
        directions.push(
            [-1, -1], // Top-left
            [-1, 1],  // Top-right
            [1, -1],  // Bottom-left
            [1, 1]    // Bottom-right
        );
    }

    const result = [];
    for (const [dx, dy] of directions) {
        const nx = x + dx;
        const ny = y + dy;
        if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
            result.push([nx, ny]);
        }
    }

    return result;
}

export function gcd(a, b) {
    while (b !== 0) {
        const temp = b;
        b = a % b;
        a = temp;
    }
    return Math.abs(a); // GCD is always non-negative
}

export function lcm(a, b) {
    if (a === 0 || b === 0) return 0; // LCM of 0 with any number is 0
    return Math.abs(a * b) / gcd(a, b);
}

export function powerset(items) {
    const result = [[]]; // Start with the empty subset

    for (const item of items) {
        // For each existing subset, add a new subset with the current item included
        const newSubsets = result.map(subset => [...subset, item]);
        result.push(...newSubsets);
    }

    return result;
}


export function count_bits(n) {
    let count = 0;
    while (n !== 0) {
        count += n & 1; // Add 1 if the least significant bit is 1
        n = n >>> 1;    // Perform an unsigned right shift
    }
    return count;
}
