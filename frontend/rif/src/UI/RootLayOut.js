import { Outlet, useNavigate, useLocation } from "react-router-dom";
import NavigationComponent from "./NavigationComponent";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { navigationActions } from "../store/navigationSlice";

const RootLayOut = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  const routeAddress = location.pathname.split("/")[1];
  const titleObj = {
    login: "로그인",
    home: "",
    main: "프로필",
    description: "개요",
    usage: "사용법",
    lot: "뽑기",
    search: "검색",
    badge: "뱃지",
    ranking: "랭킹",
    "change-profile": "개인정보 수정",
    "change-password": "비밀번호 수정",
    log: "기기 사용 기록",
    achievement: "업적",
    member: "다른 멤버",
  };
  const title = Object.entries(titleObj).find(
    (list) => list[0] === routeAddress
  );
  console.log(title);
  useEffect(() => {
    const htmlTitle = document.querySelector("title");
    htmlTitle.innerHTML = "RIF " + title[1];

    if (location.pathname === "/") {
      navigate("/home");
    } else if (location.pathname.endsWith("/")) {
      navigate(location.pathname.slice(0, -1));
    } else {
      if (
        location.pathname !== "/login" &&
        location.pathname !== "/login/" &&
        location.pathname !== "/main"
      )
        dispatch(navigationActions.setPastHistory(location.pathname));
    }
  }, [navigate, location, dispatch, title]);

  return (
    <>
      <main style={{ margin: "10px 0px 70px 0px" }}>
        <Outlet />
      </main>
      <NavigationComponent />
    </>
  );
};

export default RootLayOut;
