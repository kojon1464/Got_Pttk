import {makeStyles} from "@material-ui/core/styles";
import WorldImg from "../assets/world.png";
import {Paper} from "@material-ui/core";
import React from "react";

const AuthContainer = ({title, caption, children}) => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <div className={classes.leftBox}>
        <img className={classes.logoImg} src={WorldImg} />
        <h1>GOT PTTK</h1>
      </div>
      <div className={classes.rightBox}>
        <h1>{title}</h1>
        <Paper className={classes.formContainer}>{children}</Paper>
        <span className={classes.link}>{caption}</span>
      </div>
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "center",
    paddingTop: 150,
    width: "100%",
    maxWidth: 1000
  },
  leftBox: {
    width: "50%",
    paddingRight: 50,
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center"
  },
  logoImg: {
    width: 250
  },
  rightBox: {
    width: "50%",
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  formContainer: {
    display: "flex",
    flexDirection: "column",
    padding: 20,
    width: 400
  },
  link: {
    marginTop: 20
  }
});

export default AuthContainer;
