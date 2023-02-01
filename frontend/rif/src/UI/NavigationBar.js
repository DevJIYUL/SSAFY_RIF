// import { useNavigate } from "react-router-dom";

const NavigationBar = () => {
  // const navigate = useNavigate();
  const navigationBarClickHandler = (event) => {
    const data = event.target.dataset.navigation;
    console.log(data);
  };
  return (
    <nav
      style={{ width: "100%", display: "flex", justifyContent: "space-evenly" }}
    >
      {["search", "ranking", "home", "main", "lot"].map((value, index) => (
        <img
          src={`navigationbar/${value}.png`}
          alt={value}
          key={`navigation${index}`}
          data-navigation={value}
          onClick={navigationBarClickHandler}
        />
      ))}
    </nav>
  );
};

export default NavigationBar;
