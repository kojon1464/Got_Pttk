import {makeStyles} from "@material-ui/core/styles";
import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import WorldImg from "../assets/world.png";
import ExitToAppIcon from "@material-ui/icons/ExitToAppRounded";
import PersonIcon from "@material-ui/icons/PersonRounded";

const NavBar = ({onLogoutRequest}) => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <AppBar position="static" color="transparent">
        <Toolbar>
          <img className={classes.logoImg} src={WorldImg} />
          <Typography variant="h6" className={classes.title}>
            GOT PTTK
          </Typography>

          <div className={classes.userContainer}>
            <PersonIcon className={classes.icon} />
            <Typography>Witaj, Filip</Typography>
          </div>
          <Button
            id='logout'
            color="inherit"
            startIcon={<ExitToAppIcon />}
            onClick={onLogoutRequest}
          >
            Wyloguj
          </Button>
        </Toolbar>
      </AppBar>
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    flexGrow: 1,
    width: "100%"
  },
  logoImg: {
    width: 50,
    height: 50,
    marginRight: 20
  },
  menuButton: {
    marginRight: 12
  },
  title: {
    flexGrow: 1
  },

  userContainer: {
    display: "flex",
    marginRight: 40
  },
  icon: {
    marginRight: 10
  }
});

export default NavBar;
