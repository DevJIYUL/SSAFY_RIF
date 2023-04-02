import axiosInterface from "./axiosInterface";
import { useEffect, useState } from "react";

async function getLogAPI(accessToken, memberId, page, size) {
  const response = await axiosInterface(
    "GET",
    "/api/v/member/riflog",
    {},
    {
      Authorization: `Bearer ${accessToken}`,
    },
    {
      memberId: memberId,
      page: page,
      size: size,
    }
  );
  if (response.status === 200) {
    if (response.newToken) {
      response.data.newToken = response.newToken;
    }
    return response.data;
  } else {
  }
  return response;
}

export default function useLogGetAPI(accessToken, memberId, page, size) {
  console.log = function () {};
  console.error = function () {};
  console.warn = function () {};

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [logs, setLogs] = useState([]);
  const [hasMore, setHasMore] = useState(true);
  const [newToken, setNewToken] = useState("");
  const [statusCode, setStatusCode] = useState("");

  useEffect(() => {
    if (statusCode === 307) {
      console.log("the end");
      return;
    }
    setLoading(true);
    setError(false);

    const resData = getLogAPI(accessToken, memberId, page, size);
    resData
      .then((res) => {
        console.log(res);
        if (res.newToken) {
          console.log(res);
          setNewToken(res.newToken);
        } else if (res.response && res.response.status === 307) {
          console.log(307);
          setStatusCode(307);
          return;
        }

        const newLogs = res.content;

        if (newLogs.length) {
          // 새로운 로그가 아직 있다면
          setLogs((prevLogs) => {
            const totalLogs = [...prevLogs, ...newLogs];
            return totalLogs;
          });
          setLoading(false);
        }
        if (res.last) {
          // 새로운 로그가 없다면
          setHasMore(false);
          setLoading(false);
        }
      })
      .catch((err) => {
        console.log(err);
        setError(true);
      });
  }, [accessToken, memberId, page, size, statusCode]);

  return { loading, error, logs, hasMore, newToken, statusCode };
}
