import axiosInterface from "./axiosInterface";

/**
 * Get infomations about a User representative badge from the user's primary key(id).
 * This request will return the {id,title,description,ImgPath,achievedAt} properties in onDisplayBadge property with array type.
 */
async function getUserRefBadgeAPI(id) {
  const response = await axiosInterface(
    "GET",
    "/api/member/badge",
    {},
    {},
    { memberId: id }
  );

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default getUserRefBadgeAPI;
