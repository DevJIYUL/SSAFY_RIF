import axiosInterface from "./axiosInterface"

/**
 * Get infos about a User from the user's primary key(id).
 * Will return the users id, name, point, exp, profileImgPath
 */
async function lottoAPI() {
  const response = await axiosInterface("PUT", "api/gatcha")

  if (response.status === 200) {
    return response
  }

  return response.response
}

export default lottoAPI
