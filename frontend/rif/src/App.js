import { RouterProvider } from "react-router-dom";
import router from "./router";

function App() {
  if (process.env.NODE_ENV === "production") {
    console.log = function () {};
    console.warn = function () {};
    console.warn = function () {};
  }
  return <RouterProvider router={router} />;
}

export default App;
