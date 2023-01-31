import axiosInterface from "./axiosInterface";

/**
 * Get infos about badges
 */
async function getBadgesAPI(accessToken) {
  const response = await axiosInterface("GET", "/badge", {
    headers: { accessToken: { accessToken } },
  });

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default getBadgesAPI;
