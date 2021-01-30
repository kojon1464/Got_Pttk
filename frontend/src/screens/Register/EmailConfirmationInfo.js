import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import {EmailRounded} from "@material-ui/icons";
import {Typography} from "@material-ui/core";

const EmailConfirmationInfo = () => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <EmailRounded className={classes.emailIcon} />
      <Typography variant="h6">Konto zostało utworzone</Typography>
      <Typography>Wysłaliśmy wiadomość. Potwierdź swoje konto</Typography>
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 10,
    paddingBottom: 40
  },

  emailIcon: {
    fontSize: 140
  }
});

export default EmailConfirmationInfo;
