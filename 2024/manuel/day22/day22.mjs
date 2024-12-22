import fs from 'fs'

let secrets = fs.readFileSync('./day22.txt', 'utf8').trim().split('\n').map(BigInt)

console.log(secrets)


function evolveSecretNumber(secretNumber) {
    const mix = (value, current) => current ^ value;  // Bitwise XOR
    const prune = (current) => current % 16777216n;    // Modulo 2^24

    let valueStep1 = secretNumber * 64n;
    secretNumber = mix(valueStep1, secretNumber);
    secretNumber = prune(secretNumber);

    let valueStep2 = BigInt(secretNumber / 32n);
    secretNumber = mix(valueStep2, secretNumber);
    secretNumber = prune(secretNumber);

    let valueStep3 = secretNumber * 2048n;
    secretNumber = mix(valueStep3, secretNumber);
    secretNumber = prune(secretNumber);

    return secretNumber;
}
let sum = 0n
for (const s of secrets) {
    let res = s
    for (let i = 0; i < 2000; i++) {
        res = evolveSecretNumber(res)
    }
    console.log(s, ':', res)
    sum += res
}

console.log(sum)


let diffs = secrets.map(() => []);
let cache = new Map();
let max = 0;

for (let i = 0; i < 2000; i++) {
    secrets = secrets.map((prev, j) => {
        let next = evolveSecretNumber(prev);

        diffs[j].push(Number((next % 10n) - (prev % 10n)));
        if (diffs[j].length === 4) {

            let key = diffs[j].join(",");
            let value = cache.get(key) || { sum: 0, set: new Set() };

            if (!value.set.has(j)) {
                value.set.add(j);
                value.sum += Number(next % 10n);
                max = Math.max(max, value.sum);
            }
            cache.set(key, value);
            diffs[j].shift();
        }
        return next;
    });
}

console.log(max)
