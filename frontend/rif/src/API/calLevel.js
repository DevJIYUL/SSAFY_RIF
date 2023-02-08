const levelSection = [0, 50, 150, 300, 500, 750, 1050, 1400, 1800, 2250, 2750];
const index = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

/** This function calculates level, the return value is two.
 * If input exp is more then 2750, it returns level 10 and excess experience.
 * If exp is not over 2750, it return level and percentage of experience to level up from current level.
 */
export default function calLevel(exp) {
  let level = 0;
  let caledExp = 0;
  if (exp < 2750) {
    level = index.find((value) => levelSection[value] > exp) - 1;
    caledExp = parseInt(
      ((exp - levelSection[level]) /
        (levelSection[level + 1] - levelSection[level])) *
        100
    );
  } else {
    level = 10;
    caledExp = exp - 2750;
  }

  return [level, caledExp];
}
