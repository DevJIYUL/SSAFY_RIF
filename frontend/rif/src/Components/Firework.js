import { useEffect } from "react";
import { useSelector } from "react-redux";

let ctx;
let canvas;

const Firework = () => {
  const badgeTier = useSelector((state) => state.lotto.badgeTier);

  useEffect(() => {
    if (!canvas) {
      canvas = document.getElementById("firework");
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;
      ctx = canvas.getContext("2d");
    }
  }, []);

  useEffect(() => {
    if (canvas && badgeTier) {
      run(badgeTier);
    }
  }, [badgeTier]);

  let shells = [];
  let pass = [];

  const colors = {
    1: ["#C9D8EC", "#6685BB", "#D8E0EE"],
    2: ["#F5E763", "#FBED9C", "#E7D85A"],
    3: ["#636363", "#D2D2D2", "#969696"],
    4: ["#E4A07A", "#97674B", "#E8D8CF"],
  };

  // make new shell (big chunk of firework entity)
  const newShell = (tier) => {
    var left = Math.random() > 0.5;
    var shell = {};
    shell.x = 1 * left;
    shell.y = 1;
    shell.xoff = (0.001 + Math.random() * 0.007) * (left ? 1 : -1);
    shell.yoff = 0.01 + Math.random() * 0.0007;
    shell.size = Math.random() * 7 + 3; // 폭죽의 크기
    shell.color = colors[tier][Math.floor(Math.random() * colors[tier].length)];

    shells.push(shell);
  };

  // make new pass (small chunk of firework entity)
  const newPass = (shell) => {
    //   var pasCount = Math.ceil(Math.pow(shell.size, 3) * Math.PI);
    var pasCount = 40;

    for (let i = 0; i < pasCount; i++) {
      var pas = {};
      pas.x = shell.x * canvas.width;
      pas.y = shell.y * canvas.height;

      var a = Math.random() * 4;
      var s = Math.random() * 10;

      pas.xoff = s * Math.sin((5 - a) * (Math.PI / 2));
      pas.yoff = s * Math.sin(a * (Math.PI / 2));

      pas.color = shell.color;
      pas.size = Math.sqrt(shell.size);

      pass.push(pas);
    }
  };

  var lastRun = 0;
  var runAgain = 1;

  function run(tier) {
    var dt = 1;
    if (lastRun !== 0) {
      dt = Math.min(50, performance.now() - lastRun);
    }
    lastRun = performance.now();

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    // ctx.fillStyle = "rgba(0,0,0,0.25)";
    // ctx.fillRect(0, 0, canvas.width, canvas.height);

    // 폭죽 shell을 n개로 제한한다
    if (shells.length < 10 && runAgain) {
      newShell(tier);
    } else {
      runAgain = 0;
    }

    // 각각의 shell에 대해 [{x, y, xoff, yoff, size...}, {}, {},]
    for (let ix in shells) {
      var shell = shells[ix];

      ctx.beginPath();
      ctx.arc(
        shell.x * canvas.width,
        shell.y * canvas.height,
        shell.size,
        0,
        2 * Math.PI
      );
      ctx.fillStyle = shell.color;
      ctx.fill();

      shell.x -= shell.xoff;
      shell.y -= shell.yoff;
      shell.xoff -= shell.xoff * dt * 0.001;
      shell.yoff -= (shell.yoff + 0.2) * dt * 0.00005;

      if (shell.yoff < 0) {
        newPass(shell);
        shells.splice(ix, 1);
      }
    }

    for (let ix in pass) {
      var pas = pass[ix];

      ctx.beginPath();
      ctx.arc(pas.x, pas.y, pas.size, 0, 2 * Math.PI);
      ctx.fillStyle = pas.color;
      ctx.fill();

      pas.x -= pas.xoff;
      pas.y -= pas.yoff;
      pas.xoff -= pas.xoff * dt * 0.001;
      pas.yoff -= (pas.yoff + 5) * dt * 0.0005;
      pas.size -= dt * 0.002 * Math.random();

      if (pas.y > canvas.height || pas.y < -50 || pas.size <= 0) {
        pass.splice(ix, 1);
      }

      if (!pass.length) {
        cancelAnimationFrame(() => {
          run(tier);
        });
        runAgain = 1;
        return;
      }
    }
    requestAnimationFrame(() => {
      run(tier);
    });
  }

  return (
    <div>
      <canvas
        id="firework"
        style={{
          position: "absolute",
          top: "0px",
          bottom: "0px",
          zIndex: "-1",
        }}
      ></canvas>
    </div>
  );
};

export default Firework;
