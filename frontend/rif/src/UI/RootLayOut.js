import { Outlet, useNavigate, useLocation } from "react-router-dom";
import NavigationComponent from "./NavigationComponent";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { navigationActions } from "../store/navigationSlice";
import useWindowDimensions from "../Components/useWindowDimensions";
import BigWindowComponent from "../Components/BigWindowComponent";
import TopBarComponent from "../Components/TopBarComponent";

const RootLayOut = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();
  const { width } = useWindowDimensions();
  const [navigationHidden, setNavigationHidden] = useState(false);

  const locationPath = location.pathname;
  let routeAddress;
  if (locationPath) {
    routeAddress = location.pathname.split("/")[1];
  } else {
    routeAddress = "";
  }
  const titleObj = {
    "": "",
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

  useEffect(() => {
    if (width >= 480 || width <= 350) {
      setNavigationHidden(true);
    } else {
      setNavigationHidden(false);
    }
  }, [width, setNavigationHidden]);

  return (
    <div>
      <TopBarComponent />
      {navigationHidden ? (
        <p>
          <BigWindowComponent width={width} />
        </p>
      ) : (
        <main style={{ margin: "0px 0px 70px 0px" }}>
          <Outlet />
        </main>
      )}
      <NavigationComponent hidden={navigationHidden} />
    </div>
  );
};

export default RootLayOut;
