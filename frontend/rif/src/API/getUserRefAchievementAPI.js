import axiosInterface from "./axiosInterface";

/**
 * Get infomations about a User representative achievement from the user's primary key(id).
 * This request will return the {id,title,description,ImgPath,achievedAt} properties in onDisplayAchievement property with array type.
 */
async function getUserRefAchievementAPI(id) {
  const response = await axiosInterface(
    "GET",
    "/api/member/achievement",
    {},
    {},
    { memberId: id }
  );

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default getUserRefAchievementAPI;
