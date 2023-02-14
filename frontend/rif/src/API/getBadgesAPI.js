import axiosInterface from "./axiosInterface";

/**
 * Get infos about badges
 */
async function getBadgesAPI(accessToken, memberId) {
  console.log(accessToken, memberId, "ss");
  const response = await axiosInterface(
    "GET",
    "/api/v/member/badge",
    {},
    {
      Authorization: `Bearer ${accessToken}`,
    },
    { memberId: memberId }
  );

  console.log(response);

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default getBadgesAPI;
