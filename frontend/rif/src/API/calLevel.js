const levelSection = [0, 50, 150, 300, 500, 750, 1050, 1400, 1800, 2250];
const index = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

/**
 *
 * @param {} exp
 * @returns [level, caledExp]
 */
export default function calLevel(exp) {
  let level = 1;
  let caledExp = 0;
  if (exp < 2750) {
    level = index.find((value) => levelSection[value] > exp);
    caledExp = parseInt(
      ((exp - levelSection[level - 1]) /
        (levelSection[level] - levelSection[level - 1])) *
        100
    );
  } else {
    level = 10;
    caledExp = exp - 2250;
  }

  return [level, caledExp];
}
