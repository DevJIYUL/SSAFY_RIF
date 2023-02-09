import { Outlet } from "react-router-dom";
import NavigationComponent from "./NavigationComponent";

const RootLayOut = () => {
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
