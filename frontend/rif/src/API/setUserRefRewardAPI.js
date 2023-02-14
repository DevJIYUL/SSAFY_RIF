import axiosInterface from "./axiosInterface";

/**
 * get memberId and achievementId,
 * and set the achievement as the user's representative one
 */
async function setUserRefRewardAPI(type, accessToken, memberId, rewardId) {
  let response;

  if (type === "badge") {
    response = await axiosInterface(
      "PATCH",
      "api/v/member/badge",
      {},
      { Authorization: `Bearer ${accessToken}` }, // headers
      { memberId: memberId, badgeId: rewardId } // params
    );
  } else {
    // if type === "achievement"
    response = await axiosInterface(
      "PATCH",
      "api/v/member/achievement",
      {},
      { Authorization: `Bearer ${accessToken}` }, // headers
      { memberId: memberId, achievementId: rewardId } // params
    );
  }

  if (response.status === 200) {
    // console.log("set ref Reward SUCCESS");
    console.log(response);
  } else {
    // console.log("set ref Reward FAILED");
    console.log(response);
  }

  return response;
}

export default setUserRefRewardAPI;
