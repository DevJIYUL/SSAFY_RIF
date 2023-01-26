import axiosInterface from "./axiosInterface"

/** Response status code in RIF is 200 or 401.
 */
async function loginAPI(id, password) {
  const response = await axiosInterface("post", "user/login", { id, password })

  if (response.status === 200) {
    return response
  }

  return response.response
}

export default loginAPI
