import axios from "axios";

/** axiosInterface is using axios module.
 * It's just help to fetch easly axios's argument.
 * baseurl is fixed. So if you want to change this, you have to change in axiosInterface.
 * method and url are required. And their type is String.
 * data, headers and params, their type is object, they work like data in axios.
 * If axios returned right response, it return common response.
 * If axios raised an error, it return axios error. If you want to get response, you can use key named 'response'.
 */
export default async function axiosInterface(
  method,
  url,
  data = {},
  headers = {},
  params = {}
) {
  if (headers.Authorization) {
    const myInterceptor = axios.interceptors.response.use(
      function (res) {
        return res;
      },
      async function (err) {
        const { config } = err;
        const responseData = err.response.data;
        const state = JSON.parse(localStorage.getItem("persist:root"));
        const authentication = JSON.parse(state.auth);

        console.log(responseData, "aa");

        if (responseData === "Login Require") {
          axios.interceptors.response.eject(myInterceptor);
          const refreshResponse = await axios({
            method: "POST",
            url: "api/reissue",
            baseURL: "http://i8a501.p.ssafy.io:8080/",
            data: {
              grantType: "Bearer",
              accessToken: authentication.authentication.token,
              refreshToken: authentication.authentication.refreshToken,
            },
          });
          if (refreshResponse.status === 200) {
            config.headers.Authorization = `Bearer ${refreshResponse.data.accessToken}`;
            const data2 = await axios(config);
            data2.newToken = refreshResponse.data.accessToken;
            return Promise.resolve(data2);
          }
        } else if (responseData.message === "refreshtoken_expired") {
          return Promise.reject({
            message: "Redirect Login",
            value: responseData,
          });
        }
      }
    );
  }

  let response = await axios({
    method: method,
    url: url,
    baseURL: "http://i8a501.p.ssafy.io:8080/",
    data: data,
    headers: headers,
    params: params,
  })
    .then((res) => res)
    .catch((err) => {
      console.log(err);
      return err;
    });

  return response;
}
