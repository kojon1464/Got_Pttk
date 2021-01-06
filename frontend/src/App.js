import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import {makeStyles} from "@material-ui/core/styles";
import Register from "./screens/Register/Register";
import Manage from "./screens/Manage/Manage";
import Login from "./screens/Login/Login";
import React from "react";

function App() {
  const classes = useStyles();

  return (
    <Router>
      <div className={classes.root}>
        <Switch>
          <Route path="/manage">
            <Manage />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/">
            <Login />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

const useStyles = makeStyles({
  root: {
    display: "flex",
    justifyContent: "center"
  }
});

export default App;
