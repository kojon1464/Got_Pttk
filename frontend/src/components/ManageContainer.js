import React from "react";
import PropTypes from "prop-types";
import {Button, Paper} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";

const ManageContainer = ({children}) => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Paper className={classes.menuContainer}>
        <Button className={classes.linkButton} fullWidth>
          Zarządzanie lokacjami
        </Button>
        <Button className={classes.linkButton} fullWidth>
          Zarządzanie odcinkami
        </Button>
        <Button className={classes.linkButton} fullWidth>
          Zarządzanie odznakami
        </Button>
        <Button color="primary" className={classes.linkButton} fullWidth>
          Mod. grup i terenów górskich
        </Button>
        <Button className={classes.linkButton} fullWidth>
          Usuwanie użytkowników
        </Button>
        <Button className={classes.linkButton} fullWidth>
          Zmiana uprawnień
        </Button>
      </Paper>
      <Paper className={classes.contentContainer}>{children}</Paper>
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    flexDirection: "row",
    marginTop: 150,
    width: "95%"
  },

  menuContainer: {
    width: 300,
    marginRight: 8
  },

  linkButton: {
    paddingTop: 15,
    paddingBottom: 15
  },

  contentContainer: {
    flex: 1,
    padding: 20
  }
});

export default ManageContainer;
