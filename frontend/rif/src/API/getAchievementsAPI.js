import axiosInterface from "./axiosInterface";

/**
 * Get infos about badges
 */
async function getAchievementsAPI(accessToken) {
  const response = await axiosInterface(
    "GET",
    "api/member/achievement",
    {},
    {
      Authorization: `Baerer ${accessToken}`,
    }
  );

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default getAchievementsAPI;
