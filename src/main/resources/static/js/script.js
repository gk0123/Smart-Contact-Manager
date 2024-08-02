console.log("Script Loaded");

let currentTheme = getTheme();
const changeThemebutton = document.querySelector("#theme_change_button");

document.addEventListener("DOMContentLoaded", () => {
  //initially this function will run
  changeTheme(currentTheme);
  //change the text of button
  changeThemebutton.querySelector("span").textContent = currentTheme;
});

//TODO
function changeTheme() {
  //set to webpage
  document.querySelector("html").classList.add(currentTheme);

  //set the button listener
  changeThemebutton.addEventListener("click", (event) => {
    console.log("Change Theme btn clicked");
    //remove the current theme
    const oldTheme = currentTheme;
    if (currentTheme == "dark") {
      currentTheme = "light";
    } else {
      currentTheme = "dark";
    }
    setTheme(currentTheme);
    //set the current theme
    document.querySelector("html").classList.remove(oldTheme);
    document.querySelector("html").classList.add(currentTheme);

    //change the text of button
    changeThemebutton.querySelector("span").textContent = currentTheme;
  });
}

//set theme to local storage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

//get theme from local storage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}
