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
    console.log(`Log API 성공 page: ${page}`);
    console.log(response.data, "response.data");
    return response.data;
  } else {
    console.log("Log API 실패");
  }
  return response;
}

export default function useLogGetAPI(accessToken, memberId, page, size) {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [logs, setLogs] = useState([]);
  const [hasMore, setHasMore] = useState(true);

  useEffect(() => {
    setLoading(true);
    setError(false);

    const resData = getLogAPI(accessToken, memberId, page, size);
    resData
      .then((res) => {
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
        setError(true);
      });
  }, [accessToken, memberId, page, size]);

  return { loading, error, logs, hasMore };
}
