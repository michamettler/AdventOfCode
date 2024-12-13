import { getNeighbors, readFile, inBounds, gcd } from '../util/load-csv.mjs';


(async () => {
    let input = await readFile('./day13.txt')
    input = input.split("\n")

    let calcs = []

    for (let i = 0; i < input.length; i++) {
        let calc = input.slice(i, i + 3)
        i += 3

        const result = calc.reduce((acc, line) => {
            const [label, data] = line.split(': ');

            let key = label.includes('Button') ? label : 'Prize';
            if (key === 'Button A') {
                key = 'A'
            } else if (key === 'Button B') {

                key = 'B'
            }

            const coords = data.split(', ').reduce((coordsAcc, pair) => {
                const [axis, value] = pair.includes('+') ? pair.split('+') : pair.split('=');
                coordsAcc[axis] = parseInt(value);
                return coordsAcc;
            }, {});
            if (key === 'Prize') {
                acc[key] = coords;
            } else {
                acc[key] = coords;
            }
            return acc;
        }, {});
        calcs.push(result)
    }

    let tokens = 0;
    for (let i = 0; i < calcs.length; i++) {
        const c = calcs[i]
        let gcdx = findMinX1X2(c.A.X, c.B.X, c.Prize.X)
        let gcdy = findMinX1X2(c.A.Y, c.B.Y, c.Prize.Y)
        if (gcdx.length && gcdy.length) {
            let solutions = gcdx.filter(res1 =>
                gcdy.some(res2 => res1.a === res2.a && res1.b === res2.b)
            )
            solutions = solutions.map((el) => {
                return { a: el.a * 3, b: el.b }
            })
            solutions.sort((a, b) => a.a + a.b - (b.a + b.b))
            if (solutions.length) {

                tokens += solutions[0].a
                tokens += solutions[0].b
            }
        }
    }
    console.log(tokens)


    tokens = 0;
    for (let i = 0; i < calcs.length; i++) {
        const c = calcs[i]
        let gcdx = calculateButtonPresses(c.A.X, c.B.X, c.Prize.X + 10000000000000, c.A.Y, c.B.Y, c.Prize.Y + 10000000000000)
        if (gcdx) {
            tokens += gcdx.result
        }
    }
    console.log(tokens)
})();

function findMinX1X2unlimited(a, b, c) {
    let res = []

    for (let x1 = 0; x1 < 10000000000000; x1++) {
        for (let x2 = 0; x2 < 10000000000000; x2++) {
            if (x1 * a + x2 * b === c && x1 < 100 && x2 < 100) {
                res.push({ a: x1, b: x2 })
            }
        }
    }

    return res
}

function findMinX1X2(a, b, c) {
    let res = []

    for (let x1 = 0; x1 < 100; x1++) {
        for (let x2 = 0; x2 < 100; x2++) {
            if (x1 * a + x2 * b === c && x1 < 100 && x2 < 100) {
                res.push({ a: x1, b: x2 })
            }
        }
    }

    return res
}

function calculateButtonPresses(c1, c2, c3, c4, c5, c6) {
    const determinant = c1 * c5 - c2 * c4;

    if (determinant === 0) {
        return null
    }

    const A = (c3 * c5 - c2 * c6) / determinant;
    const B = (c1 * c6 - c3 * c4) / determinant;

    // Calculate the result of the formula
    const result = 3 * A + B;

    if (A % 1 === 0 && A % 1 === 0) {

        return {
            a: A,
            a: B,
            result: result
        };
    }
    return null

}
