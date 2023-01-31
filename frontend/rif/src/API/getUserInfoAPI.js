import axiosInterface from "./axiosInterface"

/**
 * Get infos about a User from the user's primary key(id).
 * Will return the users id, name, point, exp, profileImgPath
 */
async function getUserInfoAPI(id) {
  const response = await axiosInterface("GET", "user/profile", { id })

  if (response.status === 200) {
    return response
  }

  return response.response
}

export default getUserInfoAPI
