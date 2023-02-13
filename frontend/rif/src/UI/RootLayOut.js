import { Outlet, useNavigate, useLocation } from "react-router-dom";
import NavigationComponent from "./NavigationComponent";
import { useEffect } from "react";

const RootLayOut = () => {
  const navigate = useNavigate();
  const location = useLocation();
  console.log(location);
  useEffect(() => {
    if (location.pathname === "/") {
      navigate("/home");
    } else if (location.pathname.endsWith("/")) {
      navigate(location.pathname.slice(0, -1));
    }
  }, [navigate, location]);

  return (
    <>
      <main style={{ marginBottom: "70px" }}>
        <Outlet />
      </main>
      <NavigationComponent />
    </>
  );
};

export default RootLayOut;
