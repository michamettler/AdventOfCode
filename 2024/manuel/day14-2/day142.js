const fs = require('fs');

// Read input (format: p=x,y v=x,y)
const lines = fs.readFileSync('day14.txt', 'utf8').trim().split('\n');

let initialRobots = lines.map(line => {
    const [pPart, vPart] = line.split(' ');
    const pMatch = pPart.match(/p=(-?\d+),(-?\d+)/);
    const vMatch = vPart.match(/v=(-?\d+),(-?\d+)/);

    return {
        x: parseInt(pMatch[1], 10),
        y: parseInt(pMatch[2], 10),
        vx: parseInt(vMatch[1], 10),
        vy: parseInt(vMatch[2], 10)
    };
});

function getBounds(robots) {
    let minX = Infinity, maxX = -Infinity;
    let minY = Infinity, maxY = -Infinity;
    for (const r of robots) {
        if (r.x < minX) minX = r.x;
        if (r.x > maxX) maxX = r.x;
        if (r.y < minY) minY = r.y;
        if (r.y > maxY) maxY = r.y;
    }
    return { minX, maxX, minY, maxY };
}

function step(robots) {
    for (const r of robots) {
        r.x += r.vx;
        r.y += r.vy;
    }
}

// We'll simulate time, checking the area each second
// until the area no longer decreases after some time.
function findBestTime(robots) {
    // Clone robots array so we don't modify the original during search
    let simRobots = robots.map(r => ({ ...r }));

    let time = 0;
    let minimalArea = Infinity;
    let bestTime = 0;
    let increasingCount = 0;
    let lastArea = Infinity;

    while (true) {
        const { minX, maxX, minY, maxY } = getBounds(simRobots);
        const width = maxX - minX;
        const height = maxY - minY;
        const area = width * height;

        if (area < minimalArea) {
            minimalArea = area;
            bestTime = time;
            increasingCount = 0; // reset because we found a new minimal
        } else {
            // If area is now larger than last step, count this as an increase
            if (area > lastArea) {
                increasingCount++;
            } else {
                // If it didn't increase compared to last step, reset the count
                increasingCount = 0;
            }
        }

        lastArea = area;

        // If we've seen area grow for many steps, we've likely passed the minimal point
        if (increasingCount > 100) {
            break;
        }

        // Step forward in time
        step(simRobots);
        time++;
    }

    return bestTime;
}

// Print the pattern at a given time
function printPatternAtTime(robots, time) {
    // Simulate to the given time
    let simRobots = robots.map(r => ({ ...r }));
    for (let t = 0; t < time; t++) {
        step(simRobots);
    }

    const { minX, maxX, minY, maxY } = getBounds(simRobots);
    const width = maxX - minX + 1;
    const height = maxY - minY + 1;

    // Initialize a 2D array of '.'
    const grid = Array.from({ length: height }, () => Array(width).fill('.'));

    // Mark robot positions
    for (const r of simRobots) {
        const gx = r.x - minX;
        const gy = r.y - minY;
        grid[gy][gx] = '#';
    }

    // Print the pattern
    for (let row = 0; row < height; row++) {
        console.log(grid[row].join(''));
    }
}

const bestTime = findBestTime(initialRobots);
console.log("Fewest number of seconds for the Easter egg to appear:", bestTime);

console.log("Pattern at that time:");
printPatternAtTime(initialRobots, bestTime);

